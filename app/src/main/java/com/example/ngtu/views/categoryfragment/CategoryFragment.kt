package com.example.ngtu.views.categoryfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foundation.views.BaseFragment
import com.example.foundation.views.BaseScreen
import com.example.foundation.views.screenViewModel
import com.example.ngtu.databinding.FragmentCategoriesBinding

class CategoryFragment : BaseFragment() {

    class Screen : BaseScreen

    override val viewModel by screenViewModel<CategoryViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentCategoriesBinding.inflate(inflater, container, false).also { binding ->
            with(binding.categoryRecyclerView) {
                layoutManager =
                    LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
                adapter = CategoryAdapter(viewModel)
            }
        }.root
    }
}