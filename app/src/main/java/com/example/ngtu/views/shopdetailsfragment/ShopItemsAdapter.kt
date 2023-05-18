package com.example.ngtu.views.shopdetailsfragment

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ngtu.R
import com.example.ngtu.databinding.ProductItemBinding
import com.example.ngtu.models.Product

class ShopItemsAdapter : RecyclerView.Adapter<ShopItemsAdapter.Holder>() {

    var productsList: List<Product> = emptyList()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = ProductItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun getItemCount(): Int = productsList.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(productsList[position])
    }

    inner class Holder(private val binding: ProductItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(product: Product) {

            with(binding) {
                titleTextView.text = product.name
                priceTextView.text = root.context.getString(R.string.price_number, product.price)

                if (product.imageUrl?.isNotBlank() == true) {
                    Glide.with(productImageView.context)
                        .load(product.imageUrl)
                        .centerCrop()
                        .placeholder(R.drawable.ic_image_placeholder)
                        .error(R.drawable.ic_image_placeholder)
                        .into(productImageView)
                } else {
                    Glide.with(productImageView.context).clear(productImageView)
                    productImageView.setImageResource(R.drawable.ic_image_placeholder)
                }
            }
        }
    }
}