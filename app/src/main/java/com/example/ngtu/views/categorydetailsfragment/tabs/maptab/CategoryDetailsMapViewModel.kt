package com.example.ngtu.views.categorydetailsfragment.tabs.maptab

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.foundation.views.BaseViewModel
import com.example.ngtu.models.FirebaseCityInfoRepository
import com.example.ngtu.models.Shop
import kotlinx.coroutines.launch

class CategoryDetailsMapViewModel(
    private val repository: FirebaseCityInfoRepository,
    private val screen: CategoryDetailsMapFragment.Screen
) : BaseViewModel() {

    private val _shopsList = MutableLiveData<List<Shop>>()
    val shopsList: LiveData<List<Shop>> = _shopsList

    init {
        load()
    }

    private fun load() = viewModelScope.launch {
        _shopsList.value = repository.getShopsList(screen.categoryType)
    }
}