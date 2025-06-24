package com.example.textfield

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.textfield.ui.theme.TextFieldTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TextFieldTheme {
                TextFieldExample()
            }
        }
    }
}

@Composable
fun TextFieldExample() {
    var name by remember { mutableStateOf("Joo")}

    Column(modifier = Modifier.padding(16.dp)) {
        // STEP 1) TextField를 Text 위에 만듭니다.
        // value와 nValueChanged는 비워둡니다.
//        TextField(value = "Joo", onValueChange = {})

        // STEP 2) Text에 TextField 입력을 출력하게 합니다.
        // mutableStateOf("") 필드를 하나 만듭니다.
//        TextField(value = name, onValueChange = { name = it })


        // STEP 3) TextField에 label을 추가합니다.
        // `Text("Name")`으로 합시다.
//        TextField(
//            value = name,
//            label = {
//                Text(text = "Name")
//            },
//            onValueChange = { name = it }
//        )


        // STEP 4) TextField와 Text 사이에 Spacer를 넣어
        // 8.dp 간격을 둡니다.
//        TextField(
//            value = name,
//            label = {
//                Text(text = "Name")
//            },
//            onValueChange = { name = it }
//        )
//        Spacer(modifier = Modifier.padding(8.dp))



        // STEP 5) TextField를 OutlinedTextField로 변경해봅시다.
        OutlinedTextField(
            value = name,
            label = {
                Text(text = "Name")
            },
            onValueChange = { name = it }
        )
        Spacer(modifier = Modifier.padding(8.dp))


        Text(text = "Hello $name")

        // OutlinedText 이외에, OutlinedButton도 있습니다.
    }

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TextFieldTheme {
        TextFieldExample()
    }
}