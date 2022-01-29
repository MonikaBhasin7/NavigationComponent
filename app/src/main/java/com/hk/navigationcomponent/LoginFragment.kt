package com.hk.navigationcomponent

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment: Fragment(R.layout.fragment_login) {

    val TAG = "LoginFragment"
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        findNavController().previousBackStackEntry?.savedStateHandle?.let {
            it.set("LOGIN_TRIED", false)
        }

        btn_submit.setOnClickListener {
            isLoginSuccessful().let {
                when(it) {
                    true -> {
                        val action = LoginFragmentDirections.actionLoginFragmentToProfileFragment()
                        val navOptions: NavOptions = NavOptions.Builder()
                            .setPopUpTo(R.id.loginFragment, true)
                            .build()
                        findNavController().navigate(action, navOptions)
                    }
                    false -> {
                        findNavController().previousBackStackEntry?.savedStateHandle?.let { savedStateHandle ->
                            savedStateHandle.set("LOGIN_TRIED", true)
                        }
                        findNavController().popBackStack()
                    }
                }
            }
        }
    }

    private fun isLoginSuccessful() : Boolean{
        return false
    }
}