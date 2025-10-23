package com.app.practice.domain.usecase

import com.app.practice.domain.model.TempModel
import com.app.practice.domain.repository.TempRepository
import javax.inject.Inject

class TempUseCase @Inject constructor(
    val repo: TempRepository
) {
    fun getTempModel() : TempModel {
        return repo.getTempModel()
    }
}