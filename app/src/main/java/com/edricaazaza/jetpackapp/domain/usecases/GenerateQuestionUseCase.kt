package com.edricaazaza.jetpackapp.domain.usecases

import com.edricaazaza.jetpackapp.domain.entity.GameSettings
import com.edricaazaza.jetpackapp.domain.entity.Question
import com.edricaazaza.jetpackapp.domain.repository.GameRepository

class GenerateQuestionUseCase(
    private val repository: GameRepository
) {

    operator fun invoke(maxSumValue : Int) : Question{
        return repository.generateQuestion(maxSumValue, COUNT_OF_OPTIONS)
    }

    private companion object {
        const val COUNT_OF_OPTIONS = 6
    }
}