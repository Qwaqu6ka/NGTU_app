package com.example.foundation.navigator

import androidx.fragment.app.FragmentManager
import com.example.foundation.views.BaseScreen

interface Navigator {

    fun launch(screen: BaseScreen)

    fun launchWithNewStack(screen: BaseScreen)

    fun launchInInnerFragmentManager(screen: BaseScreen, fragmentManager: FragmentManager, containerId: Int)

    fun goBack(result: Any? = null)
}