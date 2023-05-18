package com.example.ngtu.models

import com.example.foundation.models.Repository

interface CityInfoRepository : Repository {

    suspend fun findItemsByPhrase(request: String) : List<Product>

    suspend fun getProductsByShopId(shopId: String) : List<Product>

    suspend fun getShopsList(category: Category? = null) : List<Shop>
}