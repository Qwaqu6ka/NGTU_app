package com.example.foundation.navigator

import androidx.fragment.app.FragmentManager
import com.example.foundation.utils.ResourceActions
import com.example.foundation.views.BaseScreen

class IntermediateNavigator : Navigator {

    private val targetNavigator = ResourceActions<Navigator>()

    override fun launch(screen: BaseScreen) = targetNavigator {
        it.launch(screen)
    }

    override fun launchWithNewStack(screen: BaseScreen) = targetNavigator {
        it.launchWithNewStack(screen)
    }

    override fun launchInInnerFragmentManager(
        screen: BaseScreen,
        fragmentManager: FragmentManager,
        containerId: Int
    ) = targetNavigator {
        it.launchInInnerFragmentManager(screen, fragmentManager, containerId)
    }

    fun setTarget(navigator: Navigator?) {
        targetNavigator.resource = navigator
    }

    fun clear() {
        targetNavigator.clear()
    }
}