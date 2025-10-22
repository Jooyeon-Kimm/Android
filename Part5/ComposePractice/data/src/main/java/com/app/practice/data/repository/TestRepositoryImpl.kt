package com.app.practice.data.repository

import com.app.practice.data.datasource.TestDataSource
import com.app.practice.data.model.toDomainModel
import com.app.practice.domain.model.TestModel
import com.app.practice.domain.repository.TestRepository

class TestRepositoryImpl(val dataSource: TestDataSource) : TestRepository {

    override fun getTestData(): TestModel {
        return dataSource.getTestModelResponse().toDomainModel()
    }
}