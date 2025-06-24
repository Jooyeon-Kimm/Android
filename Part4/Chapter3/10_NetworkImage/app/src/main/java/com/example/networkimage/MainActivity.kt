package com.example.networkimage

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import coil.compose.rememberImagePainter
import com.example.networkimage.ui.theme.NetworkImageTheme
// Network Image는 Preview가 잘 작동하지 않기 때문에,
// 애뮬레이터나 실기기에서 실행하여 확인하시길 바랍니다.

// STEP 1) build.gradle.kts (:app)에서 coil 라이브러리 implementation
// STEP 2) AndroidManifest.xml에서 INTERNET 권한 추가

class MainActivity : ComponentActivity() {
    // {onCreate}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NetworkImageTheme {
                CoilExample()
            }
        }
    }
}

@Composable
fun CoilExample() {
    // STEP 3) rememberImagePainter를 이용해 Image의 painter를 설정합니다.
    // (Compose 한국어 문서의 추천, but Deprecated)
    // 이미지 URI
    val imageUri = "https://images.unsplash.com/photo-1503023345310-bd7c1de61c7d?auto=format&fit=crop&w=800&q=80"
//    val painter = rememberImagePainter(data = imageUri)
//    // remember가 붙어있는 것은, 뒤의 내용을 저장했다가
//    // 컴포저블이 재호출될 때, 그 내용을 기억했다가 알려준다는 의미입니다.
//    Image(
//        painter=painter,
//        contentDescription = "무료 이미지"
//    )

    // STEP 4) AsyncImage를 이용해봅시다.
    // model에 주소를 적으면 됩니다.
    Column {
        AsyncImage(
            model = imageUri,
            contentDescription = "무료 이미지"
        )
        AsyncImage(
            model = imageUri,
            contentDescription = "무료 이미지"
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    NetworkImageTheme {
        CoilExample()
    }
}