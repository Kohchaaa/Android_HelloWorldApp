package com.example.helloworldapp

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.helloworldapp.ui.theme.HelloWorldAppTheme
import com.example.helloworldapp.navigation.NavigationScreen

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            val viewModel: ThemeViewModel = viewModel()

            val isDarkTheme by viewModel.isDarkTheme.collectAsState()

            val themeController = remember(isDarkTheme, viewModel) {
                ThemeController(
                    isDarkTheme = isDarkTheme,
                    toggleTheme = viewModel::toggleTheme
                )
            }

            CompositionLocalProvider(LocalThemeController provides themeController) {
                HelloWorldAppTheme(
                    darkTheme = LocalThemeController.current.isDarkTheme
                ) {
                    NavigationScreen()
                }
            }
        }
    }
}
