package com.example.ngtu.models

import androidx.annotation.StringRes
import com.example.ngtu.R
import java.io.Serializable

enum class Category(@StringRes val nameRes: Int) : Serializable {
    Food(R.string.food),
    Drugstores(R.string.drugstore),
    Stationery(R.string.stationery)
}