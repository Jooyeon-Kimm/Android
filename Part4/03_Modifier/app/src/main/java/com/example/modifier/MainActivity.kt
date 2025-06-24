package com.example.modifier

import android.R
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.modifier.ui.theme.ModifierTheme
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {


    // {onCreate}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ModifierTheme {
                ModifierExample()
            }
        }
    }
}

@Composable
fun ModifierExample() {
    // STEP 0) 기본
//    Button(onClick = {}) {
//        Icon(
//            imageVector = Icons.Filled.Search,
//            contentDescription = null
//        )
//        Spacer(
//            modifier = Modifier.size(ButtonDefaults.IconSpacing)
//        )
//        Text("Search")
//    }

    // STEP 1) modifier에 Modifier.fillMaxSize()를 사용해봅시다.
//    Button(
//        onClick = {},
//        modifier = Modifier.fillMaxSize()
//    ) {
//        Icon(
//            imageVector = Icons.Filled.Search,
//            contentDescription = null
//        )
//        Spacer(
//            modifier = Modifier.size(ButtonDefaults.IconSpacing)
//        )
//        Text("Search")
//    }


    // STEP 2) fillMaxSize 대신, Modifier.height를 설정해봅시다.
//    Button(
//        onClick = {},
//        modifier = Modifier.height(100.dp) // width 자동으로 맞춰짐
//    ) {
//        Icon(
//            imageVector = Icons.Filled.Search,
//            contentDescription = null
//        )
//        Spacer(
//            modifier = Modifier.size(ButtonDefaults.IconSpacing)
//        )
//        Text("Search")
//    }

    // STEP 3) modifier에 height와 width를 같이 설정해봅시다.
//    Button(
//        onClick = {},
//        modifier = Modifier
//            .height(100.dp)
//            .width(200.dp)
//    ) {
//        Icon(
//            imageVector = Icons.Filled.Search,
//            contentDescription = null
//        )
//        Spacer(
//            modifier = Modifier.size(ButtonDefaults.IconSpacing)
//        )
//        Text("Search")
//    }



    // STEP 4) size에 width와 height를 인자로 넣을 수 있습니다.
//    Button(
//        onClick = {},
//        modifier = Modifier.size(width = 200.dp, height = 100.dp)
//    ) {
//        Icon(
//            imageVector = Icons.Filled.Search,
//            contentDescription = null
//        )
//        Spacer(
//            modifier = Modifier.size(ButtonDefaults.IconSpacing)
//        )
//        Text("Search")
//    }


    // STEP 5) background를 설정해봅시다. (Button에서는 되지 않음)
    // Button에 되지 않으면 Text나 Icon에 지정해봅시다.
//    Button(
//        onClick = {},
//        modifier = Modifier
//            .size(width = 200.dp, height = 100.dp)
//            .background(Color.Red) // 버튼 배경색 변경되지 않음
//    ) {
//        Icon(
//            imageVector = Icons.Filled.Search,
//            contentDescription = null
//        )
//        Spacer(
//            modifier = Modifier.size(ButtonDefaults.IconSpacing)
//        )
//        Text("Search")
//    }



    // STEP 6) colors 파라미터에 ButtonDefaults.buttonColors를 넣어봅시다.
    // backgroundColor와 contentColor 프로퍼티를 설정하세요.
//    Button(
//        colors = ButtonDefaults.buttonColors(
//            containerColor = Color.Magenta, // Material3부터는 backgroundColor가 아니라 containerColor로 명칭 변경됨
//            contentColor = Color.Cyan
//        ),
//        onClick = {},
//        modifier = Modifier.size(width = 200.dp, height = 100.dp)
//    ) {
//        Icon(
//            imageVector = Icons.Filled.Search,
//            contentDescription = null
//        )
//        Spacer(
//            modifier = Modifier.size(ButtonDefaults.IconSpacing)
//        )
//        Text("Search")
//    }


    // STEP 7) Button의 modifier에 padding을 추가해봅시다.
//    Button(
//        colors = ButtonDefaults.buttonColors(
//            containerColor = Color.Magenta, // Material3부터는 backgroundColor가 아니라 containerColor로 명칭 변경됨
//            contentColor = Color.Cyan
//        ),
//        onClick = {},
//        modifier = Modifier.padding(10.dp)
//    ) {
//        Icon(
//            imageVector = Icons.Filled.Search,
//            contentDescription = null
//        )
//        Spacer(
//            modifier = Modifier.size(ButtonDefaults.IconSpacing)
//        )
//        Text("Search")
//    }


    // STEP 8) Button에 enabled를 false로 설정하고,
    // Text의 modifier에 clickable을 넣어봅시다.
//    Button(
//        colors = ButtonDefaults.buttonColors(
//            containerColor = Color.Magenta, // Material3부터는 backgroundColor가 아니라 containerColor로 명칭 변경됨
//            contentColor = Color.Cyan
//        ),
//        onClick = {},
//        modifier = Modifier.padding(10.dp),
//        enabled = false
//    ) {
//        Icon(
//            imageVector = Icons.Filled.Search,
//            contentDescription = null
//        )
//        Spacer(
//            modifier = Modifier.size(ButtonDefaults.IconSpacing)
//        )
//        Text(
//            text = "Search",
//            modifier = Modifier.clickable {  } // 버튼은 클릭 불가하지만, text는 클릭 가능
//        )
//    }


    // STEP 9) Text의 modifier에 offset을 설정하고
    // x 파라미터를 설정합니다.
    Button(
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Magenta, // Material3부터는 backgroundColor가 아니라 containerColor로 명칭 변경됨
            contentColor = Color.Cyan
        ),
        onClick = {},
        modifier = Modifier.padding(10.dp),
        enabled = true
    ) {
        Icon(
            imageVector = Icons.Filled.Search,
            contentDescription = null,
            modifier = Modifier.background(Color.Blue)
        )
        Spacer(
            // Chain 방식
            modifier = Modifier
                .size(ButtonDefaults.IconSpacing)
                .background(Color.Blue)
        )
        Text(
            text = "Search",
            modifier = Modifier
                .offset(x = 10.dp) // Search 텍스트가 오른쪽으로 이동함 // -10.dp도 가능 (왼쪽으로 이동함)
                .background(Color.Blue)
        )
    }

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ModifierTheme {
        ModifierExample()
    }
}