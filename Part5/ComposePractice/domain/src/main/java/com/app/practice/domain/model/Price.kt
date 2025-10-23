package com.app.practice.domain.model

data class Price (
    val originPrice: Int, // 원래 가격
    val finalPrice: Int, // 세일 적용된 가격
    val salesStatus: SalesStatus, // 세일 상태
)