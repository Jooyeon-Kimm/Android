package com.example.theme

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.LocalAbsoluteTonalElevation
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.theme.ui.theme.ThemeTheme

class MainActivity : ComponentActivity() {
    // {onCreate}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            // STEP 1) ThemeTheme으로 이동합니다.
            ThemeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ThemeExample()
                }
            }
        }
    }
}

@Composable
fun ThemeExample() {
    Card(
        modifier = Modifier.padding(8.dp),
        colors = androidx.compose.material3.CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        // M3 (Material3)에서는 LocalContentAlpha가 사라짐
        CompositionLocalProvider(
            LocalContentColor provides MaterialTheme.colorScheme.onSurface.copy(alpha = 0.38f)
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
            ) {
                Text("안녕하세요. 그대로멈춰라")
                Text("라안녕하세요. 그대로멈춰")
                Text("춰라안녕하세요. 그대로멈")
                Text("멈춰라안녕하세요. 그대로")
                Text("로멈춰라안녕하세요. 그대")
                Text("대로멈춰라안녕하세요. 그")
                Text("그대로멈춰라안녕하세요.")
                Button(
                    onClick = { /* TODO */ },
                    modifier = Modifier.padding(top = 16.dp)
                ) {
                    Text("그대로 멈춰 버튼") // Theme.kt // onPrimary
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ThemePreview() {
    ThemeTheme {
        Surface(
            color = MaterialTheme.colorScheme.surface
        ) {
            ThemeExample()
        }
    }
}