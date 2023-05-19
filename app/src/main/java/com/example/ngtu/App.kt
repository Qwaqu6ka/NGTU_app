package com.example.ngtu

import android.app.Application
import com.example.foundation.BaseApplication
import com.example.ngtu.models.FirebaseCityInfoRepository
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.yandex.mapkit.MapKitFactory

class App : Application(), BaseApplication {

    val _singletonScopeDependencies = mutableListOf<Any>()
    override val singletonScopeDependencies: List<Any> = _singletonScopeDependencies

    lateinit var auth: FirebaseAuth

    override fun onCreate() {
        super.onCreate()

        FirebaseApp.initializeApp(this)
        auth = Firebase.auth
        _singletonScopeDependencies += auth

        val firebaseCityInfoRepository = FirebaseCityInfoRepository()
        _singletonScopeDependencies += firebaseCityInfoRepository

        MapKitFactory.setApiKey("36b165b7-c1f6-4cf6-a63a-c119209c8e0c")
    }
}