package com.edricaazaza.jetpackapp.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.edricaazaza.jetpackapp.R
import com.edricaazaza.jetpackapp.databinding.FragmentGameBinding
import com.edricaazaza.jetpackapp.domain.entity.GameResult
import com.edricaazaza.jetpackapp.domain.entity.GameSettings
import com.edricaazaza.jetpackapp.domain.entity.Level
import com.edricaazaza.jetpackapp.presentation.MainActivity.Companion.FRAGMENT_CONTAINER_MAIN


const val EXTRA_LEVEL = "level"

class GameFragment : Fragment() {

    private var _binding: FragmentGameBinding? = null
    private val binding get() = _binding!!

    private var _level: Level? = null
    private val level get() = _level!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _level = arguments?.getSerializable(EXTRA_LEVEL) as Level

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGameBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvOption1.setOnClickListener {
            runGameOverScreen()
        }

    }

    private fun runGameOverScreen(){
        parentFragmentManager
            .beginTransaction()
            .replace(FRAGMENT_CONTAINER_MAIN, GameResultFragment.newInstance(
                GameResult(
                    true,
                    6,
                    6,
                    GameSettings(
                        6,
                        0,
                        50,
                        60
                    )
                )
            ))
            .addToBackStack(this::class.java.simpleName.toString())
            .commit()
    }

    companion object {

        fun newInstance(level: Level): GameFragment {
            return GameFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(EXTRA_LEVEL, level)
                }
            }
        }

    }


}