package com.example.viewmodel

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.viewmodel.ui.theme.ViewModelTheme

/*
    viewModel로 상태를 바꿀 때는, remember 가 필요 없음
    이유) 상태의 "생명주기"와 "보존 위치"가 다르기 때문

    ===========================================================

    remember는 Composable 내부의 일시적인 상태를 저장
    viewModel은 Activity나 Fragment보다 오래 사는 구조로,
    재구성(recomposition)이나 화면 회전에도 상태가 유지됨

    ===========================================================

    1. remember란?
    @Composable 안에서 UI 상태를 임시 저장하기 위해 사용
    recomposition 시에도 값을 기억하지만,
    화면이 사라지거나 Composable이 제거되면 값이 사라짐
    val counter = remember { mutableStateOf(0) } // 컴포저블이 유지되는 동안만 유효

    ===========================================================

    2. viewModel이란?
    ViewModel은 Compose 바깥에 있는 상태 관리자
    화면이 회전되거나 다시 그려져도 상태가 유지됨
    Fragment/Activity보다 오래 살아있기 때문

    ViewModel에서 선언한 MutableState는
    이미 Compose에서 관찰 가능하기 때문에
    remember가 필요 없음

     class MyViewModel : ViewModel() {
        var counter by mutableStateOf(0)
            private set

        fun increment() {
            counter++
        }
    }
    이렇게 해두면 Compose는 자동으로 counter를 관찰해 UI를 갱신합니다.


    비교 정리
    항목               |        remember	        |    viewModel
    생명주기            |Composable 내에서만 유지	|화면이 파괴돼도 유지 (e.g. 회전)
    사용 목적           |	UI의 짧은 생명 상태 저장	|앱 전반에서 쓰는 긴 생명 상태 유지
    재구성 대응         |	O (Recomposition 시 유지됨)|	O (ViewModelScope로 오래 살아남)
    화면 회전 대응      |	X 상태 사라짐            |	O상태 유지
    remember 필요 여부 |	O항상 필요	            |X 필요 없음

    결론
    viewModel은 Compose 바깥에 있는 독립적인 상태 저장소이므로,
    remember를 사용하지 않아도 자동으로 Compose가 상태를 추적하고 UI를 업데이트해줘요.

    그래서 viewModel 상태를 observe할 때는 이렇게만 써도 됩니다:
    val counter = viewModel.counter // remember 안 써도 OK

 */
class MainActivity : ComponentActivity() {
    // {onCreate}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ViewModelTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TopLevel()
                }
            }
        }
    }
}

// STEP 2) `ViewModel`을 상속받은 `ToDoViewModel`을 만듭니다.
// 첫 단계에서는 내용을 비워두고 시작합시다.
class ToDoViewModel : ViewModel() {
    val text = mutableStateOf("")
    val toDoList = mutableStateListOf<ToDoData>()

    val onSubmit: (String) -> Unit = {
        val key = (toDoList.lastOrNull()?.key ?: 0) + 1
        toDoList.add(ToDoData(key, it))
        text.value = ""
    }

    val onEdit: (Int, String) -> Unit = { key, newText ->
        val i = toDoList.indexOfFirst { it.key == key }
        toDoList[i] = toDoList[i].copy(text = newText)
    }

    val onToggle: (Int, Boolean) -> Unit = { key, checked ->
        val i = toDoList.indexOfFirst { it.key == key }
        toDoList[i] = toDoList[i].copy(done = checked)
    }

    val onDelete: (Int) -> Unit = { key ->
        val i = toDoList.indexOfFirst { it.key == key }
        toDoList.removeAt(i)
    }
}

// STEP 3) `TopLevel`의 파라미터로 `ToDoViewModel` 타입의
// `viewModel`을 전달합니다. 기본 값은 `viewModel()`로 설정합시다.
// 에러가 발생하면 아래의 `import` 문을 추가합니다.
// `import androidx.lifecycle.viewmodel.compose.viewModel`
@Composable
fun TopLevel(viewModel: ToDoViewModel = viewModel()) {
    // STEP 4) text, setText를 뷰 모델로 옮겨봅시다.
    // 뷰 모델의 프로퍼티로 변경할 경우에는 destrunction (비구조화,
    // 구조 분해)는 사용할 수 없으니 `by`를 써봅시다.
    // `remember`는 제거해야 합니다.
//    val (text, setText) = remember { mutableStateOf("") }

    // STEP 5) `toDoList`, `onSubmit`, `onEdit`, `onToggle`,
    // `onDelete`를 모두 뷰 모델로 옮겨봅시다.
//    val toDoList = remember { mutableStateListOf<ToDoData>() }

//    val onSubmit: (String) -> Unit = {
//        val key = (toDoList.lastOrNull()?.key ?: 0) + 1
//        toDoList.add(ToDoData(key, it))
//        viewModel.text.value = ""
//    }
//
//    val onEdit: (Int, String) -> Unit = { key, newText ->
//        val i = toDoList.indexOfFirst { it.key == key }
//        toDoList[i] = toDoList[i].copy(text = newText)
//    }
//
//    val onToggle: (Int, Boolean) -> Unit = { key, checked ->
//        val i = toDoList.indexOfFirst { it.key == key }
//        toDoList[i] = toDoList[i].copy(done = checked)
//    }
//
//    val onDelete: (Int) -> Unit = { key ->
//        val i = toDoList.indexOfFirst { it.key == key }
//        toDoList.removeAt(i)
//    }
//
    Scaffold { padding ->
        Column(modifier = Modifier.padding(padding)) {
            ToDoInput(
                text = viewModel.text.value,
                onTextChange = { viewModel.text.value = it },
                onSubmit = viewModel.onSubmit
            )
            LazyColumn {
                items(
                    items = viewModel.toDoList,
                    key = { it.key }
                ) { toDoData ->
                    ToDo(
                        toDoData = toDoData,
                        onEdit = viewModel.onEdit,
                        onToggle = viewModel.onToggle,
                        onDelete = viewModel.onDelete
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ViewModelTheme {
        TopLevel()
    }
}

@Composable
fun ToDoInput(
    text: String,
    onTextChange: (String) -> Unit,
    onSubmit: (String) -> Unit
) {
    Row(modifier = Modifier.padding(8.dp)) {
        OutlinedTextField(
            value = text,
            onValueChange = onTextChange,
            modifier = Modifier.weight(1f)
        )
        Spacer(modifier = Modifier.size(8.dp))
        Button(onClick = {
            onSubmit(text)
        }) {
            Text("입력")
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ToDoInputPreview() {
    ViewModelTheme {
        ToDoInput("테스트", {}, {})
    }
}

@Composable
fun ToDo(
    toDoData: ToDoData,
    onEdit: (key: Int, text: String) -> Unit = { _, _ -> },
    onToggle: (key: Int, checked: Boolean) -> Unit = { _, _ -> },
    onDelete: (key: Int) -> Unit = {}
) {
    var isEditing by remember { mutableStateOf(false) }
    Card(
        modifier = Modifier.padding(4.dp),
        elevation = CardDefaults.elevatedCardElevation(8.dp)
    ) {
        Crossfade(
            targetState = isEditing,
        ) {
            when (it) {
                false -> {
                    Row(
                        modifier = Modifier.padding(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = toDoData.text,
                            modifier = Modifier.weight(1f)
                        )
                        Text("완료")
                        Checkbox(
                            checked = toDoData.done,
                            onCheckedChange = { checked ->
                                onToggle(toDoData.key, checked)
                            }
                        )
                        Button(
                            onClick = { isEditing = true }
                        ) {
                            Text("수정")
                        }
                        Spacer(modifier = Modifier.size(4.dp))
                        Button(
                            onClick = { onDelete(toDoData.key) }
                        ) {
                            Text("삭제")
                        }
                    }
                }
                true -> {
                    Row(
                        modifier = Modifier.padding(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        val (text, setText) = remember { mutableStateOf(toDoData.text) }
                        OutlinedTextField(
                            value = text,
                            onValueChange = setText,
                            modifier = Modifier.weight(1f)
                        )
                        Spacer(modifier = Modifier.size(8.dp))
                        Button(onClick = {
                            isEditing = false
                            onEdit(toDoData.key, text)
                        }) {
                            Text("완료")
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ToDoPreview() {
    ViewModelTheme {
        ToDo(ToDoData(1, "nice", true))
    }
}

data class ToDoData(
    val key: Int,
    val text: String,
    val done: Boolean = false
)