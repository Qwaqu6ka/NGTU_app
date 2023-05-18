package com.example.ngtu.utils

import com.example.ngtu.models.Category
import com.example.ngtu.models.Product
import com.example.ngtu.models.Shop
import com.google.firebase.firestore.GeoPoint

class FirebaseParser {
    companion object {
        fun firebaseShopParser(entity: Map<String, Any>, shopId: String): Shop =
            Shop(
                id = shopId,
                address = entity["address"] as String?,
                mid_price = entity["mid_price"] as Long?,
                name = entity["name"] as String?,
                geo = entity["geo"] as GeoPoint?,
                type = getShopCategoryByName(entity["type"] as String?)
            )

        fun firebaseProductParser(entity: Map<String, Any>): Product =
            Product(
                name = entity["name"] as String?,
                imageUrl = entity["image"] as String?,
                price = entity["price"] as Long?,
                shopId = entity["shop_id"] as String
            )

        private fun getShopCategoryByName(name: String?) : Category {
            return when (name) {
                "drugstore" -> Category.Drugstores
                "cafe" -> Category.Food
                "office" -> Category.Stationery
                else -> throw IllegalArgumentException("Wrong type in database")
            }
        }
    }
}