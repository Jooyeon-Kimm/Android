package com.example.statehoisting

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.statehoisting.ui.theme.StateHoistingTheme
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment

/*
    [ State & State Hoisting ]

    1. MutableState는 상태를 저장하는 객체입니다.
    Compose에서는 mutableStateOf(value)로 상태를 만들고, 이를 UI에서 관찰합니다.
    이 상태가 변경되면 Composable 함수는 자동으로 recomposition됩니다.

    2. remember는 상태를 Composable 내부에 기억시킵니다. (메모리에 캐싱)
    remember { mutableStateOf(...) } 는 Composable이 다시 호출되어도 상태를 유지합니다.
    단, 앱의 구성 변경(Configuration Change) (예: 화면 회전) 시에는 이 값이 사라집니다.

    3. 상태가 바뀌지 않으면 Composable은 재구성되지 않습니다.
    Compose는 성능을 위해 상태가 변경될 때만 UI를 다시 그립니다.
    따라서 UI를 바꾸려면 상태값을 반드시 변경해야 한다.

    4. 구성 변경에도 상태를 유지하려면 rememberSaveable을 써야 합니다.
    rememberSaveable은 내부적으로 Bundle에 값을 저장해 구성 변경 후에도 복원합니다.
    하지만 저장할 수 있는 데이터는 제한적입니다.
   (기본 타입, Parcelable, Serializable 등만 가능)

    5. 모든 값을 rememberSaveable로 저장하면 안 됩니다.
    그 이유는, 시스템에 의해 제공되는 저장 공간(Bundle)은 용량에 제한이 있기 때문입니다.
    너무 많은 데이터를 rememberSaveable로 저장하면 앱이 느려지거나 충돌할 수 있습니다.
    따라서 정말 필요한 값만 저장하는 것이 좋습니다.
    예: 사용자가 입력한 텍스트, 선택한 탭 위치 등

    정리 문장
    Compose에서는 remember로 상태를 메모리에 기억하고,
    구성 변경에도 유지해야 할 값은 rememberSaveable을 사용합니다.
    하지만 저장 공간에는 제한이 있기 때문에
    꼭 필요한 값에만 rememberSaveable을 쓰는 것이 좋습니다.

    [ State Hoisting ]
    가능한 한, 상태가 전달되는 범위를 좁혀야 합니다.
    상태가 전달되는 범위를 좁히기 위해서
    상태를 윗단으로 끌어올리는 것을 State Hoising 이라고 합니다.
 */

class MainActivity : ComponentActivity() {
    // {onCreate}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            StateHoistingTheme {
                var pyeong by remember {
                    mutableStateOf("23")
                }

                var squaremeter by remember {
                    mutableStateOf((23 * 3.306).toString())
                }
                Column (
                    modifier = Modifier.fillMaxSize()
                        .wrapContentSize(Alignment.Center),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    PyeongToSquareMeterWithStateHoisting(
                        pyeong = pyeong,
                        squareMeter = squaremeter,
                        onPyeongChange = {
                            if (it.isBlank()) {
                                pyeong = ""
                                squaremeter = ""
                                return@PyeongToSquareMeterWithStateHoisting
                            }
                            val numericValue = it.toFloatOrNull() ?: return@PyeongToSquareMeterWithStateHoisting
                            pyeong = it.toString()
                            squaremeter = (numericValue * 3.306).toString()
                        },
                        onSquareMeterChange = {
                            if (it.isBlank()) {
                                pyeong = ""
                                squaremeter = ""
                                return@PyeongToSquareMeterWithStateHoisting
                            }
                            val numericValue = it.toFloatOrNull() ?: return@PyeongToSquareMeterWithStateHoisting
                            squaremeter = it
                            pyeong = (numericValue / 3.306).toString()
                        }
                    )
                }
            }
        }
    }
}

// State Hoisting 없이...
@Composable
fun PyeongToSquareMeterWithoutStateHoisting() {
    var pyeong by remember {
        mutableStateOf("23")
    }

    var squaremeter by remember {
        mutableStateOf((23 * 3.306).toString())
    }

    // STEP 1) remember를 이용해 상태를 만들고 값을 입력하면
    // 제곱미터가 출력되도록 화면을 구성합시다.
    // 직접 제곱미터를 만들기 위해서는 3.306을 곱하면 됩니다.
    Column(modifier = Modifier.padding(16.dp)) {
        OutlinedTextField(
            value = pyeong,
            onValueChange = {
                if (it.isBlank()) {
                    pyeong = ""
                    squaremeter = ""
                    return@OutlinedTextField
                }
                val numericValue = it.toFloatOrNull() ?: return@OutlinedTextField
                pyeong = it
                squaremeter = (numericValue * 3.306).toString()
            },
            label = {
                Text(text = "평")
            }
        )

        OutlinedTextField(
            value = squaremeter,
            onValueChange = {},
            label = {
                Text(text = "제곱미터")
            }
        )
    }
}

// State Hoisting 을 적용...
// Stateless 이기 때문에 (상태가 없기 때문에)
// onValueChange 불가합니다.
// 대신 값 변경시 콜백함수를 전달받습니다. (onPyeongChange)
@Composable
fun PyeongToSquareMeterWithStateHoisting(
    pyeong: String,
    squareMeter: String,
    onPyeongChange: (String) -> Unit,
    onSquareMeterChange: (String) -> Unit,
) {
    Column(modifier = Modifier.padding(16.dp)) {
        OutlinedTextField(
            value = pyeong,
            onValueChange = onPyeongChange,
            label = {
                Text(text = "평")
            }
        )

        OutlinedTextField(
            value = squareMeter,
            onValueChange = onSquareMeterChange,
            label = {
                Text(text = "제곱미터")
            }
        )
    }


}


@Preview(showBackground = true)
@Composable
fun WithoutStateHoistingPreview() {
    StateHoistingTheme {
        PyeongToSquareMeterWithoutStateHoisting()
    }
}

@Preview(showBackground = true)
@Composable
fun WithStateHoistingPreview() {
    StateHoistingTheme {
        var pyeong by remember {
            mutableStateOf("23")
        }

        var squaremeter by remember {
            mutableStateOf((23 * 3.306).toString())
        }
        PyeongToSquareMeterWithStateHoisting(
            pyeong = pyeong,
            squareMeter = squaremeter,

            // 복잡한 로직의 경우, 람다를 함수로 분리하여 사용할 수 있습니다.
            onPyeongChange = {
                handlePyeongChange(it.toString()) { newPyeong, newSqm ->
                    pyeong = newPyeong
                    squaremeter = newSqm
                }
            },

            // 간단한 로직이라면, 람다를 인라인으로 직접 작성해도 괜찮습니다.
            onSquareMeterChange = {
                if (it.isBlank()) {
                    pyeong = ""
                    squaremeter = ""
                    return@PyeongToSquareMeterWithStateHoisting
                }
                val numericValue = it.toFloatOrNull() ?: return@PyeongToSquareMeterWithStateHoisting
                squaremeter = it
                pyeong = String.format("%.2f", numericValue / 3.306)
            }
        )
    }
}

fun handlePyeongChange(
    input: String,
    onUpdate: (newPyeong: String, newSqm: String) -> Unit
) {
    if (input.isBlank()) {
        onUpdate("", "")
        return
    }

    val num = input.toFloatOrNull() ?: return
    val sqm = String.format("%.2f", num * 3.306)
    onUpdate(input, sqm)
}

