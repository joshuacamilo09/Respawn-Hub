package com.app.respawn.repository

import com.app.respawn.model.Game
import com.app.respawn.model.PurchasableItem

object GameRepo {
    val games: List<Game> by lazy {
        listOf(
            Game(
                id = "Game 1",
                title = "Space Raiders",
                shortDescription = "FPS espacial com naves personalizadas",
                fullDescription = "Space Raiders: explore a galáxia, lute com piratas e melhore a sua nave com skins e pacotes de energia",
                coverResName = "cover_space_raiders",
                purchasableItem = listOf(
                    PurchasableItem("sr_skin_01", "Skin do cavalheiro", "Skin rara pra nave", 3.99, "icon_skin"),
                    PurchasableItem("sr_dlc_fleet", "Pacote Fleet", "Expansão com 3 naves novas", 9.99, "icon_dlc"),
                    PurchasableItem("sr_coin_500", "500 Creditos", "Moedas para upgrades", 15.99, "icon_coins")
                )
            ),
            Game(
                id = "Game 2",
                title = "KingDoms Rise",
                shortDescription = "Estratégia e conquista em tempo real",
                fullDescription = "KingDoms Rise: construa o seu reino, recrute heróis e conquiste territórios com DLCs e passe sazonais",
                coverResName = "cover_kingdoms_rises",
                purchasableItem = listOf(
                    PurchasableItem("kr_pass_season 1", "Passe temporada 1", "Conteúdo sazonal de 3 meses", 7.50, "icon_pass"),
                    PurchasableItem("kr_hero_bundle", "Pacote de heróis", "4 heróis exclusivos", 5.99, "icon_hero"),
                    PurchasableItem("kr_gems_100", "100 Gems", "Gemas Premium", 2.50, "icon_gems")
                )
            )
        )
    }
}