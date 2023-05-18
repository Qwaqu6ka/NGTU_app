package com.example.ngtu.views.categorydetailsfragment.tabs.listtab

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ngtu.R
import com.example.ngtu.databinding.ShopItemBinding
import com.example.ngtu.models.Shop

class CategoryShopAdapter(
    private val listener: Listener
) : RecyclerView.Adapter<CategoryShopAdapter.Holder>(), View.OnClickListener {

    var shops: List<Shop> = emptyList()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = ShopItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        binding.root.setOnClickListener(this)
        return Holder(binding)
    }

    override fun getItemCount(): Int = shops.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(shops[position])
    }

    override fun onClick(v: View) {
        val shop = v.tag as Shop
        listener.onShopClicked(shop)
    }

    inner class Holder(private val binding: ShopItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(shopItem: Shop) {
            with(binding) {
                root.tag = shopItem

                titleTextView.text = shopItem.name ?: "Template"
                addressTextView.text = shopItem.address ?: "Template"
                shopItem.mid_price?.let {
                    priceTextView.text = itemView.context.getString(R.string.price_number, it)
                } ?: "Template"

            }
        }
    }

    interface Listener {
        fun onShopClicked(shop: Shop)
    }
}