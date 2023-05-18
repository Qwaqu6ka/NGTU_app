package com.example.ngtu.views.categorydetailsfragment.tabs.maptab

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.foundation.views.BaseFragment
import com.example.foundation.views.BaseScreen
import com.example.foundation.views.BaseViewModel
import com.example.foundation.views.screenViewModel
import com.example.ngtu.databinding.FragmentCategoryDetailsMapBinding
import com.example.ngtu.models.Category

class CategoryDetailsMapFragment : BaseFragment() {

    class Screen(
        val categoryType: Category
    ) : BaseScreen

    override val viewModel by screenViewModel<CategoryDetailsMapViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentCategoryDetailsMapBinding.inflate(inflater, container, false).root
    }
}