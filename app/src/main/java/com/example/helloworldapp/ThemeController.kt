package com.example.helloworldapp

import androidx.compose.runtime.staticCompositionLocalOf

data class ThemeController(
    val isDarkTheme: Boolean,
    val toggleTheme: () -> Unit
)

val LocalThemeController = staticCompositionLocalOf<ThemeController> {
    error("LocalThemeControllerが提供されていません")
}
