package com.edricaazaza.jetpackapp.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.edricaazaza.jetpackapp.R
import com.edricaazaza.jetpackapp.databinding.FragmentWelcomeBinding
import com.edricaazaza.jetpackapp.presentation.MainActivity.Companion.FRAGMENT_CONTAINER_MAIN

class WelcomeFragment : Fragment() {

    private var _binding : FragmentWelcomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentWelcomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.startBtn.setOnClickListener {
            parentFragmentManager
                .beginTransaction()
                .addToBackStack(this::class.java.simpleName.toString())
                .replace(FRAGMENT_CONTAINER_MAIN, HomeFragment())
                .commit()
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}