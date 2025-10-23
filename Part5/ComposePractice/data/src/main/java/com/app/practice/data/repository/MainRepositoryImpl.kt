package com.app.practice.data.repository

import android.content.Context
import com.app.practice.domain.model.Product
import com.app.practice.domain.repository.MainRepository
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.qualifiers.ApplicationContext
import jakarta.inject.Inject
import java.io.InputStreamReader

class MainRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context,
) : MainRepository {
    override fun getProductList(): List<Product> {
        val inputStream = context.assets.open("product_list.json")
        val inputStreamReader = InputStreamReader(inputStream)
        val jsonString = inputStreamReader.readText()
        val type = object : TypeToken<List<Product>>() {}.type
        return GsonBuilder().create().fromJson(jsonString, type)
    }
}