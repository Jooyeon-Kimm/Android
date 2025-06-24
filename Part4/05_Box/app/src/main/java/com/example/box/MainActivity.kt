package com.example.box

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.box.ui.theme.BoxTheme

// Box의 2가지 용도
// 1. Box 자체를 만들기 위해 사용
// 2. Android Framework에 있는, FrameLayout 같이 중첩시키는 용도로 사용
class MainActivity : ComponentActivity() {

    // {onCreate}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BoxTheme {
                BoxExample()
            }
        }
    }
}

@Composable
fun BoxExample() {
    // STEP 0) 기본
//    Box(modifier = Modifier.size(100.dp)) {
//        Text(
//            text = "Hello World",
//            modifier = Modifier.align(Alignment.Center) // TopStart, BottomCetner 등...
//        )
//    }

    // STEP 1) Text 세 개를 Box 안에 배치해봅시다.
//    Box(modifier = Modifier.size(100.dp)) {
//        Text(
//            text = "Compose",
//            modifier = Modifier.align(Alignment.BottomEnd)
//        )
//        Text(
//            text = "Joo",
//            modifier = Modifier.align(Alignment.Center)
//        )
//        Text(
//            text = "Jetpack",
//            modifier = Modifier.align(Alignment.TopStart)
//        )
//    }

    // STEP 2) 2개의 Box를 Box 안에 배치하고
    // 사이즈를 70dp, 색상을 각기 다르게 해봅시다.
//    Box(modifier = Modifier.size(100.dp)) {
//        Box(modifier = Modifier
//            .size(70.dp)
//            .background(Color.Cyan)
//            .align(Alignment.CenterStart)
//        )
//        Box(modifier = Modifier
//            .size(70.dp)
//            .background(Color.Yellow)
//            .align(Alignment.BottomEnd)
//        )
//    }

    // STEP 3) 부모 Box에 modifier 설정을 제거해서 콘텐츠 사이즈만큼 보여주게 합시다.
    // 그리고 첫 번째 자식 Box의 사이즈를 matchParentSize()로 설정하고,
    // 그 다음에는 fillMaxSize()로 설정해봅시다.
    Box {
        Box(modifier = Modifier
            .matchParentSize() // .fillMaxSize() // 가능한 최대 크기로 잡음
            .background(Color.Cyan)
            .align(Alignment.CenterStart)
        )
        Box(modifier = Modifier
            .size(70.dp)
            .background(Color.Yellow)
            .align(Alignment.BottomEnd)
        )
    }

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    BoxTheme {
        BoxExample()
    }
}