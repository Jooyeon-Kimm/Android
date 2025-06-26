package com.example.bottomappbar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.bottomappbar.ui.theme.BottomAppBarTheme
import kotlinx.coroutines.launch
import java.math.BigDecimal

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BottomAppBarTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    BottomAppBarExample()
                }
            }
        }
    }
}

@Composable
fun BottomAppBarExample() {
    // remember가 붙어있으면, 리멤버함수를 호출하는 유틸리티
    val snackbarHostState = SnackbarHostState() // Material 2
    val coroutineScope = rememberCoroutineScope()
    var counter by remember { mutableStateOf(0) }

    // STEP 1) `Scaffold`에 `scaffoldState`를 설정합니다.
    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        bottomBar = {
            BottomAppBar(){
                Text("안녕")
                Button(onClick = {
                    coroutineScope.launch {
                        snackbarHostState.showSnackbar("안녕하세요.")
                    }
                }) {
                    Text("인사하기")
                }
                Button(onClick = {
                    counter++
                    coroutineScope.launch {
                        snackbarHostState.showSnackbar("$counter 입니다.")
                    }
                }){
                    Text("더하기")
                }
                Button(onClick = {
                    counter--
                    coroutineScope.launch {
                        snackbarHostState.showSnackbar("$counter 입니다.")
                    }
                }){
                    Text("빼기")
                }
            }
        }
    ) {paddingValues ->
        Column (modifier = Modifier.padding(paddingValues)) {
            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                Text(
                    text = "카운터는 ${counter}회입니다.",
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    }
    // STEP 2) `bottomBar` 파라미터에 `BottomAppBar`를 넣읍시다.
    // 내용은 텍스트와 버튼을 넣어 봅시다. 버튼에는 `snackBar`를
    // 연동해 메시지를 출력합니다.
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BottomAppBarTheme {
        BottomAppBarExample()
    }
}