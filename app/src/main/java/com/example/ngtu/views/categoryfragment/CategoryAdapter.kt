package com.example.ngtu.views.categoryfragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ngtu.databinding.CategotyItemBinding
import com.example.ngtu.models.Category

class CategoryAdapter(
    private val listener: Listener
) : RecyclerView.Adapter<CategoryAdapter.Holder>(), View.OnClickListener {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding =
            CategotyItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        binding.button.setOnClickListener(this)
        return Holder(binding)
    }

    override fun getItemCount(): Int = Category.values().size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val category = Category.values()[position]
        holder.bind(category)
        holder.binding.button.tag = category
    }

    override fun onClick(v: View) {
        val category = v.tag as Category
        listener.onCategoryChosen(category)
    }

    inner class Holder(
        val binding: CategotyItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(category: Category) {
            binding.button.text = binding.root.context.getString(category.nameRes)
        }
    }

    interface Listener {
        fun onCategoryChosen(category: Category)
    }
}