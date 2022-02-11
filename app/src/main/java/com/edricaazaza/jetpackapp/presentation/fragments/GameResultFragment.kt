package com.edricaazaza.jetpackapp.presentation.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.edricaazaza.jetpackapp.databinding.FragmentGameResultBinding
import com.edricaazaza.jetpackapp.domain.entity.GameResult

private const val EXTRA_GAME_RESULT = "game_result"

class GameResultFragment : Fragment() {

    private var _binding: FragmentGameResultBinding? = null
    private val binding get() = _binding!!

    private var _gameResult: GameResult? = null
    private val gameResult get() = _gameResult!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.getParcelable<GameResult>(EXTRA_GAME_RESULT).let {
            _gameResult = it
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGameResultBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding){

            answersRequired.text = gameResult.gameSettings.minCountOfRightAnswers.toString()
            answersScore.text = gameResult.countOfRightAnswers.toString()
            answersPercent.text = gameResult.gameSettings.minPercentOfRightAnswers.toString()

            tryAgainBtn.setOnClickListener {
                goHome()
            }


        }

    }

    private fun goHome(){
        parentFragmentManager.popBackStack(HomeFragment::class.java.simpleName.toString(), FragmentManager.POP_BACK_STACK_INCLUSIVE)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        // handle back button, for navigate to HomeFragment
        val callback: OnBackPressedCallback = object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                goHome()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        _gameResult = null
    }

    companion object {
        fun newInstance(gameResult: GameResult): GameResultFragment {
            return GameResultFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(EXTRA_GAME_RESULT, gameResult)
                }
            }
        }
    }

}