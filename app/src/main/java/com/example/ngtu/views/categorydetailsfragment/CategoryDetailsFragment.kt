package com.example.ngtu.views.categorydetailsfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.foundation.views.BaseFragment
import com.example.foundation.views.BaseScreen
import com.example.foundation.views.screenViewModel
import com.example.ngtu.databinding.FragmentCategoryDetailsBinding
import com.example.ngtu.models.Category
import com.example.ngtu.views.categorydetailsfragment.tabs.listtab.CategoryDetailsListFragment
import com.example.ngtu.views.categorydetailsfragment.tabs.maptab.CategoryDetailsMapFragment

class CategoryDetailsFragment : BaseFragment() {

    class Screen(
        val categoryType: Category
    ) : BaseScreen

    override val viewModel by screenViewModel<CategoryDetailsViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentCategoryDetailsBinding.inflate(inflater, container, false).also { binding ->

            binding.bottomNavView.setOnItemSelectedListener {
                viewModel.onBottomTabSelected(it.itemId)
            }

            viewModel.activeTab.observe(viewLifecycleOwner) {
                val targetScreen = when (it) {
                    is CategoryDetailsListFragment -> CategoryDetailsListFragment.Screen(viewModel.screen.categoryType)
                    is CategoryDetailsMapFragment -> CategoryDetailsMapFragment.Screen(viewModel.screen.categoryType)
                    else -> {
                        throw IllegalArgumentException("Invalid screen")
                    }
                }
                viewModel.toggleTab(targetScreen, childFragmentManager, binding.fragmentContainer.id)
            }

        }.root
    }
}