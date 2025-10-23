package com.app.practice.data.db.entity

import android.R.attr.data
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.app.practice.data.db.converter.LikeConverter
import com.app.practice.domain.model.Category
import com.app.practice.domain.model.Price
import com.app.practice.domain.model.Product
import com.app.practice.domain.model.Shop

// LIKE 가 SQL에서 예약어(reserved keyword)이기 때문에...
@Entity(tableName = "liked")
@TypeConverters(LikeConverter::class)
data class LikeProductEntity(
    @PrimaryKey
    val productId: String,
    val productName: String,
    val imageUrl: String,
    val price: Price,
    val category: Category,
    val shop: Shop,
    val isNew: Boolean,
    val isFreeShipping: Boolean,
)

fun LikeProductEntity.toDomainModel() : Product {
    return Product(
        productId = productId,
        productName = productName,
        imageUrl = imageUrl,
        price = price,
        category = category,
        shop = shop,
        isNew = isNew,
        isFreeShipping = isFreeShipping
    )
}