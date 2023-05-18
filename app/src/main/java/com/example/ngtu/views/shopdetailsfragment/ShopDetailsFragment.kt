package com.example.ngtu.views.shopdetailsfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foundation.views.BaseFragment
import com.example.foundation.views.BaseScreen
import com.example.foundation.views.screenViewModel
import com.example.ngtu.databinding.FragmentShopDetailsBinding
import com.example.ngtu.models.Shop
import com.example.ngtu.views.renderSimpleResult

class ShopDetailsFragment : BaseFragment() {

    class Screen(
        val shop: Shop
    ) : BaseScreen

    override val viewModel by screenViewModel<ShopDetailsViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentShopDetailsBinding.inflate(inflater, container, false).also { binding ->

            val adapter = ShopItemsAdapter()
            binding.recyclerView.adapter = adapter
            binding.recyclerView.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

            viewModel.shopProducts.observe(viewLifecycleOwner) { result ->
                renderSimpleResult(binding.root, result) {
                    adapter.productsList = it
                }
            }

        }.root
    }
}