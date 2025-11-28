package com.app.respawn.model

import java.io.Serializable

data class Game (
        val id: String,
        val title: String,
        val shortDescription: String,
        val fullDescription: String,
        val coverResName: String? = null, //nome do drawable da capa
        val purchasableItem: List<PurchasableItem>
    ): Serializable