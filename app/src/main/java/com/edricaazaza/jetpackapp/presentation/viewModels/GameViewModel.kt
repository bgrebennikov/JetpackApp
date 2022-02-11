package com.edricaazaza.jetpackapp.presentation.viewModels

import android.app.Application
import android.os.CountDownTimer
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.edricaazaza.jetpackapp.R
import com.edricaazaza.jetpackapp.data.GameRepositoryImpl
import com.edricaazaza.jetpackapp.domain.entity.GameResult
import com.edricaazaza.jetpackapp.domain.entity.GameSettings
import com.edricaazaza.jetpackapp.domain.entity.Level
import com.edricaazaza.jetpackapp.domain.entity.Question
import com.edricaazaza.jetpackapp.domain.usecases.GenerateQuestionUseCase
import com.edricaazaza.jetpackapp.domain.usecases.GetGameSettingsUseCase

class GameViewModel(application: Application) : AndroidViewModel(application) {

    val context = application

    private val repository = GameRepositoryImpl

    private val generateQuestionUseCase = GenerateQuestionUseCase(repository)
    private val getGameSettingsUseCase = GetGameSettingsUseCase(repository)

    private lateinit var level: Level
    private lateinit var gameSettings: GameSettings

    private lateinit var timer: CountDownTimer

    private val _formattedTime = MutableLiveData<String>()
    val formattedTime: LiveData<String> get() = _formattedTime

    private val _question = MutableLiveData<Question>()
    val question: LiveData<Question> get() = _question

    private val _percentOfRightAnswers = MutableLiveData<Int>()
    val percentOfRightAnswers: LiveData<Int> get() = _percentOfRightAnswers

    private val _progressAnswersText = MutableLiveData<String>()
    val percentAnswersText: LiveData<String> get() = _progressAnswersText

    private val _enoughCountOfRightAnswers = MutableLiveData<Boolean>()
    val enoughCountOfRightAnswers: LiveData<Boolean> get() = _enoughCountOfRightAnswers

    private val _minPercent = MutableLiveData<Int>()
    val minPercent: LiveData<Int> get() = _minPercent

    private val _gameResult = MutableLiveData<GameResult>()
    val gameResult: LiveData<GameResult> get() = _gameResult


    private fun startGame(level: Level) {
        this.level = level
        this.gameSettings = getGameSettingsUseCase(level)

        generateQuestion()
        _minPercent.value = gameSettings.minPercentOfRightAnswers
    }

    private fun startTimer() {
        timer = object : CountDownTimer(
            gameSettings.gameTimeInSeconds * MILLIS_IN_SECOND,
            MILLIS_IN_SECOND
        ) {
            override fun onTick(p0: Long) {
                _formattedTime.value = formatTime(p0)
            }

            override fun onFinish() {
                _gameResult.value = GameResult(
                    isWinner = enoughCountOfRightAnswers.value == true,
                    countOfRightAnswers,
                    countOfQuestions,
                    gameSettings
                )
            }

        }

        timer.start()
    }

    private fun generateQuestion() {
        _question.value = generateQuestionUseCase(gameSettings.maxSumValue)
    }


    private var countOfRightAnswers = 0
    private var countOfQuestions = 0

    fun chooseAnswer(number: Int) {
        val rightAnswer = question.value?.rightAnswer
        if (number == rightAnswer) {
            countOfRightAnswers++
        }
        countOfQuestions++
        generateQuestion()
        updateProgress()
    }

    private fun updateProgress() {
        val percent = calculatePercentOfRightAnswers()
        _percentOfRightAnswers.value = percent
        _progressAnswersText.value = String.format(
            context.resources.getString(R.string.progress_answers),
            countOfRightAnswers, gameSettings.minCountOfRightAnswers
        )

        _enoughCountOfRightAnswers.value =
            countOfRightAnswers >= gameSettings.minCountOfRightAnswers

    }

    private fun calculatePercentOfRightAnswers(): Int {
        return ((countOfRightAnswers / countOfQuestions.toDouble()) * 100).toInt()
    }


    private fun formatTime(millisUntilFinished: Long): String {
        val seconds = millisUntilFinished / MILLIS_IN_SECOND
        val minutes = seconds / SECONDS_IN_MINUTE
        val leftSeconds = seconds - (minutes * SECONDS_IN_MINUTE)
        return String.format("%02d:%02d", minutes, leftSeconds)
    }

    override fun onCleared() {
        super.onCleared()
        timer.cancel()
    }

    companion object {
        private const val MILLIS_IN_SECOND = 1000L
        private const val SECONDS_IN_MINUTE = 60
    }


}