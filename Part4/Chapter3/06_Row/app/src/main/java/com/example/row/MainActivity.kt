package com.example.row

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.row.ui.theme.RowTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RowTheme {
                RowExample()
            }
        }
    }
}

// Row는 기본적으로 가로로 배치하지만, align은 세로로 배치 (vertically)
@Composable
fun RowExample() {
    // STEP 0) 기본
//    Row(modifier = Modifier.height(40.dp)) {
//        Text(text = "첫 번째!")
//        Text(text = "두 번째!")
//        Text(text = "세 번째!")
//    }

    // STEP 1) 각 Text의 modifier에 align을 설정합니다.
    // Alignment.Top, CenterVertically, Bottom을 지정해봅니다.
//    Row(modifier = Modifier.height(40.dp)) {
//        Text(
//            text = "첫 번째!",
//            modifier = Modifier.align(Alignment.Top)
//        )
//        Text(
//            text = "두 번째!",
//            modifier = Modifier.align(Alignment.CenterVertically)
//        )
//        Text(
//            text = "세 번째!",
//            modifier = Modifier.align(Alignment.Bottom)
//        )
//    }

    // STEP 2) Row에 verticalAlignment를 설정해봅니다.
    // Text에 align을 사용할 때와 쓰이는 값이 같습니다.
//    Row(
//        verticalAlignment = Alignment.Bottom, // 전체 요소의 align 설정
//        modifier = Modifier.height(40.dp)
//    ) {
//        Text(text = "첫 번째!", modifier = Modifier.align(Alignment.Top))
//        Text(text = "두 번째!")
//        Text(text = "세 번째!")
//    }

    // STEP 3) Row의 height를 200dp 정도로 설정합니다.
    // Row에 horizontalArrangement에 Arrangement.Center를 설정해봅시다.
    // Start, End, SpaceAround, SpaceBetween, SpaceEvenly를 설정해봅시다.
//    Row(
//        horizontalArrangement = Arrangement.SpaceEvenly, // Row 방향 따름
//        verticalAlignment = Alignment.Bottom, // 전체 요소의 align 설정
//        modifier = Modifier.height(40.dp).width(200.dp)
//    ) {
//        Text(text = "첫 번째!", modifier = Modifier.align(Alignment.Top))
//        Text(text = "두 번째!")
//        Text(text = "세 번째!")
//    }

    // STEP 4) horizontalArrangement를 제거하고
    // 각 Text에 Modifier.weight를 설정합니다.
    // 각 항목의 weight 값을 바꾸어 보세요.
    // 3 : 2 : 3의 가로 비율을 가짐
//    Row(
//        verticalAlignment = Alignment.Bottom, // 전체 요소의 align 설정
//        modifier = Modifier.height(40.dp).width(200.dp)
//    ) {
//        Text(
//            text = "첫 번째!",
//            modifier = Modifier
//                .align(Alignment.Top)
//                .weight(3f)
//        )
//        Text(
//            text = "두 번째!",
//            modifier = Modifier.weight(2f)
//        )
//        Text(
//            text = "세 번째!",
//            modifier = Modifier.weight(3f)
//        )
//    }


    // STEP 5) Text 대신 Icon을 하나 넣어봅시다.
    Row(
        verticalAlignment = Alignment.Bottom, // 전체 요소의 align 설정
        modifier = Modifier.height(40.dp).width(200.dp)
    ) {
        Text(
            text = "첫 번째!",
            textAlign = TextAlign.End,
            modifier = Modifier
                .align(Alignment.Top)
                .weight(3f)
                .background(Color.Magenta)
        )
        Icon(
            imageVector = Icons.Filled.AccountBox,
            contentDescription = "추가",
            modifier = Modifier.weight(1f)
                .background(Color.Cyan)
        )
        Text(
            text = "세 번째!",
            textAlign = TextAlign.Center,
            modifier = Modifier.weight(3f)
                .background(Color.Blue)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    RowTheme {
        RowExample()
    }
}