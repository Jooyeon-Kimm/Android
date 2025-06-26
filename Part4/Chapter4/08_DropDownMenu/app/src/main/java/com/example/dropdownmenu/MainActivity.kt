package com.example.dropdownmenu

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.dropdownmenu.ui.theme.DropDownMenuTheme

// 드롭다운 메뉴의 외부를 클릭하면
// 드롭다운 메뉴가 닫힙니다.
class MainActivity : ComponentActivity() {
    // {onCreate}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DropDownMenuTheme {
                DropDownMenuExample()
            }
        }
    }
}

@Composable
fun DropDownMenuExample() {
    var expandDropDownMenu by remember { mutableStateOf(false) }
    var counter by remember { mutableIntStateOf(0) }

    Column {
        Button(onClick = {
            expandDropDownMenu = true
        }) {
            Text("드롭다운 메뉴 열기")
        }
        Text("카운터: $counter")
    }

    // STEP 1) `DropdownMenu`를 만들고 `expanded`를 `expandDropDownMenu`로 등록합니다.
    // `onDismissRequest`에 대해서는 `expandDropDownMenu = false`로 바꿉니다.
    DropdownMenu(
        expanded = expandDropDownMenu,
        onDismissRequest = {
            expandDropDownMenu = false
        }
    ) {
        // STEP 2) 두개의 `DropdownMenuItem`을 등록합니다.
        // `onClick`을 구현하고 내용을 `Text`로 채워봅시다.
        DropdownMenuItem(
            onClick = {
                counter++
            },
            text = {
                Text("증가")
            }
        )
        DropdownMenuItem(
            onClick = {
                counter--
            },
            text = {
                Text("감소")
            }
        )

    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    DropDownMenuTheme {
        DropDownMenuExample()
    }
}