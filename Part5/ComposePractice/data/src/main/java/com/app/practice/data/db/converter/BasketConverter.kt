package com.app.practice.data.db.converter

import android.R.attr.category
import androidx.room.TypeConverter
import com.app.practice.domain.model.Category
import com.app.practice.domain.model.Price
import com.app.practice.domain.model.Shop
import com.google.gson.GsonBuilder

class BasketConverter {
    private val gson = GsonBuilder().create()

    @TypeConverter
    fun fromPrice(price: Price): String {
        return gson.toJson(price)
    }

    @TypeConverter
    fun toPrice(value: String): Price {
        return gson.fromJson(value, Price::class.java)
    }

    @TypeConverter
    fun fromCategory(category: Category): String {
        return gson.toJson(category)
    }

    @TypeConverter
    fun toCategory(value: String): Category {
        return gson.fromJson(value, Category::class.java)
    }

    @TypeConverter
    fun fromShop(shop: Shop): String {
        return gson.toJson(shop)
    }

    @TypeConverter
    fun toShop(value: String): Shop {
        return gson.fromJson(value, Shop::class.java)
    }


}