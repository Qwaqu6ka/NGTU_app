package com.example.ngtu.views.searchfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foundation.views.BaseFragment
import com.example.foundation.views.BaseScreen
import com.example.foundation.views.screenViewModel
import com.example.ngtu.databinding.FragmentSearchBinding
import com.example.ngtu.views.renderSimpleSearchResult
import com.example.ngtu.views.shopdetailsfragment.SearchRecyclerAdapter

class SearchFragment : BaseFragment() {

    class Screen : BaseScreen

    override val viewModel by screenViewModel<SearchViewModel>()

    private lateinit var binding: FragmentSearchBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(inflater, container, false)

        with(binding) {
            val adapter = SearchRecyclerAdapter()
            searchRecyclerView.adapter = adapter
            searchRecyclerView.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

            searchLayout.editText?.addTextChangedListener {
                viewModel.onSearchTextChange(it.toString())
            }

            viewModel.searchProducts.observe(viewLifecycleOwner) { result ->
                renderSimpleSearchResult(root, result) {
                    adapter.productsList = it
                }
            }

            return root
        }
    }
}