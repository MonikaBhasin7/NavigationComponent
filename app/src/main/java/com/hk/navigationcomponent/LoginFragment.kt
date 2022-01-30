package com.hk.navigationcomponent

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavOptions
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment: Fragment(R.layout.fragment_login) {

    val TAG = "LoginFragment"
    private var savedStateHandle: SavedStateHandle? = null


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        savedStateHandle = findNavController().previousBackStackEntry?.savedStateHandle

        btn_submit.setOnClickListener {
            isLoginSuccessful().let {
                when(it) {
                    true -> {
                        val action = LoginFragmentDirections.actionLoginFragmentToProfileFragment(et_name.text.toString())
                        val navOptions: NavOptions = NavOptions.Builder()
                            .setPopUpTo(R.id.loginFragment, true)
                            .build()
                        findNavController().navigate(action, navOptions)
                    }
                    false -> {
                        savedStateHandle?.set("LOGIN_TRIED", true)
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