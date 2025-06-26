package com.example.constraintchainbarrier

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester.Companion.createRefs
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalMapOf
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.constraintchainbarrier.ui.theme.ConstraintChainBarrierTheme

// Barrier 를 이용하면, 여러 가지 Composable 공통적으로
// 가장 아래쪽, 가장 위쪽에 배치할 수 있습니다.
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ConstraintChainBarrierTheme {
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    ConstraintLayoutExample()
                }
            }
        }
    }
}

@Composable
fun ConstraintLayoutExample() {
    ConstraintLayout(
        modifier = Modifier.fillMaxSize()
    ) {
        val (redBox, yellowBox, magentaBox, text) = createRefs()

        Box(
            modifier = Modifier
                .size(40.dp)
                .background(Color.Red)
                .constrainAs(redBox) {
                    top.linkTo(parent.top, margin = 18.dp)
                }
        )
        Box(
            modifier = Modifier
                .size(40.dp)
                .background(Color.Yellow)
                .constrainAs(yellowBox) {
                    top.linkTo(parent.top, margin = 32.dp)
                }
        )
        Box(
            modifier = Modifier
                .size(40.dp)
                .background(Color.Magenta)
                .constrainAs(magentaBox) {
                    top.linkTo(parent.top, margin = 50.dp)
                }
        )

        // STEP 1) `createVerticalChain`, `createHorizontalChain`을
        // 이용해서 세 박스의 레퍼런스를 연결해봅시다.
//        createVerticalChain(redBox, yellowBox, magentaBox)
//        createHorizontalChain(redBox, yellowBox, magentaBox)
        // createVerticalChain/createHorizontalChain 은
        // 가변 아규먼트 이기 때문에, 몇 개든지 인자로 받을 수 있음

        // STEP 2) `createHorizontalChain`를 사용하고 `chainStyle`
        // 키워드 파라미터를 추가합시다.
        // `ChainStyle.Packed`, `ChainStyle.Spread`,
        // `ChainStyle.SpreadInside` 등을 지정해봅시다.
        createHorizontalChain(redBox, yellowBox, magentaBox,
            chainStyle = ChainStyle.SpreadInside)

        // STEP 3) 세 박스의 top을 parent.top에 연결하고 각각
        // 다른 마진을 줍시다.

        // STEP 4) `createBottomBarrier`로 베리어를 만듭시다.
        // 이는 모든 박스들의 하단을 포함하는 베리어입니다.
        val boxBottomBarrier = createBottomBarrier(redBox, yellowBox, magentaBox)
        // Barrier : Start, End, Top, Bottom 등...

        // STEP 5) `Text` 하나 만들어 top을 박스 베리어로 지정합니다.
        Text(
            text = """
                나랏말싸미 듕귁에 달아 문자와로 서르 사맛디 아니할쎄
                이런 전차로 어린 백셩이 니르고져 홀베이셔도 
                마참네 제 뜨들 시러펴디 몯할 노미하니아
                내 이랄 윙하야 어엿비너겨 새로 스믈 여들 짜랄 맹가노니
                사람마다 해여 수비니겨 날로 쑤메 뻔한킈 하고져 할따라미니라""".trimIndent(),
            modifier = Modifier.constrainAs(text) {
                top.linkTo(boxBottomBarrier)
            }
        )

        // STEP 6) 체인의 방향이나 베리어 방향을 바꾸어 보며 다양하게 테스트해봅시다.
    }

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ConstraintChainBarrierTheme {
        ConstraintLayoutExample()
    }
}