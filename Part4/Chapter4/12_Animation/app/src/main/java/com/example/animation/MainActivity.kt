package com.example.animation

import android.graphics.Paint.Align
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.animation.ui.theme.AnimationTheme

/*
    animate{}AsState
 */
class MainActivity : ComponentActivity() {
    // {onCreate}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AnimationTheme {
                Column (
                    modifier = Modifier
                        .fillMaxSize()
                        .wrapContentSize(Alignment.Center)
                ) {
                    AnimationExample()
                }
            }
        }
    }
}

// 실험적인 애니메이션을 사용하고 싶을 떄는, OptIn()
// @OptIn(ExperimentalAnimationApi::class)
@Composable
fun AnimationExample() {
    var helloWorldVisible by remember { mutableStateOf(value = true) }
    var isRed by remember { mutableStateOf(value = false) }

//    val backgroundColor = Color.LightGray
    // STEP 4) `backgroundColor`를 `animateColorAsState`로
    // 변경하십시오.
    // `targetValue`는 `isRed`에 따라 `Color`를 설정합니다.
    val backgroundColor by animateColorAsState(
        targetValue = if (isRed) Color.Red else Color.White
    )

    /* Hello World */
    Column(
        modifier = Modifier
            .padding(16.dp)
            .background(backgroundColor)
    ) {
        // Text(text = "Hello World!")
        AnimatedVisibility(
            // STEP 1) `Text`를 `AnimatedVisibility`로 감싸고 `visible`을
            // `helloWorldVisible`로 지정해봅시다.
            visible = helloWorldVisible,

            // STEP 2) `enter` 파라미터를 바꾸어봅시다.
            // 예:
            // `expandHorizontally()`
            // `scaleIn()`
            // `slideInHorizontally()`
            // `fadeIn()`
            enter = scaleIn(),
            exit = scaleOut()
        ) {
            Text(text = "Hello World!")
        }

        Row(
            Modifier.selectable(
                selected = helloWorldVisible,
                onClick = {
                    helloWorldVisible = true
                }
            ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            RadioButton(
                selected = helloWorldVisible,
                onClick = { helloWorldVisible = true }
            )
            Text(
                text = "Hello World 보이기"
            )
        }
        Row(
            Modifier.selectable(
                selected = helloWorldVisible,
                onClick = {
                    helloWorldVisible = false
                }
            ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            RadioButton(
                selected = !helloWorldVisible,
                onClick = { helloWorldVisible = false }
            )
            Text(
                text = "Hello World 감추기"
            )
        }

        /* 색 변경 */
        Text("색을 바꿉니다.")
        Row(
            Modifier.selectable(
                selected = !isRed,
                onClick = {
                    isRed = false
                }
            ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            RadioButton(
                selected = !isRed,
                onClick = { isRed = false }
            )
            Text(
                text = "흰색"
            )
        }
        Row(
            Modifier.selectable(
                selected = isRed,
                onClick = {
                    isRed = true
                }
            ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            RadioButton(
                selected = isRed,
                onClick = { isRed = true }
            )
            Text(
                text = "빨간색"
            )
        }


        // STEP 3) `enter` 값을 덧셈으로 결합해봅시다.
        // `exit` 도 적절한 값을 설정해봅시다.


    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AnimationTheme {
        AnimationExample()
    }
}