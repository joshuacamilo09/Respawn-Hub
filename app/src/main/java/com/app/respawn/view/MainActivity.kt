package com.app.respawn.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.app.respawn.controller.GameController
import com.app.respawn.repository.GameRepo
import com.app.respawn.ui.detail.GameDetailActivity
import com.app.respawn.ui.main.GameListScreen
import com.app.respawn.ui.theme.RespawnTheme

class MainActivity : ComponentActivity() {

    private val controller = GameController()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val games = controller.getAllGames()
        setContent {
            RespawnTheme {
               Surface (
                   color = MaterialTheme.colorScheme.background
               ) {
                   GameListScreen (
                       games = games,
                       onGameClick = { game ->
                           val intent = Intent(this@MainActivity, GameDetailActivity::class.java)
                           intent.putExtra(GameDetailActivity.EXTRA_GAME, game)
                           startActivity(intent) //faz com que o cards sejam clicaveis e etc, é pra o funcionamento da jogo
                       }
                   )
               }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MainActivityPreview (){
    val context = LocalContext.current
    RespawnTheme {
        GameListScreen(
            games = GameRepo.games,
            onGameClick = {
                game -> val intent = Intent(context, GameDetailActivity::class.java)
                intent.putExtra(GameDetailActivity.EXTRA_GAME, game)
                context.startActivity(intent)
            }
        )
    }
}