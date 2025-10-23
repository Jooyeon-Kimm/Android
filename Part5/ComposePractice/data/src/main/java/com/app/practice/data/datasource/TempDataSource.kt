package com.app.practice.data.datasource

import com.app.practice.domain.model.TempModel
import jakarta.inject.Inject

class TempDataSource @Inject constructor(

) {
    fun getTempModel() : TempModel {
        return TempModel("tempModel")

    }
}