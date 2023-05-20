package com.example.ngtu.views

import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.view.children
import com.example.foundation.models.ErrorResult
import com.example.foundation.models.PendingResult
import com.example.foundation.models.Result
import com.example.foundation.models.SuccessResult
import com.example.foundation.views.BaseFragment
import com.example.ngtu.R
import com.example.ngtu.databinding.PartResultBinding

fun <T> BaseFragment.renderSearchResult(root: ViewGroup, result: Result<T>,
                     onPending: () -> Unit,
                     onError: (Exception) -> Unit,
                     onSuccess: (T) -> Unit) {
    root.children
        .filter { it.id != R.id.searchLayout }
        .forEach { it.visibility = View.GONE }
    when (result) {
        is PendingResult -> onPending()
        is ErrorResult -> onError(result.exception)
        is SuccessResult -> onSuccess(result.data)
    }
}

fun <T> BaseFragment.renderSimpleSearchResult(root: ViewGroup, result: Result<T>, onSuccess: (T) -> Unit) {
    val resultBinding = PartResultBinding.bind(root)
    renderSearchResult(
        root = root,
        result = result,
        onSuccess = { successData ->
            root.children
                .filter { it.id != resultBinding.errorContainer.id && it.id != resultBinding.progressBar.id }
                .forEach { it.visibility = View.VISIBLE }
            onSuccess(successData)
        },
        onError = {
            resultBinding.errorContainer.visibility = View.VISIBLE
        },
        onPending = {
            resultBinding.progressBar.visibility = View.VISIBLE
        }
    )
}

fun <T> BaseFragment.renderSimpleResult(root: ViewGroup, result: Result<T>, onSuccess: (T) -> Unit) {
    val resultBinding = PartResultBinding.bind(root)
    renderResult(
        root = root,
        result = result,
        onSuccess = { successData ->
            root.children
                .filter { it.id != resultBinding.errorContainer.id && it.id != resultBinding.progressBar.id }
                .forEach { it.visibility = View.VISIBLE }
            onSuccess(successData)
        },
        onError = {
            resultBinding.errorContainer.visibility = View.VISIBLE
        },
        onPending = {
            resultBinding.progressBar.visibility = View.VISIBLE
        }
    )
}

fun BaseFragment.onTryAgain(root: ViewGroup, onTryAgain: () -> Unit) {
    val button = root.findViewById<Button>(R.id.tryAgainButton)
    button.setOnClickListener { onTryAgain() }
}