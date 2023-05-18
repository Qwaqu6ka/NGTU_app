package com.example.ngtu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import com.example.foundation.ActivityScopeViewModel
import com.example.foundation.navigator.IntermediateNavigator
import com.example.foundation.navigator.StackFragmentNavigator
import com.example.foundation.uiactions.AndroidUiActions
import com.example.foundation.utils.viewModelCreator
import com.example.foundation.views.BaseScreen
import com.example.foundation.views.FragmentHolder
import com.example.ngtu.databinding.ActivityMainBinding
import com.example.ngtu.views.categoryfragment.CategoryFragment
import com.example.ngtu.views.enterfragment.EnterFragment

class MainActivity : AppCompatActivity(), FragmentHolder {

    private lateinit var navigator: StackFragmentNavigator

    private val activityViewModel by viewModelCreator<ActivityScopeViewModel> {
        ActivityScopeViewModel(
            navigator = IntermediateNavigator(),
            uiActions = AndroidUiActions(applicationContext)
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        onBackPressedDispatcher.addCallback(onBackPressedCallback)

        navigator = StackFragmentNavigator(
            activity = this,
            fragmentContainerId = binding.mainFragmentContainer.id,
            defaultToolbarTitleRes = R.string.app_name,
            animations = StackFragmentNavigator.Animations(
                enter = R.anim.enter,
                exit = R.anim.exit,
                popEnter = R.anim.pop_enter,
                popExit = R.anim.pop_exit
            ),
            initialScreenCreator = ::authScreenInitializer
        )
        navigator.onCreate(savedInstanceState)
    }

    override fun onDestroy() {
        navigator.onDestroy()
        super.onDestroy()
    }

    override fun onResume() {
        super.onResume()
        activityViewModel.navigator.setTarget(navigator)
    }

    override fun onPause() {
        super.onPause()
        activityViewModel.navigator.setTarget(navigator)
    }

    override fun notifyScreenUpdates() {
        navigator.notifyScreenUpdates()
    }

    override fun getActivityScopeViewModel(): ActivityScopeViewModel = activityViewModel

    private val onBackPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            navigator.onBackPressed()
        }
    }

    private fun authScreenInitializer() : BaseScreen {
        val currentUser = (application as App).auth.currentUser
        return if (currentUser == null)
            EnterFragment.Screen()
        else
            CategoryFragment.Screen()
    }
}