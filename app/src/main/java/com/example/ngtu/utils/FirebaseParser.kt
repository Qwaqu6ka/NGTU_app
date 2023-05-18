package com.example.ngtu.utils

import com.example.ngtu.models.Category
import com.example.ngtu.models.Shop
import com.google.firebase.firestore.GeoPoint

class FirebaseParser {
    companion object {
        fun firebaseShopParser(entity: Map<String, Any>): Shop =
            Shop(
                address = entity["address"] as String?,
                mid_price = entity["mid_price"] as Long?,
                name = entity["name"] as String?,
                imageUrl = entity["url"] as String?,
                geo = entity["geo"] as GeoPoint?,
                type = when (entity["type"] as String?) {
                    "drugstore" -> Category.Drugstores
                    "cafe" -> Category.Food
                    "office" -> Category.Stationery
                    else -> throw IllegalArgumentException("Wrong type in database")
                }
            )
    }
}