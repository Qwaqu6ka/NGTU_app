package com.example.ngtu.models

import com.google.firebase.firestore.GeoPoint

data class Shop(
    val id: String,
    val address: String?,
    val mid_price: Long?,
    val name: String?,
    val type: Category?,
    val geo: GeoPoint?
)
