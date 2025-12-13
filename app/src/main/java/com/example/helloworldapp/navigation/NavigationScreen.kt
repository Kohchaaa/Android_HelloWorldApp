package com.example.helloworldapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.helloworldapp.feature.main.MainScreen
import com.example.helloworldapp.feature.setting.SettingScreen

@Composable
fun NavigationScreen() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = MainRoute,
    ) {
        composable<MainRoute> {
            MainScreen()
        }

        composable<SettingRoute> {
            SettingScreen()
        }
    }
}