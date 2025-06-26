package com.example.customdialog

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.customdialog.ui.theme.CustomDialogTheme
import androidx.compose.material3.Button
import androidx.compose.ui.window.Dialog
import androidx.compose.material3.Surface
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.Row

class MainActivity : ComponentActivity() {

    // {onCreate}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CustomDialogTheme {
                CustomDialog()
            }
        }
    }
}

@Composable
fun CustomDialog() {
    var openDialog by remember { mutableStateOf(false) }
    var counter by remember { mutableStateOf(0) }


    Column {
        Button(onClick = {
            openDialog = true
        }) {
            Text("다이얼로그 열기")
        }
        Text("카운터: $counter")
    }

    if (openDialog) {
        Dialog(onDismissRequest = {
            // STEP 1) 디스미스 처리를 합니다.
            openDialog = false
        }) {
            // Surface가 없다면, 다이얼로그에 있는 컨텐츠까지 딤 효과(검정색으로 가려지는)가 있습니다.
            // Surface를 통해, Contents 영역 보호를 해야 합니다.
            Surface {
                // STEP 2) 컬럼을 만들고 설명을 적어봅시다.
                Column(modifier = Modifier.padding(8.dp)) {
                    Text("버튼을 클릭해주세요.\n * +1을 누르면 값이 증가됩니다.\n * -1을 누르면 값이 감소합니다.")

                    Row {
                        Button(onClick = {
                            openDialog = false
                        }){
                            Text("취소")
                        }
                        Button(onClick = {
                            openDialog = false
                            counter += 1
                        }) {
                            Text("+1")
                        }
                        Button(onClick = {
                            openDialog = false
                            counter -= 1
                        }){
                            Text("-1")
                        }
                    }
                }

                // STEP 3) 컬럼 안에 로우를 만들어 수평 방향으로 버튼을 배치합니다.
                // 버튼은 +1, -1, 취소로 구성하겠습니다.

                // +1은 counter를 증가시키고 -1은 감소, 취소는 다이얼로그를 닫습니다.
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CustomDialogTheme {
        CustomDialog()
    }
}