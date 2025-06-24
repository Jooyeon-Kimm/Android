package com.example.profilecardpractice

import android.graphics.Paint.Align
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.profilecardpractice.ui.theme.ProfileCardPracticeTheme

// AsyncImage (Network Image) : 기본적으로 Preview 미제공하므로,
// 애뮬레이터나 실기기에서 빌드 후 확인
class MainActivity : ComponentActivity() {
    // {onCreate}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ProfileCardPracticeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column {
                        CardExample(cardData)
                        CardExample(cardData)
                    }
                }
            }
        }
    }

    companion object {
        val cardData = CardData(
            imageUri = "https://images.unsplash.com/photo-1503023345310-bd7c1de61c7d?auto=format&fit=crop&w=800&q=80",
            imageDescription = "무료 이미지",
            author = "joo",
            description = "이십오년 유월 이십사일, 행복한 근무일입니다. 새로운 트랙터 프로젝트 이전, Jetpack Compose를 공부합니다."
        )
    }
}

@Composable
fun CardExample(cardData: CardData) {
    val placeHolderColor = Color(0x33000000)

    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        modifier = Modifier.padding(4.dp)
    ){
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(8.dp)
        ) {
            // STEP 1) `AsyncImage`, `Spacer`, `Column`, `Text`로
            // 레이아웃을 만들어보세요.
            // 1-1) 이미지
            AsyncImage(
                model = cardData.imageUri,
                contentScale = ContentScale.Crop,
                contentDescription = cardData.imageDescription,
                placeholder = ColorPainter(placeHolderColor), // 이미지 없을 때 기본
                modifier = Modifier.size(32.dp)
                    .clip(CircleShape) // RoundedCornerShape(5.dp)
            )

            // 1-2) 빈 공간 (margin)
            Spacer(modifier = Modifier.size(4.dp))

            // 1-3) 
            Column {
                Text(
                    text = cardData.author
                )
                Text(
                    text = cardData.description
                )
            }


            // STEP 2) `AsyncImage`에는 `placeholder  를 지정하고,
            // `contentScale`을 `ContentScale.Crop`으로 설정합니다.
            // `clip(CircleShape)`로 둥근 외양을 만들어봅니다.
        }
    }

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ProfileCardPracticeTheme {
        Row {
            CardExample(MainActivity.cardData)
        }
    }
}

data class CardData(
    val imageUri: String,
    val imageDescription: String,
    val author: String,
    val description: String
)