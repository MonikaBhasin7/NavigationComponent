package com.hk.navigationcomponent

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment(R.layout.fragment_home) {


    private var savedStateHandle: SavedStateHandle? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        savedStateHandle = findNavController().currentBackStackEntry?.savedStateHandle
        savedStateHandle?.set("LOGIN_TRIED", false)

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



//        btn_navigate_to_login.setOnClickListener {
//            val action = HomeFragmentDirections.actionHomeFragmentToLoginFragment()
//            findNavController().navigate(action)
//        }

        savedStateHandle?.getLiveData<Boolean>("LOGIN_TRIED")
            ?.observe(findNavController().currentBackStackEntry!!) {
                if(it) {
                    Toast.makeText(requireContext(), "Sorry not able to login", Toast.LENGTH_SHORT).show()
                } else {
                    checkUserIsLogedIn().let {
                        when(it) {
                            true -> {
                                val action = HomeFragmentDirections.actionHomeFragmentToProfileFragment()
                                val navOptions: NavOptions = NavOptions.Builder()
                                    .build()

                                findNavController().navigate(
                                    action
                                )
                            }
                            false -> {
                                val action = HomeFragmentDirections.actionHomeFragmentToLoginFragment()
                                val navOptions: NavOptions = NavOptions.Builder()
                                    .build()

                                findNavController().navigate(
                                    action
                                )
                            }
                        }
                    }
                }
            }



    }

    private fun checkUserIsLogedIn(): Boolean {
        return false
    }
}