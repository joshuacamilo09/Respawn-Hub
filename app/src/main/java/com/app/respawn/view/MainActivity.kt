package com.app.respawn.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.respawn.controller.GameController
import com.app.respawn.model.Game
import com.app.respawn.repository.GameRepo
import com.app.respawn.ui.components.GameCard
import com.app.respawn.ui.detail.GameDetailActivity
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
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameListScreen(games: List<Game>, onGameClick: (Game) -> Unit){

    val screenBg = Color(0xFFF6EDF2)
    val context = LocalContext.current

    Scaffold (
        containerColor = screenBg,
        topBar = {
            TopAppBar(
                title = {},
                actions = {
                    IconButton(onClick = {}) {
                        Icon(
                            imageVector = Icons.Default.Notifications,
                            contentDescription = "Notificação"
                        )
                    }
                    IconButton(onClick = {}) {
                        Icon(
                            imageVector = Icons.Default.Settings,
                            contentDescription = "Settings"
                        )
                    }
                },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = screenBg
                )
            )
        },
        bottomBar = {
            NavigationBar (containerColor = Color.White, tonalElevation = 8.dp, modifier = Modifier.fillMaxWidth()){
                NavigationBarItem(
                    selected = false,
                    onClick = {},
                    icon = {Icon(Icons.Default.DateRange, contentDescription = "History")},
                    label = {Text("History")}
                )
                NavigationBarItem(
                    selected = false,
                    onClick = {},
                    icon = {Icon(Icons.Default.Star, contentDescription = "Featured")},
                    label = {Text("Featured")}
                )
                NavigationBarItem(
                    selected = false,
                    onClick = {},
                    icon = {Icon(Icons.Default.Person, contentDescription = "Profile")},
                    label = {Text("Profile")}
                )
            }
        }
    ) { innerPadding ->
        LazyColumn (
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(screenBg),
            contentPadding = PaddingValues(bottom = 80.dp)
        ) {
            item {
                Text(
                    text = "Respawn Hub",
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 55.dp)
                )
            }

            items(games) {
                    game -> GameCard (
                game = game,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(175.dp),
                onClick = {onGameClick(game)}
            )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MainActivityPreview() {
    RespawnTheme {
        GameListScreen(
            games = GameRepo.games,
            onGameClick = {}
        )
    }
}
