package com.example.ngtu.models

data class Item(
    val category: Category,
    val imageUrl: String,
    val name: String,
    val price: Double,
    val shopId: String
)
