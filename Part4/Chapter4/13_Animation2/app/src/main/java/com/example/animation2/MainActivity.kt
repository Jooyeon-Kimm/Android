package com.example.animation2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.Crossfade
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.animation2.ui.theme.Animation2Theme

/*
    SlotAPI를 제공하고 있는 Composable 함수는
    마지막이 content()인 경우가 많습니다.
    예) Column, Row

    content: @Composable () -> Unit
    content는 Composable 함수를 "슬롯처럼" 전달할 수 있도록 하는 인자입니다.
 */

class MainActivity : ComponentActivity() {
    // {onCreate}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Animation2Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.White
                ) {
                    Animation2Example()
                }
            }
        }
    }
}

/*
    animateColorAsState, animateFloatAsState 처럼 각각 지정하면
    관리가 쉽지 않습니다. 따라서, 쉬운 관리를 위해
    updateTransition을 통해 애니메이션들을 transition으로 묶겠습니다.
 */
@Composable
fun Animation2Example() {
    var isDarkMode by remember { mutableStateOf(false) }
    // STEP 1) `updateTransition` 수행하고 `targetState`를 `isDarkMode`로
    // 설정합니다. `transition`으로 리턴을 받습니다.
    val transition = updateTransition(
        targetState = isDarkMode,
        label = "다크 모드 트랜지션" // 디버깅할 때 유용
    )


    // STEP 2) `transition`에 대해 `animateColor`를 호출해 `backgroundColor`를 받습니다.
    // 배경색상을 만드세요. false일 때 하얀 배경, true일 때 검은 배경.
    // = 하면 State<Color>를 가져오는데
    // by하면 Color를 property로 가져옵니다.
    val backgroundColor by transition.animateColor(label = "다크 모드 배경 색상 애니메이션") { state ->
        when (state) {
            false -> Color.White
            true -> Color.Black
        }
    }

    // STEP 3) 글자 색상을 만드세요.
    val textColor by transition.animateColor(label = "다크 모드 글자 색상 애니메이션") { state ->
        when (state) {
            false -> Color.Black
            true -> Color.White
        }
    }


    // STEP 4) `animateFloat`를 호출해서 알파 값을 만드세요.
    val alpha by transition.animateFloat(label = "다크 모드 알파 애니메이션") { state ->
        when (state) {
            false -> 0.7f
            true -> 1f
        }
    }


    // STEP 5) 컬럼에 배경과 알파를 적용합시다.
    Column(
        modifier = Modifier
            .background(backgroundColor)
            .alpha(alpha)
    ) {
        // STEP 6) 라디오 버튼에 글자 색을 적용합시다.
        RadioButtonWithText(text = "일반 모드", color = textColor, selected = !isDarkMode) {
            isDarkMode = false
        }

        RadioButtonWithText(text = "다크 모드", color = textColor, selected = isDarkMode) {
            isDarkMode = true
        }
//        Row {
//            Box(
//                modifier = Modifier
//                    .background(Color.Red)
//                    .size(100.dp)
//            ) {
//                Text("1")
//            }
//            Box(
//                modifier = Modifier
//                    .background(Color.Magenta)
//                    .size(100.dp)
//            ) {
//                Text("2")
//            }
//            Box(
//                modifier = Modifier
//                    .background(Color.Blue)
//                    .size(100.dp)
//            ) {
//                Text("3")
//            }
//        }
        Crossfade(targetState = isDarkMode) {state->
            when(state) {
                false -> {
                    Column {
                        Box(
                            modifier = Modifier
                                .background(Color.Red)
                                .size(100.dp)
                        ) {
                            Text("1")
                        }
                        Box(
                            modifier = Modifier
                                .background(Color.Magenta)
                                .size(100.dp)
                        ) {
                            Text("2")
                        }
                        Box(
                            modifier = Modifier
                                .background(Color.Blue)
                                .size(100.dp)
                        ) {
                            Text("3")
                        }
                    }
                }
                true -> {
                    Row {
                        Box(
                            modifier = Modifier
                                .background(Color.Red)
                                .size(100.dp)
                        ) {
                            Text("A")
                        }
                        Box(
                            modifier = Modifier
                                .background(Color.Magenta)
                                .size(100.dp)
                        ) {
                            Text("B")
                        }
                        Box(
                            modifier = Modifier
                                .background(Color.Blue)
                                .size(100.dp)
                        ) {
                            Text("C")
                        }
                    }
                }

            }

        }
    }
// STEP 7) Crossfade를 이용해 `isDarkMode`가 참일 경우
// `Row`로 항목을 표현하고 거짓일 경우 `Column`으로 표현해봅시다.

}


@Composable
fun RadioButtonWithText(
    text: String,
    color: Color = Color.Black,
    selected: Boolean,
    onClick: () -> Unit,
//    content: @Composable ()-> Unit // UI적으로 완결되었다고 볼 수 있으므로, content를 인자로 받지 않겠습니다.
) {
    // Row에 있는 onClick 이랑
    // RadioButton에 있는 onClick 이랑 같습니다.

    /*
     질문) Row에 onClick을 설정해주었으니
     RadioButton의 onClick은 비워두어도 되지 않을까요?

     대답) 아니오. RadioButton 자체가 이벤트를 가로채기 때문에
     (다시 말해, RadioButton 이 자체적으로 터치 이벤트를 소비하기 때문에)
     Row 혼자에 onClick을 설정해주어도
     RadioButton에서 인식하지 않습니다.

     정리)
     구조 :
     Row (전체 영역) {
        RadioButton (작은 부분)
        Text
     }

     기대하는 동작 :
     Text        클릭 → 선택됨 (Row에 onClick이 있으므로)
     RadioButton 클릭 → 선택됨 (Row에 onClick이 있으므로)

     실제 동작    :
     Text        클릭 → 선택됨 (Row의 onClick이 호출됨)
     RadioButton 클릭 → 아무 일도 안 일어남

     이유) RadioButton이 터치를 자기 안에서 소비하고
     Row로 터치가 전달되지 않기 때문

     결론) Row, RadioButton 둘 다 onClick이 필요
     */

    Row(
        modifier = Modifier.selectable(
            selected = selected,
            onClick = onClick
        ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(
            selected = selected,
            onClick = onClick
        )
        Text(
            text = text,
            color = color
        )
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Animation2Theme {
        Animation2Example()
    }
}

@Preview(showBackground = true)
@Composable
fun RadioButtonWithTextPreview() {
    Animation2Theme {
        RadioButtonWithText(
            text = "라디오 버튼",
            color = Color.Red,
            selected = true,
            onClick = {}
        )
    }
}