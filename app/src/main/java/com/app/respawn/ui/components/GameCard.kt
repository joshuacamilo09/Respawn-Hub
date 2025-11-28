package com.app.respawn.ui.components

import android.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.app.respawn.model.Game
import com.app.respawn.repository.GameRepo
import com.app.respawn.ui.main.GameListScreen
import com.app.respawn.ui.theme.RespawnTheme

@Composable
fun GameCard(game: Game, modifier: Modifier = Modifier, onClick: () -> Unit, previewDrawable: Int? = null){

    val context = LocalContext.current
    val isPreview = LocalInspectionMode.current

    val coverId = if (isPreview && previewDrawable != null) {
        previewDrawable
    }
    else {
        val id = context.resources.getIdentifier(game.coverResName, "drawable", context.packageName)
        if (id != 0) {
            id
        }
        else {
            com.app.respawn.R.drawable.cover_space_raiders
        }
    }

    Card(
        modifier = modifier.clickable{onClick ()},
        shape = RoundedCornerShape(0.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(id = coverId),
                contentDescription = game.title,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
            Column (
                modifier = Modifier.fillMaxSize().padding(9.dp),
                verticalArrangement = Arrangement.Bottom
            )
            {
                Text(
                    text = game.title,
                    style = MaterialTheme.typography.titleLarge,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GameListScreenPreview(){
    RespawnTheme {
        GameListScreen(
            games = GameRepo.games,
            onGameClick = {}
        )
    }
}