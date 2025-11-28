package com.app.respawn.model

import java.io.Serializable

data class PurchasableItem (
    val id: String,
    val name: String,
    val description: String,
    val price : Double,
    val iconResName: String? = null //nome do drawable do icone
): Serializable