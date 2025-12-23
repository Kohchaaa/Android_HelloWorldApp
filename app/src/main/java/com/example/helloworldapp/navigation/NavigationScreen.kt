package com.example.helloworldapp.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.helloworldapp.feature.main.MainScreen
import com.example.helloworldapp.feature.setting.SettingScreen

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NavigationScreen() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = MainRoute,
    ) {

        // メイン画面
        composable<MainRoute> {
            MainScreen(
                onNavigate = {
                    navController.navigate(SettingRoute)
                }
            )
        }

        // 設定画面
        composable<SettingRoute> {
            SettingScreen()
        }
    }
}