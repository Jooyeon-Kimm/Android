package com.example.surface

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.surface.ui.theme.SurfaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SurfaceTheme {
                SurfaceExample(
                    name = "Android",
                )
            }
        }
    }
}

@Composable
fun SurfaceExample(name: String) {
    // STEP 0) 기본
//    Surface(
//        modifier = Modifier.padding(5.dp)
//    ) {
//        Text(
//            text = "Hello $name!",
//            modifier = Modifier.padding(8.dp)
//        )
//    }

    // Surface나 Component가 겹치는 상태로 UI를 만들 수 있는데,
    // 그때, elevation으로 입체감을 넣을 수 있습니다.
    // STEP 1) Surface에 elevation을 설정합니다. (그림자)
//    Surface(
//        modifier = Modifier.padding(5.dp),
//        shadowElevation = 10.dp
//    ) {
//        Text(
//            text = "Hello $name!",
//            modifier = Modifier.padding(8.dp)
//        )
//    }

    // STEP 2) border값을 설정해봅시다.
//    Surface(
//        border = BorderStroke(width = 2.dp, color = Color.Magenta),
//        modifier = Modifier.padding(5.dp),
//        shadowElevation = 10.dp
//    ) {
//        Text(
//            text = "Hello $name!",
//            modifier = Modifier.padding(8.dp)
//        )
//    }

    // STEP 3) Surface의 shape을 설정해봅시다.
//    Surface(
//        border = BorderStroke(width = 2.dp, color = Color.Magenta),
//        modifier = Modifier.padding(5.dp),
//        shadowElevation = 10.dp,
//        shape = CircleShape
//    ) {
//        Text(
//            text = "Hello $name!",
//            modifier = Modifier.padding(8.dp)
//        )
//    }

    // STEP 4) color를 지정합시다.
    // MaterialTheme.colorScheme에서
    // primary, error, background, surface, secondary 등을 지정해봅시다.
    // color를 지정하고 contentColor를 미지정하면,
    // contentColor가 자동으로 선택됩니다.
    Surface(
        border = BorderStroke(width = 2.dp, color = Color.Magenta),
        modifier = Modifier.padding(5.dp),
        shadowElevation = 10.dp,
        shape = CircleShape,
        color = MaterialTheme.colorScheme.error
    ) {
        Text(
            text = "Hello $name!",
            modifier = Modifier.padding(8.dp)
        )
    }


}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    SurfaceTheme {
        SurfaceExample("Android")
    }
}