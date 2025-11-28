package com.app.respawn.ui.detail

import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import com.app.respawn.model.Game
import com.app.respawn.ui.theme.RespawnTheme
import kotlin.jvm.java

class GameDetailActivity : ComponentActivity (){

    companion object{
        const val EXTRA_GAME = "extra_game"
    }

    //quando a tela é abertta, o android chama automatcamenet o onCreate.
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val game = intent.getParcelableExtra(EXTRA_GAME, Game::class.java) //pega o que veio do intent da tela interior e converte para um objeto do tipo Game.
            ?: run {
                Toast.makeText(this, "Error loading the game", Toast.LENGTH_LONG).show()
                finish()
                return
            }//tratamento de erro se o jogo nao chegou direito

        setContent {//inteface
            RespawnTheme {
                GameDetailScreen (
                    game = game,
                    onBackClick = {finish()},
                    onPurchase = {
                        item -> Toast.makeText(this, "Acabou de comprar o item ${item.name} por $${item.price}", Toast.LENGTH_LONG).show()
                    }
                )
            }
        }
    }
}