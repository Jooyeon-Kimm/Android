package com.app.practice.domain.repository

import com.app.practice.domain.model.TempModel

interface TempRepository {
    fun getTempModel() : TempModel
}