package com.edricaazaza.jetpackapp.domain.entity

data class GameResult(
    val isWinner: Boolean,
    val countOfRightAnswers: Int,
    val countOfQuestions: Int,
    val gameSettings: GameSettings
)