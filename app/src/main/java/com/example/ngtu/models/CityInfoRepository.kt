package com.example.ngtu.models

interface CityInfoRepository {

    suspend fun findItemsByPhrase(request: String) : List<Product>

    suspend fun getProductsByShopId(shopId: String) : List<Product>

    suspend fun getShopsList(category: Category? = null) : List<Shop>
}