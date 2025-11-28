package com.app.respawn.ui.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.app.respawn.model.Game
import com.app.respawn.model.PurchasableItem
import com.app.respawn.repository.GameRepo
import com.app.respawn.ui.components.PurchasableItemRow
import com.app.respawn.ui.theme.RespawnTheme

@OptIn(ExperimentalMaterial3Api::class)//por causa do sheetState
@Composable //por causa do selectedItem by
fun GameDetailScreen (game: Game, onBackClick: () -> Unit, onPurchase: (PurchasableItem) -> Unit) {

    val context = LocalContext.current
    val sheetState = rememberModalBottomSheetState()
    var selectedItem by remember { mutableStateOf<PurchasableItem?>(null) }

    val coverId = context.resources.getIdentifier(game.coverResName, "drawable", context.packageName) //busca pelo cover

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text( text = game.title, style = MaterialTheme.typography.titleLarge)
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "voltar")
                    }
                },
                actions = { //visual
                    IconButton(onClick = { }) {
                        Icon(Icons.Default.FavoriteBorder, contentDescription = "Favorito")
                    }
                }
            )
        }
    ) { padding -> //espaço pra topbar e bottomBar.
        LazyColumn(
            modifier = Modifier
                .fillMaxSize().padding(padding), //o padding daqui so afecta os itens da lista
            contentPadding = PaddingValues(bottom = 80.dp, top = 32.dp), //padding extra pra evitar conflito dos itens com a naviBar e bottomsheet
            verticalArrangement = Arrangement.spacedBy(10.dp) //espaço entre os cards mas acho que vou remover
        ) {
            item {
                Row( //organizar na horizontal
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 13.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp) //pra imagem e texto nao ficarem colados
                ) {
                    Image(
                        painter = painterResource(id = coverId),
                        contentDescription = null,
                        modifier = Modifier.size(140.dp).aspectRatio(1f).clip(MaterialTheme.shapes.medium) //arredondada
                    )

                    Text(
                        text = game.fullDescription,
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.weight(1f).alignByBaseline() //alinhar texto com imagem
                    )
                }
            }

            //lista dos itens compraveis

            item {
                Text (text = "Purchasable Items",
                    style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold, modifier = Modifier.padding(horizontal = 16.dp))
            }

            items(game.purchasableItem) {
                 item -> PurchasableItemRow(item = item) {
                    //para cada item mostra um purchasableItemRow
                    selectedItem = item
                }
            }
        }
    }

    //janela de compra, isso so e exibido se o user tiver tocado nalgum item
    selectedItem?.let {
        item -> PurchaseBottomSheet (
            item = item,
            sheetState = sheetState,
            onDismiss = {selectedItem = null},
            onPurchase = {
                onPurchase(item)
                selectedItem = null
            }
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GameDetailScreenPreview() {
    RespawnTheme {
        GameDetailScreen(
            game = GameRepo.games[0],
            onBackClick = {},
            onPurchase = {}
        )
    }
}