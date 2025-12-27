package com.example.helloworldapp.feature.home

import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.helloworldapp.LocalThemeController
import com.example.helloworldapp.feature.main.Greeting
import com.example.helloworldapp.feature.main.TestButton

@Composable
fun HomeScreen(
    onNavigateSetting: () -> Unit,
    onToggleTheme: () -> Unit,
    isDarkTheme: Boolean
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
    ) {
        Greeting(
            name = "Kohcha.",
        )
        TestButton(label = "Go to UserInput", onClick = { onNavigateSetting() })
        Button(
            onClick = { onToggleTheme() }
        ) {
            Icon(
                imageVector = if (isDarkTheme) Icons.Default.DarkMode else Icons.Default.LightMode,
                contentDescription = "テーマ切り替え",
                tint = if (isDarkTheme) Color.White else Color.Black
            )
        }

    }
}