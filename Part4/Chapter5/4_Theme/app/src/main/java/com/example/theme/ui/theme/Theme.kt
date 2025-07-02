package com.example.theme.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = Purple80,
    secondary = PurpleGrey80,
    tertiary = Pink80,
    surface = Color.Black,
    onSurface = Color.White,
)

// STEP 2) `onSurface` 값을 할당합니다.
// `surface` 값도 할당해봅시다.

// STEP 3) `primary`와 `onPrimary` 값도 바꾸어봅시다.
private val LightColorScheme = lightColorScheme(
    primary = Pink80,   // 버튼 배경색
    onPrimary = Color.Cyan, // 버튼 글자색
    secondary = PurpleGrey40,
    tertiary = Pink40,
    surface = Color.Yellow, // Card 배경색
    onSurface = Color.Blue, // Card 글자색

    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

@Composable
fun ThemeTheme(
    // Color 같은 경우, isSystemInDarkTheme() 이 있어서
    // Dark Theme이냐 아니냐에 따라서
    // Dark Color Scheme이냐? Light Color Scheme이냐가 결정됩니다.

    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false, // false 지정해야 lightColorScheme 적용 가능
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    // MaterialTheme은 LocalColorScheme을 가지고 있음...
    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}