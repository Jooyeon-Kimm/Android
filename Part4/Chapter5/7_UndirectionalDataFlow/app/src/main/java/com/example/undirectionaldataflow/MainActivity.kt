package com.example.undirectionaldataflow

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.undirectionaldataflow.ui.theme.UndirectionalDataFlowTheme

/*
    1. 상태는 어디에?
    UI 컴포넌트(TextField 등)가 상태를 갖지 않고,
    상태는 외부(ViewModel이나 remember 등) 가 가지고 있음

    2. 이벤트는 어디서?
    사용자의 동작 (타이핑, 버튼 클릭 등)이 이벤트이고
    이 이벤트를 ViewModel 등 위쪽으로 전달해서 상태를 바꿈

    ---
    비유: 물통과 수도꼭지
    OutlinedTextField   = 수도꼭지 (물이 흘러나오는 곳)
    mutableStateOf("")  = 물통 (물의 원천)
    onValueChange       = 수도꼭지를 틀었을 때, 물통으로 보내는 신호

    수도꼭지는 스스로 물을 가지지 않아요.
    물이 바뀌면 수도꼭지는 "아, 상태 바뀌었네!" 하고 다시 그려져요.
    이게 Compose의 핵심: UI는 상태에 따라 그려지고, 직접 상태를 갖지 않음!

    왜 이렇게 복잡하게 하냐?
    테스트 쉬움     : UI는 그냥 그리는 것만 함. 상태만 따로 테스트 가능.
    버그 적음       : 상태는 한 군데만 바뀌게 관리됨 (SSOT = Single Source of Truth).
    일관성         : ViewModel이나 StateFlow, LiveData 등을 쓰면 변화가 즉시 UI에 반영됨.


    외우지 말고 이렇게만 기억해요:
    상태는 위에서 내려오고
    이벤트는 아래에서 올라가고
    UI는 상태를 "보기만" 한다
    상태 바꾸는 건 ViewModel 등에서 한다
 */

/*
    [ Unidirectional Data Flow : 단방향 데이터 흐름 ]
    Compose의 특징 때문에, 단방향 데이터 흐름이 강조되는 경향이 있습니다.


    var name by remember { mutableStateOf("") }
    OutlinedTextField (
        value = name,
        onValueChange = { name = it },
        label = { Text("Name") }
    )

    OutlinedTextField 에서 값이 수정될 때도
    OnValueCHange의 name 값을 바꾸게 되어 있습니다.


    ● Compose의 UI는 기본적으로는 수정될 수 없습니다.
    우리가 사용하고 있는 Text Field 같은 경우는
    (Android Legacy View 같은 경우)
    상태를 가지고 있습니다.

    그래서 우리가 Text Field의 값을 수정하면, 그 값이 바뀌고,
    그 바뀐 값을 우리가 Getter나 Setter를 통해
    가져오거나 대입할 수 있는 형태입니다.

    Legacy Data Hurry 방식과 다르게
    Jetpack Compose는 Outlined Text Field라는 이 Text Field가
    상태를 가지고 있지 않습니다.
    상태를 외부에서 가지고 있습니다.



    ● UI 상태가 변경될 때만 변경된 UI 트리를 다시 만듬.
    ● TextField는 값을 받고 onValueChange를 노출함.
    ● 상태를 노출하고 이벤트를 노출. -> 단방향 데이터 흐름이 적합.
    (Unidirectional Data Flow)

    ● 상태를 위에서부터 아래로 받아오고
      이벤트를 아래에서 위로 올려보내는 형태로 코딩하게 되어 있습니다.
      한쪽만 본다면 단방향 데이터 흐름입니다.

    ● 상태는 아래로 가고, 이벤트는 위로 가는 거면, 양방향 아닌가요? 아닙니다.
      예전에 Angular 같은 도구들을 보면,
      값이 바뀔 때 아래로 내려오기도 하고
      아래쪽에서 값을 바꾸면 위로 올라가기도 했습니다.
      이렇게 왔다 갔다 상태가 갈 수 있는 것을 양방향 데이터 흐름이라고 부릅니다.

      데이터 흐름 자체는 "상태"만 보게 되는 것이고
      그렇다면 단방향이 맞습니다.


    ---
    이벤트:
    UI의 일부에서 이벤트를 생성해 위로 전달.
    뷰 모델에서 처리하는 버튼 클릭,
    사용자 세션의 만료됨을 표현하는 앱의 다른 레이어에서 전달되는 이벤트.

    상태 갱신:
    이벤트 핸들러가 상태를 바꿉니다.

    상태 표시:
    스테이트 홀더가 아래로 전달한 상태를 UI가 표시합니다.

    +---------+
    |  State  |
    +---------+
         ↓        ← state
         ↑        ← event
    +---------+
    |   UI    |
    +---------+


    ---
    UDF를 사용했을 때 장점 (단방향 데이터 흐름)
    테스트 가능성:
    상태를 표시하는 UI와 상태를 분리하면,
    둘을 분리해 쉽게 테스트할 수 있습니다.

    상태 캡슐화:
    상태가 한 곳에서만 갱신될 수 있으면,
    컴포저블 상태를 위한 단일 정보 진실원(SSOT, single source of truth)가 될 수 있고,
    일관되지 않은 상태 때문에 발생하는 버그를 줄일 수 있다.

    UI 일관성:
    StateFlow와 LiveData와 같은
    관측 가능한 상태 홀더를 사용해
    UI에 상태 갱신을 즉각 반영할 수 있다.

    ---
    Compose와 단방향 데이터 흐름

    컴포저블은 상태와 이벤트에 따라 동작한다.
    TextField는 value 파라미터가 갱신될 때만 UI가 갱신된다.
    onValueChange 콜백을 노출하는데, 값을 새로운 값으로 바꾸는 요청을 하는 이벤트이다.
    컴포즈는 값 홀더로 State 객체를 정의, 상태 값의 변경은 리컴포지션을 유발한다.
    remember, rememberSaveable로 상태를 저장.
    TextField의 value는 String, 하드코딩된 값, ViewModel로, 부모 컴포저블에서 받을 수 있고, 상태일 필요가 없음.
    값은 onValueChange 호출로 변경.

 */


class MainActivity : ComponentActivity() {
    // {onCreate}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
    }
}

