package com.example.ngtu.models

import com.example.foundation.models.Repository

interface CityInfoRepository : Repository {

    suspend fun findItemsByPhrase(request: String) : List<Item>

    suspend fun getShopsList(category: Category? = null) : List<Shop>
}