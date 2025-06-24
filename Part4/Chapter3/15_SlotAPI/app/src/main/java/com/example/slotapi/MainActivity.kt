package com.example.slotapi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.slotapi.ui.theme.SlotAPITheme
/*
Composable 함수가
다른 Composable 함수나 다른 Component를 포함할 수 있게 되어 있는 것을
Slot API라고 합니다...

 Slot API란? Composable한 함수, 즉 UI 요소를 매개변수로 하여
 또 다른 Composable 함수를 만들고 싶을 때 사용한다.
 이를 통해 컴포넌트화를 더욱 수월하게 할 수 있다.

 Slot API를 통해서 Composable을 체계적으로 합성하고 관리할 수 있습니다.
 */

/*
 토막지식) Getter와 Setter가 있는 것들은
 모두 by (위임된 프로퍼티)를 이용해서
 일반적인 프로퍼티처럼 사용할 수 있습니다.
 */

/*
가능한 위임할 수 있는 것은, 모두 외부에서 위임하도록 하여
재사용성을 높이는 것이 좋습니다.
 */

class MainActivity : ComponentActivity() {
    // {onCreate}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SlotApiExample()
        }
    }
}

// STEP 1) `Row`를 `@Composable` 함수로 분리합니다.
// `checked`의 값, `Text`의 `text`를 인자로 전달합니다.
//@Composable
//fun CheckBoxWithText(checked: MutableState<Boolean>, text: String) {
//    Row(verticalAlignment = Alignment.CenterVertically) {
//        Checkbox(
//            checked = checked.value,
//            onCheckedChange = { checked.value = it}
//        )
//        Text(
//            text = text,
//            modifier = Modifier.clickable { checked.value = !checked.value }
//        )
//    }
//}


// STEP 2) `@Composable` 함수에서 `@Composable () -> Unit` 타입으로
// 'content'를 받아옵니다.
// `Row`의 `Text`를 빼고 `content()`를 넣읍시다.
// `Row`에 `Modifier.clickable`을 넣어 전체를 클릭가능하게 합시다.
//@Composable
//fun CheckBoxWithSlot(
//    checked: MutableState<Boolean>,
//    content: @Composable ()-> Unit // Slot API를 사용할 때는, content()를 마지막으로 두는 것이 좋습니다.
//    // 그렇게 하면, 후행 람다로 빼낼 수 있기 때문입니다.
//) {
//    Row(
//        verticalAlignment = Alignment.CenterVertically,
//        modifier = Modifier.clickable {
//            checked.value = !checked.value
//        }
//    ) {
//        Checkbox(
//            checked = checked.value,
//            onCheckedChange = { checked.value = it }
//        )
//        content()
//        // CheckBoxWithText()에서 텍스트를 표기하는 것을 책임지지 않고,
//        // 호출하는 측에서 책임지도록 합니다. (content())
//    }
//}


// STEP 3) `content`의 타입을 `@Composable () -> Unit`로 바꿉시다.
// 이렇게 다른 Composable Content를 넣는 방식을
// SlotAPI라고 합니다.
//@Composable
//fun CheckBoxWithSlot(
//    checked: MutableState<Boolean>,
//    content: @Composable RowScope.()-> Unit // Slot API를 사용할 때는, content()를 마지막으로 두는 것이 좋습니다.
//    // 그렇게 하면, 후행 람다로 빼낼 수 있기 때문입니다.
//) {
//    Row(
//        verticalAlignment = Alignment.CenterVertically,
//        modifier = Modifier.clickable {
//            checked.value = !checked.value
//        }
//    ) {
//        Checkbox(
//            checked = checked.value,
//            onCheckedChange = { checked.value = it }
//        )
//        content()
//        // CheckBoxWithText()에서 텍스트를 표기하는 것을 책임지지 않고,
//        // 호출하는 측에서 책임지도록 합니다. (content())
//    }
//}


// STEP 4) 상태를 바꾸는 람다를 `@Composable` 함수의 인자로 뺍시다.
// 인자에서 MutableState 대신, boolean 값으로 변경합니다.
@Composable
fun CheckBoxWithSlot(
    checked: Boolean,
    onCheckedChanged: () -> Unit,
    content: @Composable RowScope.()-> Unit // Slot API를 사용할 때는, content()를 마지막으로 두는 것이 좋습니다.
    // 그렇게 하면, 후행 람다로 빼낼 수 있기 때문입니다.
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.clickable {
            onCheckedChanged()
        }
    ) {
        Checkbox(
            checked = checked,
            onCheckedChange = { onCheckedChanged() }
        )
        content()
        // CheckBoxWithText()에서 텍스트를 표기하는 것을 책임지지 않고,
        // 호출하는 측에서 책임지도록 합니다. (content())
    }
}


// 변경 전
@Composable
fun SlotApiExample() {
    val checked1 = remember { mutableStateOf(false) }
    val checked2 = remember { mutableStateOf(false) }

    Column {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(
                checked = checked1.value,
                onCheckedChange = { checked1.value = it}
            )
            Text(
                text = "텍스트 1",
                modifier = Modifier.clickable { checked1.value = !checked1.value }
            )
        }

        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(
                checked = checked2.value,
                onCheckedChange = { checked2.value = it}
            )
            Text(
                text = "텍스트 2",
                modifier = Modifier.clickable { checked2.value = !checked2.value }
            )
        }
    }
}


// 변경 후
//@Composable
//fun SlotApiModifiedExample() {
//    val checked1 = remember { mutableStateOf(false) }
//    val checked2 = remember { mutableStateOf(false) }
//
//    Column {
//        CheckBoxWithText(checked1, "텍스트1")
//        CheckBoxWithText(checked2, "텍스트2")
//    }
//}

@Composable
fun SlotApiModifiedExample() {
    var checked1 by remember { mutableStateOf(false) }
    var checked2 by remember { mutableStateOf(false) }

    Column {
        CheckBoxWithSlot(
            checked = checked1,
            onCheckedChanged = { checked1 = !checked1 }
        ) {
            Text(text = "텍스트1", modifier = Modifier.align(Alignment.CenterVertically))
        }

        CheckBoxWithSlot(
            checked = checked2,
            onCheckedChanged = { checked2 = !checked2 }
        ) {
            Text(text = "텍스트2")
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    SlotAPITheme {
        SlotApiModifiedExample()
    }
}