package com.app.practice.data.datasource

import com.app.practice.data.model.TestModelResponse

class TestDataSource {
    fun getTestModelResponse(): TestModelResponse {
        return TestModelResponse("response")
    }
}