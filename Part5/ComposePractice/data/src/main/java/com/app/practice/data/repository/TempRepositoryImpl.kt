package com.app.practice.data.repository

import com.app.practice.data.datasource.TempDataSource
import com.app.practice.domain.model.TempModel
import com.app.practice.domain.repository.TempRepository
import jakarta.inject.Inject

class TempRepositoryImpl @Inject constructor(
    private val dataSource: TempDataSource
): TempRepository {
    override fun getTempModel(): TempModel {
        return dataSource.getTempModel()
    }
}