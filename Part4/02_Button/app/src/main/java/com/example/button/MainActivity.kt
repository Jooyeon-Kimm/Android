package com.example.button

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SegmentedButtonDefaults.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.button.ui.theme.ButtonTheme

class MainActivity : ComponentActivity() {

    // {onCreate}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ButtonTheme {
                // STEP 1) Button을 클릭했을 때, Toast를 출력하게 만들어봅시다.
                ButtonExample(onButtonClicked = {
                    Toast.makeText(this, "Send clicked", Toast.LENGTH_SHORT).show()
                })
            }
        }
    }
}

// 컴포저블 함수
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ButtonExample(onButtonClicked: () -> Unit) {
    // STEP 0) 기본
//    Button(onClick = onButtonClicked) {// RowScope
//        Text("Send")
//    }


    // STEP 1) Button을 클릭했을 때, Toast를 출력하게 만들어봅시다.


    // STEP 2) Icon을 Text 앞에 추가해봅시다.
    // imageVector에는 Icons.Filled.Send를 넣고
    // contentDescription에는 null을 넣어봅시다.
//    Button(onClick = onButtonClicked) {
//        Icon(
//            imageVector = Icons.Filled.Send,
//            contentDescription = "Send 버튼"
//        )
//        Text("Send")
//    }

    // STEP 3) 아이콘과 텍스트 사이에 Spacer를 넣어봅시다.
    // modifier에 Modifier.size를 넣고
    // 사이즈를 ButtonDefaults.IconSpacing으로 지정합시다.
//    Button(onClick = onButtonClicked) {
//        Icon(
//            imageVector = Icons.Filled.Send,
//            contentDescription = "Send 버튼"
//        )
//        Spacer(
//            modifier = Modifier.size(ButtonDefaults.IconSpacing)
//        ) // 30.sp로 설정도 가능
//        Text("Send")
//    }

    // STEP 4) enabled를 false로 바꿔봅시다. 클릭 방지
//    Button(
//        onClick = onButtonClicked,
//        enabled=false
//    ) {
//        Icon(
//            imageVector = Icons.Filled.Send,
//            contentDescription = "Send 버튼"
//        )
//        Spacer(
//            modifier = Modifier.size(ButtonDefaults.IconSpacing)
//        ) // 30.sp로 설정도 가능
//        Text("Send")
//    }

    // STEP 5) boder에 BorderStroke를 설정합시다.
//    Button(
//        onClick = onButtonClicked,
//        enabled=true,
//        border = BorderStroke(10.dp, Color.Magenta)
//    ) {
//        Icon(
//            imageVector = Icons.Filled.Send,
//            contentDescription = "Send 버튼"
//        )
//        Spacer(
//            modifier = Modifier.size(ButtonDefaults.IconSpacing)
//        ) // 30.sp로 설정도 가능
//        Text("Send")
//    }

    // STEP 6) Shape를 CircleShape로 지정
//    Button(
//        onClick = onButtonClicked,
//        enabled=true,
//        border = BorderStroke(10.dp, Color.Magenta),
//        shape = RoundedCornerShape(10.dp) // CircleShape 도 해보세요.
//    ) {
//        Icon(
//            imageVector = Icons.Filled.Send,
//            contentDescription = "Send 버튼"
//        )
//        Spacer(
//            modifier = Modifier.size(ButtonDefaults.IconSpacing)
//        ) // 30.sp로 설정도 가능
//        Text("Send")
//    }

    // STEP 7) contentPadding에 PaddingValues를 설정합니다.
    Button(
        onClick = onButtonClicked,
        enabled=true,
        border = BorderStroke(10.dp, Color.Magenta),
        shape = RoundedCornerShape(10.dp),
        contentPadding = PaddingValues(20.dp)
    ) {
        Icon(
            imageVector = Icons.Filled.Send,
            contentDescription = "Send 버튼"
        )
        Spacer(
            modifier = Modifier.size(ButtonDefaults.IconSpacing)
        ) // 30.sp로 설정도 가능
        Text("Send")
    }

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ButtonTheme {
        ButtonExample(onButtonClicked = {})
    }
}