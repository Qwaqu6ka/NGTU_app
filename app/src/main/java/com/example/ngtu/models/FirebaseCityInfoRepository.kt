package com.example.ngtu.models

import com.example.ngtu.utils.FirebaseParser
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

class FirebaseCityInfoRepository : CityInfoRepository {

    private val db = Firebase.firestore
    private var shops: List<Shop>? = null

    override suspend fun findItemsByPhrase(request: String): List<Product> {
        TODO("Not yet implemented")
    }

    override suspend fun getProductsByShopId(shopId: String): List<Product> {

        var listToReturn = emptyList<Product>()

        db.collection("items")
            .whereEqualTo("shop_id", shopId)
            .get()
            .addOnSuccessListener { documents ->
                listToReturn = documents.map {
                    FirebaseParser.firebaseProductParser(it.data)
                }
            }
            .addOnFailureListener { throw it }
            .await()

        return listToReturn
    }

    override suspend fun getShopsList(category: Category?): List<Shop> {

        if (shops != null) return filterShopListByCategory(shops!!, category)

        db.collection("shops")
            .get()
            .addOnSuccessListener { result ->
                shops = result.map {
                    FirebaseParser.firebaseShopParser(it.data, it.id)
                }
            }
            .addOnFailureListener { throw it }
            .await()

        return filterShopListByCategory(shops!!, category)
    }

    private fun filterShopListByCategory(list: List<Shop>, category: Category?) : List<Shop> {
        if (category == null) return list

        return list.filter {
            it.type == category
        }
    }
}