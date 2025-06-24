package com.example.composepractice

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composepractice.ui.theme.ComposePracticeTheme

class MainActivity : ComponentActivity() {

    // {onCreate}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposePracticeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    // STEP 0) 기본
//    Text(
//        text = "Hello $name!",
//        modifier = modifier
//    )

    // STEP 1) 색상을 지정하기 위해, color 파라미터에 Color.Red를 전달합니다.
//    Text(
//        color = Color.Red,
//        text = "Hello $name!",
//        modifier = modifier
//    )

    // STEP 2) Color 객체를 이용해서, 해쉬값으로 색상을 전달합니다. (ARGB 순서)
//    Text(
//        color = Color(0xffff9944),
//        text = "Hello $name!",
//        modifier = modifier
//    )

    // STEP 3) fontSize 파라미터에 30.sp를 전달합니다.
//    Text(
//        color = Color(0xffff9944),
//        text = "Hello $name!",
//        modifier = modifier,
//        fontSize = 30.sp
//    )

    // STEP 4) fontWeight에 FontWeight.Bold를 전달합니다.
//    Text(
//        color = Color(0xffff9944),
//        text = "Hello $name!",
//        modifier = modifier,
//        fontSize = 30.sp,
//        fontWeight = FontWeight.Bold
//    )

    // STEP 5) fontFamily에 FontFamily.Cursive를 전달합니다.
//    Text(
//        color = Color(0xffff9944),
//        text = "Hello $name!",
//        modifier = modifier,
//        fontSize = 30.sp,
//        fontWeight = FontWeight.Bold,
//        fontFamily = FontFamily.Cursive
//    )

    // STEP 6) letterSpacing에 2.sp를 지정합니다.
//    Text(
//        color = Color(0xffff9944),
//        text = "Hello $name!",
//        modifier = modifier,
//        fontSize = 30.sp,
//        fontWeight = FontWeight.Bold,
//        fontFamily = FontFamily.Cursive,
//        letterSpacing = 2.sp
//    )

    // STEP 7) maxLines를 2로 지정하고 문자열을 추가합니다.
//    Text(
//        color = Color(0xffff9944),
//        text = "Hello $name!\nHello $name!\nHello $name!",
//        modifier = modifier,
//        fontSize = 30.sp,
//        fontWeight = FontWeight.Bold,
//        fontFamily = FontFamily.Cursive,
//        maxLines = 2
//    )

    // STEP 7) textDecoration에 TextDecoration.Underline을 추가합니다.
//    Text(
//        color = Color(0xffff9944),
//        text = "Hello $name!\nHello $name!\nHello $name!",
//        modifier = modifier,
//        fontSize = 30.sp,
//        fontWeight = FontWeight.Bold,
//        fontFamily = FontFamily.Cursive,
//        maxLines = 2,
//        textDecoration = TextDecoration.Underline
//    )

    // STEP 8) textAlign을 TextAlign.Center로 지정
    // modifier = Modifier.width(200.dp)나
    // modifier = Modifier.size(200.dp)로 설정해서 충분히 넓혀줍니다.
    Text(
        color = Color(0xffff9944),
        text = "Hello $name!\nHello $name!\nHello $name!",
        modifier = Modifier.width(200.dp),
        fontSize = 30.sp,
        fontWeight = FontWeight.Bold,
        fontFamily = FontFamily.Cursive,
        maxLines = 2,
        textDecoration = TextDecoration.Underline,
        textAlign = TextAlign.Center
    )

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ComposePracticeTheme {
        Greeting("Android")
    }
}