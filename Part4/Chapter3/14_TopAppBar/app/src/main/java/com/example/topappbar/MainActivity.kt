package com.example.topappbar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.topappbar.ui.theme.TopAppBarTheme
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton

/*
Icons.Filled.ArrowBack은
안드로이드 하드웨어에 있는 back 버튼과는 다른 기능입니다.
안드로이드 밑에서 back 버튼을 누르게 되면,
Stack 상에서 back으로 가는 것이지만,
TopAppBar 상단의 ArrowBack 버튼은, up으로 가는 버튼입니다.

예) 메일 프로그램
back 버튼: 메일을 일기 전에 무엇을 했는지 기억하고 이동
up 버튼  : 메일 목록을 보여주는 것
 */

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TopAppBarTheme {
                TopBarExample("Android")
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarExample(name: String) {
    Scaffold(
        topBar = {
            // STEP 1) TopAppBar를 만들고, title 항목을 채워줍니다.
//            TopAppBar(title = { Text("TopAppBar") })

            // STEP 2) navigationIcon 파라미터를 채워봅시다.
            // IconButton을 만들고 자식으로 Icon을 넣읍시다.
            // 아이콘은 Icons.Filled.ArrowBack을 채웁니다.
//            TopAppBar(
//                title = { Text("TopAppBar") },
//                navigationIcon = {
//                    IconButton(onClick = {}){
//                        Icon(
//                            imageVector = Icons.Filled.ArrowBack,
//                            contentDescription = "업 네비게이션"
//                        )
//                    }
//                }
//            )

            // STEP 3) actions를 추가해봅니다.
            // Icons.Filled의 여러 아이콘을 이용해봅시다.
            TopAppBar(
                title = { Text("TopAppBar") },
                navigationIcon = {
                    IconButton(onClick = {}){
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "업 네비게이션"
                        )
                    }
                },
                actions = {
                    IconButton(onClick = {}) {
                        Icon(
                            imageVector = Icons.Filled.Search,
                            contentDescription = "검색"
                        )
                    }
                    IconButton(onClick = {}) {
                        Icon(
                            imageVector = Icons.Filled.Settings,
                            contentDescription = "설정"
                        )
                    }
                    IconButton(onClick = {}) {
                        Icon(
                            imageVector = Icons.Filled.AccountBox,
                            contentDescription = "계정"
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            Text(text = "Hello $name!")
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TopAppBarTheme {
        TopBarExample("Android")
    }
}