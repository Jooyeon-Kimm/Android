package com.example.snackbar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.snackbar.ui.theme.SnackBarTheme
import kotlinx.coroutines.launch

/*
    suspend function은 coroutine scope 안에서만 호출할 수 있는 함수입니다.
    서스펜드 함수는 코루틴 범위 안에서만 호출할 수 있는 함수입니다.
 */

class MainActivity : ComponentActivity() {
    // {onCreate}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SnackBarTheme {
                SnackbarExample()
            }
        }
    }
}

@Composable
fun SnackbarExample() {
    var counter by remember { mutableStateOf(0) }

    // STEP 3) coroutineScope를 만듭시다.
    // `rememberCoroutineScope`를 사용합니다.
    val coroutineScope = rememberCoroutineScope()

    // STEP 1) scaffoldState를 만들고 Scaffold에 설정합시다.
    // scaffoldState를 만들기 위해 `rememberScaffoldState`를 사용합니다. (Material 2)
    // STEP 1) SnackbarHostState를 직접 만들어서 Scaffold에 넣겠습니다. (Material 3)
    val snackbarHostState = remember { SnackbarHostState() }

    // SnackbarHost 위에 마우스를 가져다댄 후, Ctrl+Click을 해봅시다.
    // SnackbarHost 자체가 일정 duration동안 보이고, dismiss되어 사라짐을
    // 코드를 통해 확인할 수 있습니다.
    Scaffold(snackbarHost = { SnackbarHost(hostState = snackbarHostState) }) { paddingValues ->

        Column(
            modifier = Modifier.fillMaxSize().padding(paddingValues),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // STEP 2) "더하기" 버튼을 만들어 봅시다.
            // action없이 counter를 증가시키십시다.
            Button(onClick = {
                counter++
                coroutineScope.launch {
                    val result = snackbarHostState.showSnackbar(
                        message = "카운터는 ${counter}입니다.",
                        actionLabel = "닫기",
                        duration = SnackbarDuration.Short
                    )
                    when (result) {
                        SnackbarResult.Dismissed -> {}
                        SnackbarResult.ActionPerformed -> {}
                    }
                }

            }) {
                Text("더하기")
            }

            // STEP 4) 버튼의 onClick에서 `coroutineScope.launch`를 사용합시다.

            // STEP 5) 스낵바를 사용하기 위해
            // `scaffoldState.snackbarHostState.showSnackbar` 를 사용합니다.

            // `message`에 카운터를 출력합시다.
            // `actionLabel`를 "닫기"로 지정합시다.
            // `duration`에 `SnackbarDuration.Short`를 사용합니다.

            /*
             LaunchedEffect란?
             Composable이 처음 composition될 때, 또는 key가 바뀔 때
             한 번만 실행되는 코루틴 블록 입니다.
             다시 말해, 화면이 다시 composition되지 않거나, key가 변경되지 않으면
             초기 1번 제외하고 다시 실행되지 않습니다.

             기본적으로 부작용이 있는 것들은 LaunchedEffect 내부에서 실행합니다.

             Button : LaunchedEffect X
             Button의 onClick은 클릭 시점이 명확하므로, 직접 실행 가능하기 때문에,
             LaunchedEffect를 사용하지 않습니다.

             Composable : LaunchedEffect O
             Composable은 생명주기에 따라 실행 및 정리를 해야하므로
             LaunchedEffect를 사용합니다.

             정리)
             onClick 내부는, 즉시 실행 이벤트이므로, coroutineScope.launch { } 만 사용하면 됩니다.
             Composable 내부는 자동 실행 조건이 없으면 그냥 실행되면 안 됩니다.
             LaunchedEffect는 Compose가 "언제 실행해야 하는가"를 알 수 있도록 도와줍니다.
             */
            LaunchedEffect(snackbarHostState) {
                coroutineScope.launch {
                    val result = snackbarHostState.showSnackbar(
                        message = "카운터는 ${counter}입니다.",
                        actionLabel = "닫기",
                        duration = SnackbarDuration.Short
                    )
                    when (result) {
                        SnackbarResult.Dismissed -> {}
                        SnackbarResult.ActionPerformed -> {}
                    }
                }
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SnackBarTheme {
        SnackbarExample()
    }
}