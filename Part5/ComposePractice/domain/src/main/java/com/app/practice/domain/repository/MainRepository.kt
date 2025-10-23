package com.app.practice.domain.repository

import com.app.practice.domain.model.Product

interface MainRepository {
    fun getProductList() : List<Product>
}