package com.example.checkbox

import android.os.Bundle
import android.widget.CheckBox
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.checkbox.ui.theme.CheckBoxTheme

// 동작 확인은 기기에 빌드하여 확인
// 또는 intactive mode로 확인

// 레거시 뷰에서는 어떤 것도 지정해주지 않아도, 체크박스가 동작을 하지만,
// Jetpack Compose는 checked값이 변경되어야 UI가 갱신됩니다.

// 정리) 상태가 바뀌었을 때, recomposition이 일어나고,
//       recomposition은 언제든지 일어날 수 있습니다.
class MainActivity : ComponentActivity() {
    // {onCreate}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CheckBoxTheme {
                CheckBoxExample()
            }
        }
    }

    // {onPause}
    override fun onPause() {
        super.onPause()
        // 토막지식)
        // destruction , 비구조화, 반구조화, 구조분해
        val (a, b) = listOf(2, 3)
    }

}

@Composable
fun CheckBoxExample() {
    Row (verticalAlignment = Alignment.CenterVertically) {
        // STEP 1) Checkbox를 만들어봅시다.
        // checked 속성은 false,
        // onCheckedChange는 비워둡니다.
//        Checkbox(checked = false, onCheckedChange = {} )





        // STEP 2) onCheckedChange에서 boolean 값 변수를 변경하고,
        // checked에서 그 값을 반영해봅시다. (잘 되지 않습니다.)
//        var checked = false
//        Checkbox(checked = false, onCheckedChange = { checked = !checked } )
        // 잘 안 되는 이유
        // composable 함수, compose 등은
        // recompose(다시 그려지는 절차)가 있어야, 내용이 변경됩니다.
        // recompose는 Jet Compose에서 상태 변화가 있어야 일어납니다.
        // Jet Compose에서의 상태는 mutableStateOf().
        // 위 코드는 일반적인 primitive type.


        // STEP 3) boolean 대신 remember { mutableStateOf(false) }를
        // 사용하여 상태를 도입합시다. (value 프로퍼티를 사용해야 합니다.)
//        var checked = remember { mutableStateOf(false) }
//        Checkbox(
//            checked = checked.value,
//            onCheckedChange = {
//                checked.value = !checked.value
//            }
//        )
        // composable은 언제든지 다시 그려질 수 있고, (recomposition 됨)
        // 그러므로 언제든지 상태가 날아갈 수 있다고 가정
        // 단순히 mutableStateOf()만 사용하면, 상태가 날아갈 수 있으므로,
        // remember { }를 사용해야 합니다.




        // STEP 4) delegated properties로 변경해봅시다.
        // 위임된 속성
        // STEP 3와 달리, checked가 프로퍼티인 것처럼 사용 가능 (즉슨, .value할 필요 없음)
//        var checked by remember { mutableStateOf(false) }
//        Checkbox(
//            checked = checked,
//            onCheckedChange = {
//                checked = !checked
//            }
//        )



        // STEP 5) destruction(비구조화)으로 상태를 받아서 사용해봅시다.
        // checked는 getter, setChecked는 setter
        val (checked, setChecked) = remember { mutableStateOf(false) }
        Checkbox(
            checked = checked,
            onCheckedChange = setChecked
        )


        // Checkbox를 앞에 넣어주세요.
        Text(
            text = "사람입니까?",
            // 텍스트 클릭 시에도, 체크박스 동작하게 하려면 아래처럼...
            modifier = Modifier.clickable {
                setChecked(!checked)
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CheckBoxTheme {
        CheckBoxExample()
    }
}