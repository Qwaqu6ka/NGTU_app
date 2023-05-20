package com.example.ngtu.models.repository

import com.example.ngtu.models.Category
import com.example.ngtu.models.Product
import com.example.ngtu.models.Shop

interface CityInfoRepository {

    suspend fun getProductsByPhrase(request: String) : List<Product>

    suspend fun getProductsByShopId(shopId: String) : List<Product>

    suspend fun getShopsList(category: Category? = null) : List<Shop>
}