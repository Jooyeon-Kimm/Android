package com.example.compositionlocal

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.LocalAbsoluteTonalElevation
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compositionlocal.ui.theme.CompositionLocalTheme

class MainActivity : ComponentActivity() {
    // {onCreate}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CompositionLocalTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CompositionLocal()
                }
            }
        }
    }
}

// STEP 4) 'compositionLocalOf'에 '8.dp'를 넣어 'LocalElevation'을 활용합니다.
@Composable
fun CompositionLocal() {
    // STEP 1) `CompositionLocalProvider`을 이용하면 특정 블록에 암시적인 값을 설정할 수 있습니다.
    // `CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.disabled)` 등을 설정해봅시다.
    // `LocalContentAlpha`를 `ContentAlpha.disabled`로 설정하겠다는 뜻입니다.
    // `ContentAlpha.medium`, `ContentAlpha.high`, `ContentAlpha.disabled`등을 제공할 수 있습니다.
    // `LocalContentColor`도 설정해봅시다. `Color.XXX`을 설정하면 됩니다.

    // STEP 2) 중간 중간에 `LocalContentColor.current` 등의 값을 출력해봅시다.
    // 가장 가까운 곳에서 설정한 값을 `current`로 얻을 수 있습니다.

    // STEP 5) Card의 elevation에 `LocalElevation`을 적용해봅시다.

    // STEP 6) LocalElevation의 값을 `CompositionLocalProvider`로 바꾸어 봅시다.
    CompositionLocalProvider(LocalAbsoluteTonalElevation provides 12.dp) {
        // Material3부터는 elevation을 Card의 modifier에 직접 설정해주어야 함
    }

    Card(
        modifier = Modifier.padding(8.dp)
    ) {
        // M3 (Material3)에서는 LocalContentAlpha가 사라짐
        CompositionLocalProvider(
            LocalContentColor provides MaterialTheme.colorScheme.onSurface.copy(alpha = 0.38f)
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
            ) {
                Text("안녕하세요. 역천자순천자")
                CompositionLocalProvider(LocalContentColor provides Color.Magenta) {
                    Text("자안녕하세요. 역천자순천")
                    Text("${LocalContentColor.current}")
                }
                Text("천자안녕하세요. 역천자순")
                Text("${LocalContentColor.current}")
                Text("${LocalTextStyle.current.alpha.toInt()}")
                Text("순천자안녕하세요. 역천자")
                Text("자순천자안녕하세요. 역천")
                Text("천자순천자안녕하세요. 역")
                Text("역천자순천자안녕하세요.")
                // STEP 3) `LocalContext.current`의 `resources`를 출력해보세요.
            }
        }
    }
}
/*
     CompositionLocalProvider를 직접 지정해주지 않아도
     Text() 같은 경우, 글자색이 기본으로 검정색인 이유는
     암묵적인 설정들이 모두 CompositionLocal로 설정돼있기 때문입니다.

     그런데 CompositionLocal이 너무 많이 쌓여있으면
     코드를 따라가기 쉽지 않게할 수 있으니,
     너무 많은 컨텐츠에 CompositionLocal을 남용하지 않는 것이 좋습니다.
 */

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CompositionLocalTheme {
        CompositionLocal()
    }
}