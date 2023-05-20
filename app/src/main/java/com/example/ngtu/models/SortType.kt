package com.example.ngtu.models

import com.example.ngtu.R

enum class SortType {

    SortByTitle {
        override val titleRes: Int
            get() = R.string.by_title

        override fun sortShops(list: List<Shop>): List<Shop> = list.sortedBy { it.name }

        override fun sortProducts(list: List<Product>): List<Product> = list.sortedBy { it.name }
    },

    SortByPrice {
        override val titleRes: Int
            get() = R.string.by_price

        override fun sortShops(list: List<Shop>): List<Shop> = list.sortedBy { it.mid_price }

        override fun sortProducts(list: List<Product>): List<Product> = list.sortedBy { it.price }
    };

    abstract val titleRes: Int
    abstract fun sortShops(list: List<Shop>): List<Shop>
    abstract fun sortProducts(list: List<Product>): List<Product>
}
