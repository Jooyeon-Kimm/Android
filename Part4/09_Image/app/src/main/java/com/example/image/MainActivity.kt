package com.example.image

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.image.ui.theme.ImageTheme

// 이 이미지는 정적 이미지
// 이미지를 제대로 사용하기 위해서는, 네트워크를 통해 이미지를 가져올 수 있어야 함
// 위 내용은 Network Image로 학습합시다.
class MainActivity : ComponentActivity() {
    // {onCreate}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ImageTheme {
                ImageExample()
            }
        }
    }
}

@Composable
fun ImageExample() {
    Column {
        // STEP 1) Image를 만들어봅시다.
        // painter 항목에 painterResource(id = R.drawable.wall)
        // contentDescription를 적어줍시다.
        Image(
            painter = painterResource(id = R.drawable.image_hdpi),
            contentDescription = "자연"
        )
        // Image

        // STEP 2) 두 번째 Image를 만들어봅시다.
        // imageVector에 Icons.Filled.Settings를 설정해봅시다.
        Image(
            imageVector = Icons.Filled.Settings,
            contentDescription = "세팅"
        )

        // 일반적으로 비트맵 이미지를 사용하지 않음
        // 비트맵 이미지는 순수한 컴포저블 함수 내에서 진행할 수 없고
        // 컨텍스트가 있는 액티비티 등에서 가져온 다음, 주입해야 합니다.
//        Image(
//            bitmap = 미작성,
//            contentDescription = "비트맵 이미지"
//        )
    }

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ImageTheme {
        ImageExample()
    }
}