package com.example.ngtu.views.categorydetailsfragment.tabs.listtab

import com.example.foundation.models.PendingResult
import com.example.foundation.models.SuccessResult
import com.example.foundation.navigator.Navigator
import com.example.foundation.views.BaseViewModel
import com.example.foundation.views.LiveResult
import com.example.foundation.views.MutableLiveResult
import com.example.ngtu.models.repository.FirebaseCityInfoRepository
import com.example.ngtu.models.Shop
import com.example.ngtu.models.SortType
import com.example.ngtu.views.searchfragment.SearchFragment
import com.example.ngtu.views.shopdetailsfragment.ShopDetailsFragment

class CategoryDetailsListViewModel(
    private val screen: CategoryDetailsListFragment.Screen,
    private val navigator: Navigator,
    private val repository: FirebaseCityInfoRepository
) : BaseViewModel(), CategoryShopAdapter.Listener {

    private val _shopsList = MutableLiveResult<List<Shop>>(PendingResult())
    val shopsList: LiveResult<List<Shop>> = _shopsList

    init {
        load()
    }

    override fun onShopClicked(shop: Shop) {
        navigator.launch(ShopDetailsFragment.Screen(shop))
    }

    fun onTryAgain() {
        load()
    }

    fun onSearchButtonClicked() {
        navigator.launch(SearchFragment.Screen())
    }

    fun onSortItemClick(position: Int) {
        val oldList = _shopsList.value?.takeSuccess() ?: return
        val sortedList = SortType.values()[position].sortShops(oldList)
        _shopsList.value = SuccessResult(sortedList)
    }

    private fun load() = into(_shopsList) { repository.getShopsList(screen.categoryType) }

}