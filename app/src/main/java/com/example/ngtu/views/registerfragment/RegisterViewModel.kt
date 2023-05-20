package com.example.ngtu.views.registerfragment

import com.example.foundation.navigator.Navigator
import com.example.foundation.uiactions.UiActions
import com.example.foundation.views.BaseViewModel
import com.example.ngtu.R
import com.example.ngtu.views.loginfragment.LoginFragment
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuth

class RegisterViewModel(
    private val navigator: Navigator,
    private val uiActions: UiActions,
    private val auth: FirebaseAuth
) : BaseViewModel() {

    fun register(login: String?, password: String?) {

        if (login.isNullOrEmpty() || password.isNullOrEmpty()) return

        auth.createUserWithEmailAndPassword(login, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    navigator.launch(LoginFragment.Screen()     )
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