package com.example.dialog

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.dialog.ui.theme.DialogTheme

class MainActivity : ComponentActivity() {

    // {onCreate}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DialogTheme {
                DialogExample()
            }
        }
    }
}

@Composable
fun DialogExample() {
    var openDialog by remember { mutableStateOf(value = false) }
    var counter by remember { mutableStateOf(0) }

    Column {
        Button(onClick = { openDialog = true }) {
            Text(text = "다이얼로그 열기")
        }
        Text(text = "카운터: $counter")

        if (openDialog) {
            AlertDialog(
                onDismissRequest = {
                    // STEP 1) openDialog를 이용해 다이얼로그를 닫을 수 있게 합니다.
                    openDialog = false
                },
                confirmButton = {
                    // STEP 2) "더하기" 버튼을 만들고 counter를 증가시킵니다.
                    // 다이얼로그도 닫습니다.
                    Button(
                        onClick = {
                            counter++
                            openDialog = false
                        }
                    ) {
                        Text("더하기")
                    }
                },
                dismissButton = {
                    // STEP 3) "취소" 버튼을 만들고 다이얼로그를 닫습니다.
                    Button(
                        onClick = {
                            openDialog = false
                        }
                    ) {
                        Text("취소")
                    }
                },
                title = {
                    // STEP 4) 타이틀을 만듭니다. "더하기" 정도로 해봅시다.
                    Text("더하기")
                },
                text = {
                    // STEP 5) 다이얼로그에서 설명할 문구를 출력합니다.
                    Text("더하기 버튼을 누르면 카운터를 증가시킵니다.\n버튼을 눌러주세요.")
                })
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    DialogTheme {
        DialogExample()
    }
}