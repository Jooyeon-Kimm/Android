package com.example.boxwithconstraints

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import com.example.boxwithconstraints.ui.theme.BoxWithConstraintsTheme
import kotlin.math.min

// width/height가 얼마 이상일 때, 어떤 컴포넌트를 넣겠다
// 하는 경우에 BoxConstraints를 사용합니다.

class MainActivity : ComponentActivity() {
    // {onCreate}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BoxWithConstraintsTheme {
                Outer()
            }
        }
    }
}

// 계층형으로 다른 함수 호출
@Composable
fun Outer() {
    // STEP 4) Column에 width를 지정해서 제한해봅시다.
    Column {
        // STEP 2) Inner의 인자로 Modifier.widthIn(min = 100.dp)를 전달해봅시다.
        // heightIn도 전달해봅시다.
        // 각 인자의 max값도 전달해봅시다.
        Inner(modifier = Modifier
            .widthIn(min = 100.dp, max = 350.dp)
            .heightIn(min = 50.dp, max = 300.dp)
        )
        Inner(modifier = Modifier
            .widthIn(min = 100.dp, max = 200.dp)
            .heightIn(min = 50.dp, max = 300.dp)
        )
    }
}

// STEP 1) Inner 인자로 modifier를 전달해봅시다. 기본값을 Modifier로 지정합니다.
// 파라미터로 받은 modifier를 BoxWithConstraints에 전달합니다.
@Composable
private fun Inner(modifier: Modifier = Modifier) {
    BoxWithConstraints(modifier) {// BoxWithConstraintsScope
        // STEP 3) maxWidth 값이 200dp가 넘을 때만, 추가로 Text를 출력해봅시다.
        if(maxWidth > 200.dp) {
            Text(
                text = "Pretty Long Width Here.",
                modifier = Modifier.align(Alignment.BottomCenter)
            )
        }
        Text("maxW:${maxWidth.value.toInt()} maxH:${maxHeight.value.toInt()} minW:${minWidth.value.toInt()} maxH:${maxHeight.value.toInt()}")
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    BoxWithConstraintsTheme {
        Outer()
    }
}