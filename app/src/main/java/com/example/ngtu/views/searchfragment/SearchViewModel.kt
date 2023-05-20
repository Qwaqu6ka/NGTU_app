package com.example.ngtu.views.searchfragment

import com.example.foundation.models.PendingResult
import com.example.foundation.models.SuccessResult
import com.example.foundation.views.BaseViewModel
import com.example.foundation.views.LiveResult
import com.example.foundation.views.MutableLiveResult
import com.example.ngtu.models.Product
import com.example.ngtu.models.repository.FirebaseCityInfoRepository
import kotlinx.coroutines.launch

class SearchViewModel(
    private val repository: FirebaseCityInfoRepository
) : BaseViewModel() {

    private val _searchProducts = MutableLiveResult<List<Product>>(SuccessResult(emptyList()))
    val searchProducts: LiveResult<List<Product>> = _searchProducts

    fun onSearchTextChange(searchText: String) {
        _searchProducts.value = PendingResult()

        viewModelScope.launch {
            _searchProducts.value = SuccessResult(repository.getProductsByPhrase(searchText))
        }
    }
}