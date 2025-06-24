package com.example.catalog

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.catalog.ui.theme.CatalogTheme

class MainActivity : ComponentActivity() {
    // {onCreate}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CatalogTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    CatalogExample(items)
                }
            }
        }
    }
}

data class ItemData(
    val imageId : Int,
    val title : String,
    val description : String,
)

val items = listOf(
    ItemData(
        title = "대만의 한 거리",
        imageId = R.drawable.taiwan,
        description = "대만의 어느 도시 골목길. 양옆으로 다닥다닥 붙은 건물에는 오래된 에어컨 실외기와 창문형 차양이 달려 있고, 그 아래로는 주민들의 빨래가 바람에 나부낀다. 좁은 길에는 스쿠터들이 질서 없이 세워져 있고, 도로 가장자리에는 화분들이 놓여 있어 작지만 소박한 녹음을 만든다. 빛은 나뭇잎 사이로 스며들어 정적 속에 부드러운 생기를 더하며, 이 골목에서 살아가는 사람들의 조용한 일상이 고스란히 느껴진다."
    ),
    ItemData(
        title = "싱가포르 가든스 바이 더 베이",
        imageId = R.drawable.singapore,
        description = "싱가포르의 상징적인 정원, 가든스 바이 더 베이에서 마치 미래 도시를 연상케 하는 거대한 인공 나무 '슈퍼트리'들이 울창한 열대 식물들 사이에 우뚝 솟아 있다. 공중 산책로인 OCBC 스카이웨이에서는 관광객들이 도심과 정원을 동시에 조망하며 자연과 기술이 어우러진 풍경을 만끽하고 있다."
    ),
    ItemData(
        title = "스페인 바르셀로나 구엘 공원",
        imageId = R.drawable.spain,
        description = "스페인 바르셀로나의 구엘 공원은 가우디 특유의 유기적인 곡선미와 화려한 모자이크 타일 장식이 어우러진 환상적인 건축물이 가득한 공간이다. 동화 속 장면처럼 독특한 탑과 건물들이 푸른 하늘 아래 펼쳐져 방문객들을 매혹시킨다."
    ),
    ItemData(
        title = "인도 뱅갈루루",
        imageId = R.drawable.india,
        description = "인도 뱅갈루루의 활기찬 거리 풍경으로, 수많은 오토바이와 행인, 상점들이 뒤엉켜 역동적인 일상을 보여준다. 좁은 도로를 가득 메운 사람과 상품들 사이로 지역 특유의 문화와 에너지가 느껴진다."
    ),
    ItemData(
        title = "핀란드 헬싱키",
        imageId = R.drawable.finland,
        description = "하얀 눈과 얼어붙은 항구가 인상적인 핀란드 헬싱키의 겨울 풍경. 중앙의 초록빛 돔을 가진 헬싱키 대성당은 도시의 상징으로, 고전주의 건축 양식이 돋보이며 주변 건물들과 조화를 이룬다. 차분하면서도 고요한 북유럽의 분위기가 물씬 느껴지는 장면이다."
    ),
    ItemData(
        title = "베트남 나트랑 롱선사",
        imageId = R.drawable.vietnam,
        description = "베트남 나트랑에 위치한 롱선사(Long Sơn Pagoda)의 거대한 좌불상은 도시를 내려다보며 평화와 자비의 상징으로 서 있다. 높이 약 24미터에 이르는 이 흰색 부처상은 맑은 하늘과 조화를 이루며, 많은 관광객과 불자들이 찾는 명소다."
    ),
    ItemData(
        title = "일본 후지산",
        imageId = R.drawable.japan,
        description = "후지산을 배경으로 선명한 붉은 색의 충령탑이 어우러진 이 풍경은 일본의 전통과 자연미를 함께 느낄 수 있는 명소다. 봄에는 벚꽃, 가을에는 단풍으로 더욱 아름다워진다."
    ),
    ItemData(
        title = "네덜란드 킨더다이크",
        imageId = R.drawable.netherlands,
        description = "네덜란드의 전통 풍차 마을인 킨더다이크는 18세기에 건설된 19개의 풍차가 운하를 따라 줄지어 있어 아름다운 풍경을 자아낸다. 수위 조절을 위한 역사적 유산으로 세계문화유산에 등재되었다."
    ),
    ItemData(
        title = "영국 빅벤",
        imageId = R.drawable.uk,
        description = "영국 런던의 대표 명소인 빅벤과 국회의사당이 보이며, 고딕 양식의 건축미와 함께 템스강을 가로지르는 웨스트민스터 다리가 어우러져 클래식한 도시의 풍경을 자아낸다."
    ),
)

@Composable
fun Item(itemData: ItemData) {
    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        modifier = Modifier.padding(16.dp)
    ) {
        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            Image(
                painter = painterResource(id = itemData.imageId),
                contentDescription = itemData.description
            )
            Spacer(
                modifier = Modifier.size(8.dp)
            )
            Text(
                text = itemData.title,
                style = MaterialTheme.typography.headlineMedium
            )
            Spacer(
                modifier = Modifier.size(8.dp)
            )
            Text(
                text = itemData.description,
            )
        }
    }
}
@Composable
fun CatalogExample(itemList: List<ItemData>) {
    // 안드로이드의 Recycler View나 List View 같은 걸 대체하기 위함
    // 세로로 수천, 수만개의 리스트가 보인다면, LazyColumn을 사용
    LazyColumn {
        items(itemList) { item ->  // LazyItemScope : 강 항목에 대한 Scope
            Item(item)
        }

        // 만약 수동으로 지정해주고 싶다면
//        item {
//            Item(itemList[0])
//        }
    }
}


@Preview(showBackground = true)
@Composable
fun ItemPreview() {
    CatalogTheme {
        Item(
            ItemData(
                title = "대만의 한 거리",
                imageId = R.drawable.taiwan,
                description = "대만의 어느 도시 골목길. 양옆으로 다닥다닥 붙은 건물에는 오래된 에어컨 실외기와 창문형 차양이 달려 있고, 그 아래로는 주민들의 빨래가 바람에 나부낀다. 좁은 길에는 스쿠터들이 질서 없이 세워져 있고, 도로 가장자리에는 화분들이 놓여 있어 작지만 소박한 녹음을 만든다. 빛은 나뭇잎 사이로 스며들어 정적 속에 부드러운 생기를 더하며, 이 골목에서 살아가는 사람들의 조용한 일상이 고스란히 느껴진다."
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CatalogExample(items)
}