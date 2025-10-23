package com.app.practice.presentation.ui.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.practice.domain.model.Category
import com.app.practice.domain.model.Price
import com.app.practice.domain.model.Product
import com.app.practice.domain.model.SalesStatus
import com.app.practice.domain.model.Shop
import com.app.practice.presentation.R
import com.app.practice.presentation.ui.theme.Purple40

@Composable
fun ProductCard(product: Product, onClick: (Product) -> Unit?) {
    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(intrinsicSize = IntrinsicSize.Max)
            .padding(10.dp)
            .shadow(elevation = 10.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(350.dp)
                .padding(10.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start
        ) {
            Image(painter = painterResource(R.drawable.product_image),
                "description",
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentWidth(Alignment.End)
            )
            Text(
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                text = product.shop.shopName,
                modifier = Modifier.padding(top = 10.dp)
            )
            Text(
                fontSize = 14.sp,
                text = product.productName,
            )
            Price(product)
        }
    }

}

@Composable
private fun Price(product: Product) {
    when (product.price.salesStatus) {
        SalesStatus.ON_SALE -> {
            Text(
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                text = "${product.price.originPrice}",
            )
        }
        SalesStatus.ON_DISCOUNT -> {
            Text(
                fontSize = 14.sp,
                style = TextStyle(textDecoration = TextDecoration.LineThrough),
                text = "${product.price.originPrice}",
            )
            Row {
                Text(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    text = "할인가"
                )
                Text(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    color = Purple40,
                    text =  "${product.price.finalPrice}"
                )
            }
        }
        SalesStatus.SOLD_OUT -> {
            Text(
                fontSize = 18.sp,
                color = Color(0xff666666),
                text = "판매종료"
            )
        }
    }
}

@Preview
@Composable
private fun PreviewProductCard() {
    val mockShop = Shop(
        shopId = "1",
        shopName = "샵 이름",
        imageUrl = ""
    )
    val mockPrice = Price(
        originPrice = 30000,
        finalPrice = 30000,
        salesStatus = SalesStatus.ON_SALE
    )
    val mockProduct = Product(
        category = Category.Top,
        productId = "1",
        productName = "상품 이름",
        shop = mockShop,
        isNew = false,
        price = mockPrice,
        isFreeShipping = false,
        imageUrl = ""
    )
    ProductCard(product = mockProduct) { }
}

@Preview
@Composable
private fun PreviewProductCardDiscount() {
    val mockShop = Shop(
        shopId = "1",
        shopName = "샵 이름",
        imageUrl = ""
    )
    val mockPrice = Price(
        originPrice = 30000,
        finalPrice = 20000,
        salesStatus = SalesStatus.ON_DISCOUNT
    )
    val mockProduct = Product(
        category = Category.Top,
        productId = "1",
        productName = "상품 이름",
        shop = mockShop,
        isNew = false,
        price = mockPrice,
        isFreeShipping = false,
        imageUrl = ""
    )
    ProductCard(product = mockProduct) { }
}


@Preview
@Composable
private fun PreviewProductCardSoldOut() {
    val mockShop = Shop(
        shopId = "1",
        shopName = "샵 이름",
        imageUrl = ""
    )
    val mockPrice = Price(
        originPrice = 30000,
        finalPrice = 20000,
        salesStatus = SalesStatus.SOLD_OUT
    )
    val mockProduct = Product(
        category = Category.Top,
        productId = "1",
        productName = "상품 이름",
        shop = mockShop,
        isNew = false,
        price = mockPrice,
        isFreeShipping = false,
        imageUrl = ""
    )
    ProductCard(product = mockProduct) { }
}