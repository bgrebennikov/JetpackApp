package com.edricaazaza.jetpackapp.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.edricaazaza.jetpackapp.R
import com.edricaazaza.jetpackapp.databinding.FragmentHomeBinding
import com.edricaazaza.jetpackapp.domain.entity.Level
import com.edricaazaza.jetpackapp.presentation.MainActivity.Companion.FRAGMENT_CONTAINER_MAIN

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding){
            testLevelItem.setOnClickListener { selectLevel(Level.TEST) }
            easyLevelItem.setOnClickListener { selectLevel(Level.EASY) }
            normalLevelItem.setOnClickListener { selectLevel(Level.NORMAL) }
            hardLevelItem.setOnClickListener { selectLevel(Level.HARD) }
        }

    }

    private fun selectLevel(level: Level){
        parentFragmentManager
            .beginTransaction()
            .replace(FRAGMENT_CONTAINER_MAIN, GameFragment.newInstance(level))
            .addToBackStack(this::class.java.simpleName.toString())
            .commit()
    }

}