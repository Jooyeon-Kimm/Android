package com.example.canvas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.canvas.ui.theme.CanvasTheme
import androidx.compose.ui.graphics.Color

class MainActivity : ComponentActivity() {

    // {onCreate}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CanvasTheme {
                CanvasExample(
                )
            }
        }
    }
}

@Composable
fun CanvasExample() {

    // Canvas는 modifier를 반드시 직접 지정해주어야 합니다.
    Canvas(
        modifier = Modifier.size(20.dp)
    ) {// DrawScope
        // STEP 1) `drawLine`을 사용해봅시다. 파라미터로 색상, 시작 `Offset`,
        // 끝(`Offset` 타입)을 받습니다.
        drawLine(Color.Red, Offset(30f, 10f), Offset(50f, 40f))

        // STEP 2) `drawCircle`을 사용해보세요. 색상, 반지름, 중앙(`Offset`)
        // STEP 3) 아래의 규칙으로 그려진 아이콘 `Icons.Filled.Send`를
        // `drawLine`으로 변경해봅시다.
        drawCircle(color = Color.Yellow, radius = 10f, center = Offset(15f, 30f))
        drawRect(color = Color.Magenta, topLeft = Offset(30f, 30f), size = Size(10f, 10f))

        // ImageVector에서는 한붓 그리기처럼 연속으로 그려집니다.
        // `moveTo`로 2.01f, 21.01f로 이동한 후 거기에서
        // 23.0f, 12.0f로 선이 그어지는 식입니다.

        //     moveTo(2.01f, 21.0f)
        //     lineTo(23.0f, 12.0f)
        //     lineTo(2.01f, 3.0f)
        //     lineTo(2.0f, 10.0f)
        //     lineToRelative(15.0f, 2.0f)
        //     lineToRelative(-15.0f, 2.0f)
        //     close()

        drawLine(color = Color.Green, start = Offset(2.01f, 21.0f), end = Offset(23.0f, 12.0f))
        drawLine(color = Color.Green, start = Offset(23.0f, 12.0f), end = Offset(2.01f, 3.0f))
        drawLine(color = Color.Green, start = Offset(2.01f, 3.0f), end = Offset(2.0f, 10.0f))
        drawLine(color = Color.Green, start = Offset(2.0f, 10.0f), end = Offset(17.0f, 12.0f))
        drawLine(color = Color.Green, start = Offset(17.0f, 12.0f), end = Offset(2.0f, 14.0f))
        drawLine(color = Color.Green, start = Offset(2.0f, 14.0f), end = Offset(2.01f, 21.0f))
    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CanvasTheme {
        CanvasExample()
    }
}