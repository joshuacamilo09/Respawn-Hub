package com.app.respawn.ui.detail

import android.widget.Space
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.app.respawn.model.PurchasableItem
import kotlinx.coroutines.launch

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun PurchaseBottomSheet (item: PurchasableItem, sheetState: SheetState, onDismiss: () -> Unit, onPurchase: () -> Unit){

    val scope = rememberCoroutineScope() //espaço pra lançar corrotinas dentro do compose
    val context= LocalContext.current
    val iconId = context.resources.getIdentifier(item.iconResName,"drawable", context.packageName)

    ModalBottomSheet( //painel
        onDismissRequest = onDismiss,
        sheetState = sheetState
    ) {
        Column( //layout vertical com bordas internas
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 32.dp)
        ) {
            Text(text = item.name, style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold, modifier = Modifier.align(Alignment.Start))

            Spacer(modifier = Modifier.height(5.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Card (
                    shape = MaterialTheme.shapes.medium,
                    colors = CardDefaults.cardColors(containerColor = Color.LightGray),
                    elevation = CardDefaults.cardElevation(4.dp)
                ) {
                    Image (
                        painter = painterResource(id = iconId),
                        contentDescription = null,
                        modifier = Modifier
                            .size(100.dp)
                            .clip(MaterialTheme.shapes.medium)
                    )
                }

                Text(
                    text = item.description,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.weight(1f),
                    textAlign =  androidx.compose.ui.text.style.TextAlign.Start
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            //preco + botao de compra
            Row (modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End, verticalAlignment = Alignment.CenterVertically) //vai meter tudo na direita
            {
                Text(text = "$${String.format("%.2f", item.price)}", style = MaterialTheme.typography.titleLarge, color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold)

                Spacer(modifier = Modifier.width(16.dp))

                Button(onClick = {
                    scope.launch { sheetState.hide() }.invokeOnCompletion {
                        if (!sheetState.isVisible){
                            onPurchase()
                        }
                    }
                }) {
                    Text("Buy with 1-click")
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}