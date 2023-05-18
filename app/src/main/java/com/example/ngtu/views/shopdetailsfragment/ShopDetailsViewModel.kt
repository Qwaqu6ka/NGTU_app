package com.example.ngtu.views.shopdetailsfragment

import com.example.foundation.models.PendingResult
import com.example.foundation.views.BaseViewModel
import com.example.foundation.views.LiveResult
import com.example.foundation.views.MutableLiveResult
import com.example.ngtu.models.FirebaseCityInfoRepository
import com.example.ngtu.models.Product

class ShopDetailsViewModel(
    private val screen: ShopDetailsFragment.Screen,
    private val repository: FirebaseCityInfoRepository
) : BaseViewModel() {

    private val _shopProducts = MutableLiveResult<List<Product>>(PendingResult())
    val shopProducts: LiveResult<List<Product>> = _shopProducts

    init {
        loadProducts()
    }

    private fun loadProducts() {
        val shopId = screen.shop.id
        into(_shopProducts) { repository.getProductsByShopId(shopId) }
    }
}