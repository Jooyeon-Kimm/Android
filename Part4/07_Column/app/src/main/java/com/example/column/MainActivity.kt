package com.example.column

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.column.ui.theme.ColumnTheme

/*
Column은 Horizontally하게만 Alignment할 수 있고,
Row는 Vertically하게만 Alignment할 수 있다.

Box Layout에서는 Alignment가 자유롭게 2차원으로 지정할 수 있다.

[정리]
Box만 2차원으로 Alignment 할 수 있고,
Column/Row는 1차원으로 해당 방향으로 (Column Scope/Row Scope)에 맞는 Aligment할 수 있다.
 */


class MainActivity : ComponentActivity() {
    // {onCreate}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ColumnTheme {
                ColumnExample()
            }
        }
    }
}

// Row를 수평, Column을 수직이라고 한다면,
// Alignment는 Row/Column 방향 반대
// Arrangment는 Row/Column 방향과 같음
@Composable
fun ColumnExample() {
    // STEP 0) 기본
//    Column(modifier = Modifier.size(100.dp)) {
//        Text(text = "첫 번째")
//        Text(text = "두 번째")
//        Text(text = "세 번째")
//    }

    // STEP 1) horizontalAlignment를 Column에 적용해봅시다.
//    Column(
//        horizontalAlignment = Alignment.CenterHorizontally,
//        modifier = Modifier.size(100.dp)
//    ) {
//        Text(text = "첫 번째")
//        Text(text = "두 번째")
//        Text(text = "세 번째")
//    }

    // STEP 2) Column에 vericalAlignment를 지정해봅시다.
    // SpaceAround, SpaceEvenly, SpaceBetween도 해봅시다.
//    Column(
//        verticalArrangement = Arrangement.SpaceBetween,
//        horizontalAlignment = Alignment.CenterHorizontally,
//        modifier = Modifier.size(100.dp)
//    ) {
//        Text(text = "첫 번째")
//        Text(text = "두 번째")
//        Text(text = "세 번째")
//    }


    // STEP 3) Text에 Modifier.align을 사용해봅시다.
    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.size(100.dp)
    ) {
        Text(
            text = "첫 번째",
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Text(
            text = "두 번째"
            // 기본 modifier의 align값은 END
        )
        Text(
            text = "세 번째",
            modifier = Modifier.align(Alignment.Start)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ColumnTheme {
        ColumnExample()
    }
}