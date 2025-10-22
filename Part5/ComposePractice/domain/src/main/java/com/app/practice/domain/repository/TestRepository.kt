package com.app.practice.domain.repository

import com.app.practice.domain.model.TestModel

interface TestRepository {
    fun getTestData(): TestModel
}