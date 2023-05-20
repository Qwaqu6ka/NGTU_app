package com.example.ngtu.views.categorydetailsfragment.tabs.listtab

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foundation.views.BaseFragment
import com.example.foundation.views.BaseScreen
import com.example.foundation.views.screenViewModel
import com.example.ngtu.databinding.FragmentCategoryDetailsListBinding
import com.example.ngtu.models.Category
import com.example.ngtu.models.SortType
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

        val adapter = CategoryShopAdapter(viewModel)
        binding.recyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.recyclerView.adapter = adapter

        viewModel.shopsList.observe(viewLifecycleOwner) { result ->
            renderSimpleResult(binding.root, result) {
                adapter.shops = it
            }
        }

        val dropdownAdapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_dropdown_item,
            SortType.values().map { getString(it.titleRes) }
        )
        binding.dropdownMenu.adapter = dropdownAdapter

        binding.dropdownMenu.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                viewModel.onSortItemClick(position)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        binding.searchByProductsButton.setOnClickListener {
            viewModel.onSearchButtonClicked()
        }

        onTryAgain(binding.root) {
            viewModel.onTryAgain()
        }

        return binding.root
    }
}