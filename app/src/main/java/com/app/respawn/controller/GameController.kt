package com.app.respawn.controller

import com.app.respawn.model.Game
import com.app.respawn.repository.GameRepo
class GameController {
    fun getAllGames(): List<Game> = GameRepo.games
}