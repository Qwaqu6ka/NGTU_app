package com.example.ngtu.views.categorydetailsfragment.tabs.listtab

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foundation.views.BaseFragment
import com.example.foundation.views.BaseScreen
import com.example.foundation.views.BaseViewModel
import com.example.foundation.views.screenViewModel
import com.example.ngtu.databinding.FragmentCategoryDetailsListBinding
import com.example.ngtu.models.Category
import com.example.ngtu.views.categorydetailsfragment.CategoryDetailsFragment
import com.example.ngtu.views.onTryAgain
import com.example.ngtu.views.renderSimpleResult

class CategoryDetailsListFragment : BaseFragment() {

    class Screen(
        val categoryType: Category
    ) : BaseScreen

    override val viewModel by screenViewModel<CategoryDetailsListViewModel>()

    private lateinit var binding: FragmentCategoryDetailsListBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCategoryDetailsListBinding.inflate(inflater, container, false)

        val adapter = CategoryShopAdapter()
        binding.recyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.recyclerView.adapter = adapter

        viewModel.shopsList.observe(viewLifecycleOwner) { result ->
            renderSimpleResult(binding.root, result) {
                adapter.shops = it
            }
        }

        onTryAgain(binding.root) {
            viewModel.onTryAgain()
        }

        return binding.root
    }
}