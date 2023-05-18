package com.example.ngtu.views.categoryfragment

import com.example.foundation.navigator.Navigator
import com.example.foundation.uiactions.UiActions
import com.example.foundation.views.BaseViewModel
import com.example.ngtu.models.Category
import com.example.ngtu.views.categorydetailsfragment.CategoryDetailsFragment

class CategoryViewModel(
    private val navigator: Navigator
) : BaseViewModel(), CategoryAdapter.Listener {

    override fun onCategoryChosen(category: Category) {
        navigator.launch(CategoryDetailsFragment.Screen(category))
    }

}