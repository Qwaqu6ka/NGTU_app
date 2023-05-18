package com.example.ngtu.views.enterfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.foundation.views.BaseFragment
import com.example.foundation.views.BaseScreen
import com.example.foundation.views.screenViewModel
import com.example.ngtu.databinding.FragmentEnterBinding

class EnterFragment : BaseFragment() {

    class Screen : BaseScreen

    override val viewModel by screenViewModel<EnterViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentEnterBinding.inflate(inflater, container, false).also { binding ->
            binding.loginButton.setOnClickListener { viewModel.onLogin() }
            binding.registrationButton.setOnClickListener { viewModel.onRegister() }
        }.root
    }
}