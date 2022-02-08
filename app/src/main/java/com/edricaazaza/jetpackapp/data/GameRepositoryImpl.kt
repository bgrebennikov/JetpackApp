package com.edricaazaza.jetpackapp.data

import com.edricaazaza.jetpackapp.domain.entity.GameSettings
import com.edricaazaza.jetpackapp.domain.entity.Level
import com.edricaazaza.jetpackapp.domain.entity.Question
import com.edricaazaza.jetpackapp.domain.repository.GameRepository
import kotlin.random.Random

object GameRepositoryImpl : GameRepository {

    private const val MIN_SUM_VALUE = 2
    private const val MIN_ANSWER_VALUE = 1

    override fun generateQuestion(sumMaxValue: Int, countOfOptions: Int): Question {
        val sum = Random.nextInt(MIN_SUM_VALUE, sumMaxValue + 1)
        val visibleNumber = Random.nextInt(MIN_ANSWER_VALUE, sum)

        val options = HashSet<Int>()

        while (options.size < 6){
            options.add(
                Random.nextInt(MIN_ANSWER_VALUE, sumMaxValue)
            )
        }

        return Question(
            sum,
            visibleNumber,
            options.toList()
        )

    }

    override fun getGameSettings(level: Level): GameSettings {
        return when (level){
            Level.TEST -> GameSettings(
                maxSumValue = 6,
                minCountOfRightAnswers = 3,
                minPercentOfRightAnswers = 10,
                gameTimeInSeconds = 10
            )

            Level.EASY -> GameSettings(
                maxSumValue = 6,
                minCountOfRightAnswers = 5,
                minPercentOfRightAnswers = 50,
                gameTimeInSeconds = 60
            )

            Level.NORMAL -> GameSettings(
                maxSumValue = 20,
                minCountOfRightAnswers = 20,
                minPercentOfRightAnswers = 80,
                gameTimeInSeconds = 50
            )

            Level.HARD -> GameSettings(
                maxSumValue = 6,
                minCountOfRightAnswers = 30,
                minPercentOfRightAnswers = 90,
                gameTimeInSeconds = 40
            )


        }
    }
}