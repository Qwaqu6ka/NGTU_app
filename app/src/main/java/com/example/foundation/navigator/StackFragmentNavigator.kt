package com.example.foundation.navigator

import android.os.Bundle
import androidx.annotation.AnimRes
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.foundation.ARG_SCREEN
import com.example.foundation.utils.Event
import com.example.foundation.views.BaseFragment
import com.example.foundation.views.BaseScreen

class StackFragmentNavigator(
    private val activity: AppCompatActivity,
    @IdRes private val fragmentContainerId: Int,
    private val animations: Animations,
    private val initialScreenCreator: () -> BaseScreen
) : Navigator {

    private var result: Event<Any>? = null

    override fun launch(screen: BaseScreen) {
        launchFragment(screen)
    }

    override fun launchWithNewStack(screen: BaseScreen) {
        activity.supportFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        launchFragment(screen, false)
    }

    override fun launchInInnerFragmentManager(
        screen: BaseScreen,
        fragmentManager: FragmentManager,
        containerId: Int
    ) {
        launchFragment(screen, false, fragmentManager, containerId)
    }


    override fun goBack(result: Any?) {
        if (result != null) {
            this.result = Event(result)
        }
        activity.onBackPressedDispatcher.onBackPressed()
    }

    fun onCreate(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            launchFragment(
                screen = initialScreenCreator(),
                addToBackStack = false
            )
        }
    }

    fun onBackPressed() {
        val f = getCurrentFragment()
        if (f is BaseFragment) {
            f.viewModel.onBackPressed()
        }
        closeScreen()
    }

    fun notifyScreenUpdates() {
        if (activity.supportFragmentManager.backStackEntryCount > 0) {
            activity.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        } else {
            activity.supportActionBar?.setDisplayHomeAsUpEnabled(false)
        }
    }

    private fun launchFragment(screen: BaseScreen, addToBackStack: Boolean = true,
                               fragmentManager: FragmentManager? = null,
                               containerId: Int? = null) {
        // creating fragment from screen
        val fragment = screen.javaClass.enclosingClass.newInstance() as Fragment
        fragment.arguments = bundleOf(ARG_SCREEN to screen)

        if (fragmentManager != null) {
            val transaction = fragmentManager.beginTransaction()
            if (addToBackStack) transaction.addToBackStack(null)
            transaction
                .replace(containerId!!, fragment)
                .commit()
        } else {
            val transaction = activity.supportFragmentManager.beginTransaction()
            if (addToBackStack) transaction.addToBackStack(null)
            transaction
                .setCustomAnimations(
                    animations.enter,
                    animations.exit,
                    animations.popEnter,
                    animations.popExit
                )
                .replace(fragmentContainerId, fragment)
                .commit()
        }
    }

    private fun closeScreen() = with(activity.supportFragmentManager) {
        if (backStackEntryCount > 0)
            activity.supportFragmentManager.popBackStack()
        else
            activity.finish()
    }

    private fun getCurrentFragment(): Fragment? =
        activity.supportFragmentManager.findFragmentById(fragmentContainerId)

    class Animations(
        @AnimRes val enter: Int,
        @AnimRes val exit: Int,
        @AnimRes val popEnter: Int,
        @AnimRes val popExit: Int
    )
}