package com.example.constraintlayoutexample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.constraintlayoutexample.ui.theme.ConstraintLayoutExampleTheme
import androidx.compose.ui.graphics.Color
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.Dimension
import coil.compose.AsyncImage

class MainActivity : ComponentActivity() {

    // {onCreate}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                Column(modifier = Modifier.fillMaxWidth()) {
                    CardExample(cardData)
                    CardExample(cardData)
                    CardExample(cardData)
                }
            }
        }
    }

    companion object {
        val cardData = CardData(
            imageUri = "https://images.unsplash.com/photo-1503023345310-bd7c1de61c7d?auto=format&fit=crop&w=800&q=80",
            imageDescription = "무료 이미지",
            author = "joo",
            description = "감사합니다. 감사할 일이 많습니다. 현재 호흡에 집중해봅니다."
        )
    }
}


data class CardData (
    val imageUri: String,
    val imageDescription: String,
    val author: String,
    val description: String,
)

@Composable
fun CardExample(cardData : CardData) {
    val placeHolderColor = Color(0x33000000)

    Card(
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 8.dp),
        modifier = Modifier.padding(4.dp),
    ) {
        // STEP 1) 해당 파일 가장 마지막 주석처리된 Row 레아이웃을
        // ConstraintLayout으로 바꿔봅시다.
        ConstraintLayout(
            modifier = Modifier.fillMaxWidth()
        ) {
            // ConstraintLayout에 제약을 주기 위해서
            // Reference Key를 만듭니다.
            val (profileImage, author, description) = createRefs()

            AsyncImage (
                model = "https://images.unsplash.com/photo-1506744038136-46273834b3fb?w=1080",
                contentDescription = "Beautiful Nature Landscape",
                contentScale = ContentScale.Crop,
                placeholder = ColorPainter(color = placeHolderColor),
                modifier = Modifier
                    .clip(CircleShape)
                    .size(40.dp)
                    .constrainAs(profileImage) {
                        centerVerticallyTo(parent)
                        // linkTo(parent.top, parent.bottom) // 또 다른 세로 중앙 정렬 방법
                        start.linkTo(parent.start, margin = 8.dp)
                    }
            )
            Text(
                text = cardData.author,
                modifier = Modifier.constrainAs(author) {
                    // start.linkTo(profileImage.end) // 프로필 이미지의 오른쪽에 텍스트 배치하는 또 다른 방법
                    linkTo(
                        start = profileImage.end,
                        end = parent.end,
                        startMargin = 8.dp,
                        endMargin = 8.dp
                    )
                    width = Dimension.fillToConstraints
                }
            )
            Text(
                text = cardData.description,
                modifier = Modifier.constrainAs(description) {
                    linkTo(
                        start = profileImage.end,
                        end = parent.end,
                        startMargin = 8.dp,
                        endMargin = 8.dp
                    )
                    width = Dimension.fillToConstraints
                }
            )

            val chain = createVerticalChain(
                author,
                description,
                chainStyle = ChainStyle.Packed
            )
            constrain(chain) {
                top.linkTo(parent.top, margin = 8.dp)
                bottom.linkTo(parent.bottom, margin = 8.dp)
            }
        }

    }

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ConstraintLayoutExampleTheme {
        CardExample(cardData = MainActivity.cardData)
    }
}

/*
Row 기반을 ConstraintLayout으로 변경
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(8.dp)
        ) {
            AsyncImage (
                model = "https://images.unsplash.com/photo-1506744038136-46273834b3fb?w=1080",
                contentDescription = "Beautiful Nature Landscape",
                contentScale = ContentScale.Crop,
                placeholder = ColorPainter(color = placeHolderColor),
                modifier = Modifier
                    .clip(CircleShape)
                    .size(40.dp)
            )
            Spacer(modifier = Modifier.size(8.dp))
            Column {
                Text(text = cardData.author)
                Text(text = cardData.description)
            }
        }
 */