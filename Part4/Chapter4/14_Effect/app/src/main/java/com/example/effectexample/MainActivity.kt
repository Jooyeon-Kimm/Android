package com.example.effectexample

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import com.example.effectexample.ui.theme.EffectExampleTheme

/*
    필요에 따라서 무언가를 하고 나중에 정리하는 패턴을 사용할 때
    disposal effect와 함께 onDispose를 사용할 수 있습니다.

    컴포즈 화면이 나타날 때 스낵바 띄우고,
    Lifecycle의 시작/중지에 따라 로그를 찍고,
    나중에 화면이 사라질 땐 옵저버를 정리하는 코드
 */

class MainActivity : ComponentActivity() {
    // {onCreate}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EffectExampleTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    EffectExample()
                }
            }
        }
    }
}

@Composable
fun EffectExample(lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current) {
    val snackbarHostState = remember { SnackbarHostState() }

    // STEP 1) `LaunchedEffect`를 이용해서 스낵바를 이용해 봅시다.
    // 파라미터에는 `scaffoldState.snackbarHostState`를 전달하고,
    // "헬로 컴포즈!"라고 출력합시다.
    // `LaunchedEffect`는 `CoroutineScope`를 만들기 때문에
    // 스코프를 별도로 만들 필요는 없습니다.

    // 화면이 처음 보여질 때(또는 key가 바뀔 때) 코루틴을 실행할 수 있게 해주는 Compose 함수
    // 이 UI가 뜨면 "헬로 컴포즈"라는 알림을 띄워줍니다.

    // LaunchedEffect(Unit)
    // 만약 인자로 Unit을 전달해주면, Composasble이 Composition에 진입할 때 항 상 한 번 실행됩니다.
    // 즉, 앱이 다시 실행될 때마다, 또는 화면이 recomposition될 때마다 스낵바가 뜨게 됩니다...
    LaunchedEffect(snackbarHostState) {
        // snackbarHostState :  스낵바(Snackbar)의 상태를 관리하는 객체
        //                      Compose 버전의 Toast/알림창 같은 거
        //                      스낵바를 띄우기 위한 도우미
        snackbarHostState.showSnackbar("헬로 컴포즈")
    }

    // STEP 2) `DisposableEffect`를 호출하고
    // 파라미터로 `lifecycleOwner`를 전달합니다.

    // `LifecycleEventObserver`를 상속받고 여러 상태에 대해 로그를 남깁니다.
    // `Lifecycle.Event.ON_START`, `Lifecycle.Event.ON_STOP` 등

    // DisposableEffect	: 어떤 자원을 등록할 때 사용합니다. (예: 옵저버, 센서, 리시버 등)
    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            // SAM: Single Abstract Method
            when(event) {
                Lifecycle.Event.ON_START -> {
                    Log.d("이펙트", "ON_START")
                }
                Lifecycle.Event.ON_STOP -> {
                    Log.d("이펙트", "ON_STOP")
                }
                Lifecycle.Event.ON_PAUSE -> {
                    Log.d("이펙트", "ON_PAUSE")
                }
                Lifecycle.Event.ON_RESUME -> {
                    Log.d("이펙트", "ON_RESUME")
                }
                else -> {
                    Log.d("이펙트", "ELSE")
                }
            }
        }

        // 블록 내에서 `lifecycleOwner.lifecycle.addObserver`로 옵저버를 추가하고
        // onDispose에서 옵저버를 제거합니다.
        // onDispose : 화면에서 사라질 때, 등록했던 걸 깨끗하게 정리하는 곳
        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }




    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        }
    ) { it: PaddingValues ->
        Column(
            modifier = Modifier.fillMaxSize().padding(it)
        ) {

        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    EffectExampleTheme {
        EffectExample()
    }
}

/*
[비유로 정리]
    SnackbarHostState   : "스낵바 메니저"
    LaunchedEffect      : "컴포즈가 보이면 한 번 실행해!"
    DisposableEffect    : "화면이 보일 때 뭔가 등록하고, 안 보일 때 정리해!"
                           등록한 자원을 화면이 사라질 때 안전하게 해제할 수 있도록 도와주는 도구
    LifecycleOwner      : "생명주기(START, STOP)를 알려주는 사람"
 */