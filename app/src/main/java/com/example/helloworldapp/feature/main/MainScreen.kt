package com.example.helloworldapp.feature.main

import CalendarScreen
import SuggestScreen
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.helloworldapp.feature.home.HomeScreen
import com.example.helloworldapp.feature.profile.ProfileScreen
import com.example.helloworldapp.navigation.CalendarRoute
import com.example.helloworldapp.navigation.HomeRoute
import com.example.helloworldapp.navigation.ProfileRoute
import com.example.helloworldapp.navigation.SuggestRoute

@Composable
fun MainScreen(
    onNavigateSetting : () -> Unit
) {
    val mainNavController = rememberNavController()
    val navBackStackEntry by mainNavController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    Scaffold(
        bottomBar = {
            NavigationBar(
                currentDestination = currentDestination,
                onNavigateOtherTab = { destination -> mainNavController.navigate(destination) }
            )
        },
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) { innerPadding ->

        NavHost(
            navController = mainNavController,
            startDestination = HomeRoute,
            enterTransition = { fadeIn(animationSpec = tween(300)) }, // 画面が出るとき
            exitTransition = { fadeOut(animationSpec = tween(300)) },  // 画面が消えるとき
            popEnterTransition = { fadeIn(animationSpec = tween(300)) }, // 「戻る」で戻ってきたとき
            popExitTransition = { fadeOut(animationSpec = tween(300)) },  // 「戻る」で消えるとき
            modifier = Modifier.padding(innerPadding)
        ) {
            composable<HomeRoute> {
                HomeScreen(
                    onNavigateSetting = onNavigateSetting
                )
            }

            composable<CalendarRoute> {
                CalendarScreen()
            }

            composable<SuggestRoute> {
                SuggestScreen()
            }

            composable<ProfileRoute> {
                ProfileScreen()
            }
        }


    }
}