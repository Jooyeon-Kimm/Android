package com.app.practice.domain.usecase

import com.app.practice.domain.model.TestModel
import com.app.practice.domain.repository.TestRepository

class TestUseCase(
    val repo : TestRepository
) {
    fun getTestData() : TestModel {
        return repo.getTestData()
    }
}
