package com.example.navigation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.navigation.ui.theme.NavigationTheme

class MainActivity : ComponentActivity() {
    // {onCreate}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NavigationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavigationExample()
                }
            }
        }
    }
}

// STEP 2) `navController` 파라미터를 만듭니다.
// `NavHostController` 타입에 기본 값은 `rememberNavController()`
@Composable
fun NavigationExample(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(), // 내부 컨트롤러
) {
    // STEP 3) `NavHost`를 만듭니다.
    // `navController`, `"Home"`, `modifier`를 전달합시다.
    NavHost(
        navController = navController,
        startDestination = "Home",
        modifier = modifier
    ) {
        // STEP 4) `composable("Home")`를 만들고
        // 안에 "Office로 이동" 버튼을 만듭니다.
        // Router : Home
        composable("Home") {
            Column {
                Text("Home")
                Button(onClick = {
                    navController.navigate("Office") {
                        /*
                            popUpTo 에 대하여...
                            사용자가 화면을 아래와 같이 이동했을 때,
                            "Home" → "Office" → "Playground"

                            popUpTo("Home") 를 실행하면,
                            "Home" 위에 있던 "Office"와 "Playground"는 스택에서 제거되고
                            "Office"로 새롭게 이동합니다.
                         */
                        popUpTo("Home") {
                            inclusive = true // Home 조차 함께 제거한다. (inclusive)
                        }
                    }
                } ){
                    Text("Office로 이동")
                }

                Button(onClick = {
                    navController.navigate("Playground")
                } ){
                    Text("Playground로 이동")
                }

                Button(onClick = {
                    navController.navigate("Home") {
                        launchSingleTop = true // Home -> Home 하여도, 2개의 Home이 쌓이지 않음
                        // launchSingleTop : 최상단에 떠 있으면, 새로 띄우지 않는다.
                    }
                } ){
                    Text("Home으로 이동")
                }

                Button(onClick = {
                    navController.navigate("Argument/imogenHeap") {
                        launchSingleTop = true // Home -> Home 하여도, 2개의 Home이 쌓이지 않음
                        // launchSingleTop : 최상단에 떠 있으면, 새로 띄우지 않는다.
                    }
                } ){
                    Text("imogenHeap userId로 연결")
                }

            }
        }

        // STEP 5) `composable("Office")`를 만들고 텍스트를 넣어봅시다.
        // "Office로 이동" 버튼에 `navController.navigate("Office")`를 넣어줍니다.
        // Router : Office
        composable("Office") {
            Column {
                Text("Office")
                Button(onClick = {
                    navController.navigate("Home")
                } ){
                    Text("Home으로 이동")
                }
                Button(onClick = {
                    navController.navigate("Playground")
                } ){
                    Text("Playground로 이동")
                }
            }
        }

        // STEP 6) `Playground`를 만들고
        // `Home`, `Office`, `Playgorund`를 서로 연결합니다.
        // Router : Playground
        composable("Playground") {
            Column {
                Text("Playground")
                Button(onClick = {
                    navController.navigate("Home")
                } ){
                    Text("Home으로 이동")
                }
                Button(onClick = {
                    navController.navigate("Office")
                } ){
                    Text("Office로 이동")
                }
            }
        }

        // STEP 7) Home, Office, Playgorund, Home, Office, Playgorund 순으로 이동한 후
        // 백버튼을 계속 눌러서 이동을 확인해봅시다.
        // STACK 에 계속 쌓임

        // STEP 8) navigate에 후행 람다로 `popUpTo("Home")`을 넣고
        // 스택 이동을 확인해봅니다.
        // STACK에 안 쌓임

        // STEP 9) `popUpTo`의 후행 람다에 `inclusive = true`를 넣어보고
        // 스택 이동을 확인해봅시다.

        // STEP 10) `Home`에서 `Home`으로 가는 버튼을 만들고
        // `launchSingleTop = true`을 설정해보세요.

        // STEP 11) "Argument/{userId}"를 라우트로 받는 composable을 만드세요.
        // `arguments?.get("userId")`을 받아 출력하세요.
        // "Argument/fastcampus"로 이동하는 버튼을 만들어보세요.
        composable("Argument/{userId}") { backStackEntry ->
            val userId = backStackEntry.arguments?.get("userId")
            Text("userId: $userId")
        }
    }


}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    NavigationTheme {
        NavigationExample()
    }
}

/*
    정리)
    popUpTo("X") :
    X까지 돌아가면서, 그 위에 있는 화면들을 지워준다.

    inclusive = true :
    X도 같이 지운다.

    inclusive = false :
    X는 남긴다.

    ---
    예 1)
    예를 들어, 아래처럼 화면이 쌓였는데
    Home → Office → Playground

    아래를 실행했다면?
    navController.navigate("Result") {
        popUpTo("Office")
    }

    "Playground"는 사라지고,
    "Office"는 남고,
    "Result"가 새로 올라가서

    Home → Office → Result


    ---
    예 2)
    예를 들어, 아래처럼 화면이 쌓였는데
    Home → Office → Playground

    아래를 실행했다면? (후행람다에 inclusive = true가 추가됨)
    navController.navigate("Result") {
        popUpTo("Office") {
            inclusive = true
        }
    }

    "Playground"도 지워지고
    "Office"도 같이 지워짐
    "Result"만 남음

    Home → Result
    화면 이동 시 뒤로가기 했을 때 다시 돌아가게 하기 싫을 때 씁니다.


    ---
    inclusive를 왜 써야하냐?
    Home -> Login -> Mail

    popUpTo("Login") { include = true }
    Home -> Mail 만 stack에 남아있음
 */


/*
    { 기본 }

    이동경로
    1. Home
    2. Home → Office
    3. Home → Office → Playground

    [시작]
    ┌───────┐
    │ Home  │  ← 현재 화면
    └───────┘

    [navigate("Office")]
    ┌───────┐
    │ Home  │
    ├───────┤
    │Office │  ← 현재 화면
    └───────┘

    [navigate("Playground")]
    ┌────────────┐
    │   Home     │
    ├────────────┤
    │  Office    │
    ├────────────┤
    │Playground  │  ← 현재 화면
    └────────────┘


 */

/*
    { 예제 1 }
    popUpTo("Office") (기본값: inclusive = false)

    navController.navigate("Result") {
        popUpTo("Office")
    }

    스택 상태 변화:
    [Before]
    ┌────────────┐
    │   Home     │
    ├────────────┤
    │  Office    │
    ├────────────┤
    │Playground  │  ← 현재 화면
    └────────────┘

    [popUpTo("Office")]
    ┌────────────┐
    │   Home     │
    ├────────────┤
    │  Office    │
    └────────────┘

    [navigate("Result")]
    ┌────────────┐
    │   Home     │
    ├────────────┤
    │  Office    │
    ├────────────┤
    │  Result    │  ← 현재 화면
    └────────────┘


 */


/*
    { 예제 2 }
    popUpTo("Office") { inclusive = true }

    navController.navigate("Result") {
        popUpTo("Office") {
            inclusive = true
        }
    }

    [Before]
    ┌────────────┐
    │   Home     │
    ├────────────┤
    │  Office    │
    ├────────────┤
    │Playground  │
    └────────────┘

    [popUpTo("Office") + inclusive = true]
    ┌────────────┐
    │   Home     │
    └────────────┘

    [navigate("Result")]
    ┌────────────┐
    │   Home     │
    ├────────────┤
    │  Result    │  ← 현재 화면
    └────────────┘

    언제 써야 하냐면?
    로그인 후 홈으로 가고, 로그인 화면은 제거하고 싶을 때
    `popUpTo("Login") { inclusive = true }`

    설정 → 저장 → 다시 홈. 중간 저장화면은 제거하고 싶을 때
    `popUpTo("Setting")`

    Home → Home 눌렀을 때, 중복 제거하고 한 개만 남기고 싶을 때
    `launchSingleTop = true`

 */