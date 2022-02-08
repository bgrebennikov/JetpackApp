package com.edricaazaza.jetpackapp.domain.repository

import com.edricaazaza.jetpackapp.domain.entity.GameSettings
import com.edricaazaza.jetpackapp.domain.entity.Level
import com.edricaazaza.jetpackapp.domain.entity.Question

interface GameRepository {

    fun generateQuestion(sumMaxValue: Int, countOfOptions: Int): Question

    fun getGameSettings(level: Level): GameSettings

}