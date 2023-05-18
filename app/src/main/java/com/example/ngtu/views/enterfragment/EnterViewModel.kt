package com.example.ngtu.views.enterfragment

import com.example.foundation.navigator.Navigator
import com.example.foundation.views.BaseViewModel
import com.example.ngtu.views.loginfragment.LoginFragment
import com.example.ngtu.views.registerfragment.RegisterFragment

class EnterViewModel(
    private val navigator: Navigator
) : BaseViewModel() {

    fun onRegister() {
        navigator.launch(RegisterFragment.Screen())
    }

    fun onLogin() {
        navigator.launch(LoginFragment.Screen())
    }
}