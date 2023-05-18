package com.example.ngtu.views.categorydetailsfragment.tabs.listtab

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ngtu.R
import com.example.ngtu.databinding.ShopItemBinding
import com.example.ngtu.models.Shop

class CategoryShopAdapter : RecyclerView.Adapter<CategoryShopAdapter.Holder>() {

    var shops: List<Shop> = emptyList()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = ShopItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun getItemCount(): Int = shops.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(shops[position])
    }

    inner class Holder(private val binding: ShopItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(shopItem: Shop) {
            with(binding) {
                titleTextView.text = shopItem.name ?: "Template"
                addressTextView.text = shopItem.address ?: "Template"
                shopItem.mid_price?.let {
                    priceTextView.text =
                        itemView.context.getString(R.string.price_number, it)
                } ?: "Template"

                if (shopItem.imageUrl?.isNotBlank() == true) {
                    Glide.with(shopImageView.context)
                        .load(shopItem.imageUrl)
                        .centerCrop()
                        .placeholder(R.drawable.ic_image_placeholder)
                        .error(R.drawable.ic_image_placeholder)
                        .into(shopImageView)
                } else {
                    Glide.with(shopImageView.context).clear(shopImageView)
                    shopImageView.setImageResource(R.drawable.ic_image_placeholder)
                }
            }
        }
    }
}