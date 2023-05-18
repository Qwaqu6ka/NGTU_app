package com.example.ngtu.views.categorydetailsfragment

import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.foundation.navigator.Navigator
import com.example.foundation.views.BaseScreen
import com.example.foundation.views.BaseViewModel
import com.example.ngtu.R
import com.example.ngtu.views.categorydetailsfragment.tabs.listtab.CategoryDetailsListFragment
import com.example.ngtu.views.categorydetailsfragment.tabs.maptab.CategoryDetailsMapFragment

class CategoryDetailsViewModel(
    val screen: CategoryDetailsFragment.Screen,
    private val navigator: Navigator
) : BaseViewModel() {

    private val _activeTab = MutableLiveData<Fragment>(CategoryDetailsListFragment())
    val activeTab: LiveData<Fragment> = _activeTab

    fun onBottomTabSelected(@IdRes tabId: Int): Boolean {
        return when (tabId) {
            R.id.listTab -> {
                _activeTab.value = CategoryDetailsListFragment()
                true
            }
            R.id.mapTab -> {
                _activeTab.value = CategoryDetailsMapFragment()
                true
            }
            else -> false
        }
    }

    fun toggleTab(screen: BaseScreen, fragmentManager: FragmentManager, containerId: Int) {
        navigator.launchInInnerFragmentManager(screen, fragmentManager, containerId)
    }
}