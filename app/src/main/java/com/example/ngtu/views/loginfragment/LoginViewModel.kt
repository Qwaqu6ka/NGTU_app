package com.example.ngtu.views.loginfragment

import android.util.Log
import com.example.foundation.navigator.Navigator
import com.example.foundation.uiactions.UiActions
import com.example.foundation.views.BaseViewModel
import com.example.ngtu.R
import com.example.ngtu.views.categoryfragment.CategoryFragment
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException

class LoginViewModel(
    private val navigator: Navigator,
    private val uiActions: UiActions,
    private val auth: FirebaseAuth
) : BaseViewModel() {

    fun login(login: String, password: String) {
        auth.signInWithEmailAndPassword(login, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    navigator.launchWithNewStack(CategoryFragment.Screen())
                }
                else {
                    val message = when (task.exception) {
                        is FirebaseNetworkException -> uiActions.getString(R.string.network_error)
                        else -> uiActions.getString(R.string.auth_mistake)
                    }
                    uiActions.toast(message)
                }
            }
    }
}