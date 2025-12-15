package com.example.helloworldapp.ui.theme

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


// Light Theme Colors
val LightGreen =      Color(0xFF38A3A5) // プライマリ: 信頼感のある青緑
val LightYellow =     Color(0xFFF9A825) // セカンダリ: ポジティブな印象の黄色
val LightPink =       Color(0xFFE57373) // ターシャリ/エラー: 注意を引く優しい赤
val LightBackground = Color(0xFFF7F9F9) // 背景: ほぼ白だが少し温かみのある色
val LightSurface =    Color(0xFFFFFFFF) // カードなどの表面: 純白
val LightOnSurfaceVariant = Color(0xFF595959) // 薄い文字用の色 (ライトテーマ用)

// Dark Theme Colors
val DarkGreen =      Color(0xFF57C9B8)  // プライマリ: ダークテーマで映える明るめの緑
val DarkYellow =     Color(0xFFFFC107)  // セカンダリ: ダークテーマでも視認性の良い黄色
val DarkPink =       Color(0xFFEF9A9A)  // ターシャリ/エラー: ダークテーマ用の優しい赤
val DarkBackground = Color(0xFF181818)  // 背景: 標準的なダークカラー
val DarkSurface =    Color(0xFF1E1E1E)  // カードなどの表面: 背景より少し明るいダークカラー
val DarkOnSurfaceVariant = Color(0xFFD0D0D0) // 薄い文字用の色 (ダークテーマ用)


private val DarkColorScheme = darkColorScheme(
    primary =      DarkGreen,
    secondary =    DarkYellow,
    tertiary =     DarkPink,
    background =   DarkBackground,
    surface =      DarkSurface,
    onPrimary =    Color.Black,        // DarkGreenの上の文字は黒の方が見やすい
    onSecondary =  Color.Black,
    onTertiary =   Color.Black,
    onBackground = Color(0xFFE6E6E6), // ほぼ白
    onSurface =    Color(0xFFE6E6E6),
    onSurfaceVariant = DarkOnSurfaceVariant
)

private val LightColorScheme = lightColorScheme(
    primary =      LightGreen,
    secondary =    LightYellow,
    tertiary =     LightPink,
    background =   LightBackground,
    surface =      LightSurface,
    onPrimary =    Color.White,        // LightGreenの上の文字は白が見やすい
    onSecondary =  Color.Black,
    onTertiary =   Color.White,
    onBackground = Color(0xFF1C1B1F), // ほぼ黒
    onSurface =    Color(0xFF1C1B1F),
    onSurfaceVariant = LightOnSurfaceVariant
)

@Composable
fun HelloWorldAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false,
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

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}