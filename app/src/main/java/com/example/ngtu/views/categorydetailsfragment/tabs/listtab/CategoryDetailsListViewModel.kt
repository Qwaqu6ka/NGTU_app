package com.example.ngtu.views.categorydetailsfragment.tabs.listtab

import com.example.foundation.models.PendingResult
import com.example.foundation.views.BaseViewModel
import com.example.foundation.views.LiveResult
import com.example.foundation.views.MutableLiveResult
import com.example.ngtu.models.FirebaseCityInfoRepository
import com.example.ngtu.models.Shop

class CategoryDetailsListViewModel(
    private val screen: CategoryDetailsListFragment.Screen,
    private val repository: FirebaseCityInfoRepository
) : BaseViewModel() {

    private val _shopsList = MutableLiveResult<List<Shop>>(PendingResult())
    val shopsList: LiveResult<List<Shop>> = _shopsList

    init {
        load()
    }

    fun onTryAgain() {
        load()
    }

    private fun load() = into(_shopsList) { repository.getShopsList(screen.categoryType) }
}