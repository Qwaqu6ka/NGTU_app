package com.example.ngtu.models

import com.example.ngtu.utils.FirebaseParser
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

class FirebaseCityInfoRepository : CityInfoRepository {

    private val db = Firebase.firestore

    override suspend fun findItemsByPhrase(request: String): List<Item> {
        TODO("Not yet implemented")
    }

    override suspend fun getShopsList(category: Category?): List<Shop> {
        var listToReturn = emptyList<Shop>()

        db.collection("shops")
            .get()
            .addOnSuccessListener { result ->
                listToReturn = result.map {
                    FirebaseParser.firebaseShopParser(it.data)
                }
            }
            .addOnFailureListener {
                throw it
            }.await()

        return if (category != null)
            listToReturn.filter {
                it.type == category
            }
        else
            listToReturn
    }
}