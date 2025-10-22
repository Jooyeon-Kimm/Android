package com.app.practice.composepractice.model

// domain layer에 필요한 것들 모두 MODEL에
data class TestUiModel(
)

fun TestUiModel.toDomainModel() : TestModel {
    return TestModel(this.name)
}