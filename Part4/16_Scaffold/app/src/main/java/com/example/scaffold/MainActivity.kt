package com.example.scaffold

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.scaffold.ui.theme.ScaffoldTheme

/*
Scaffold는 SlotAPI를 확장한 것으로 볼 수 있습니다.
Scaffold 내부에는 기본적으로 여러가지 상황에 대해 정의가 되어있습니다.

TapBar가 필요한 경우, BottomBar가 필요한 경우,
Scaffold 내장 상태, SnackBar를 위한 Host,
Floating ActionBar를 위한 SlotAPI,
위치를 담당하는 API,
drawerContent (햄버거 버튼, 천지 버튼, 삼선 버튼),
컨텐츠를 위한 SlotAPI 등이 있습니다.
 */

class MainActivity : ComponentActivity() {
    // {onCreate}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ScaffoldTheme {
                ScaffoldExample()
            }
        }
    }
}

@Composable
fun CheckBoxWithContent(
    checked: Boolean,
    toggleState: () -> Unit,
    content: @Composable RowScope.() -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.clickable { toggleState() }
    ) {
        Checkbox(
            checked = checked,
            onCheckedChange = { toggleState() }
        )
        content()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScaffoldExample() {
    var checked by remember { mutableStateOf(false) }
    val context = LocalContext.current
    Scaffold(
        // STEP 1) `topBar`를 `TopAppBar`로 채워봅니다.
        topBar = {
            TopAppBar(
                title = {
                    Text(text="Scaffold App")
                },
                navigationIcon = {
                    IconButton(onClick = { /* TODO */ }) {
                        Image(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "뒤로 가기"
                        )
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick =  { /* TODO */ }) {
                IconButton(onClick = { Toast.makeText(context, "버튼을 클릭했습니다.", Toast.LENGTH_SHORT).show() }) {
                    Image(
                        imageVector = Icons.Filled.Add,
                        contentDescription = "더하기 버튼"
                    )
                }
            }
        }

    ) { paddingValues ->
        Surface(modifier = Modifier
            .padding(paddingValues)
            .padding(8.dp)
        ) {
            // STEP 2) 아래에 CheckBoxWithContent를 넣어봅시다.
            CheckBoxWithContent(checked = checked, toggleState = { checked = !checked}) {
                Text(text="컴포즈를 좋아합니다.")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ScaffoldTheme {
        ScaffoldExample()
    }
}