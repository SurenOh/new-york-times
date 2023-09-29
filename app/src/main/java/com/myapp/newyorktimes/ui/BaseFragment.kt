package com.myapp.newyorktimes.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.google.android.material.snackbar.Snackbar
import com.myapp.newyorktimes.R

abstract class BaseFragment<VB : ViewBinding> : Fragment() {
    private var _binding: VB? = null
    val binding get() = _binding!!

    abstract fun inflateBinding() : VB

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        if (_binding == null) _binding = inflateBinding()
        return binding.root
    }

    fun navigate(dir: NavDirections) {
        try {
            findNavController().navigate(dir)
        } catch (e: Exception) {
            e.stackTrace
        }
    }

    fun popBackStack() {
        try {
            findNavController().popBackStack()
        } catch (e: Exception) {
            e.stackTrace
        }
    }

    fun internetErrorMessage() {
        val snackBar = Snackbar.make(binding.root, R.string.internet_message, Snackbar.LENGTH_SHORT)
        snackBar.show()
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}