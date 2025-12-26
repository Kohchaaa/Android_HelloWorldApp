package com.example.helloworldapp.feature.main

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.helloworldapp.navigation.CalendarRoute
import com.example.helloworldapp.navigation.HomeRoute
import com.example.helloworldapp.navigation.ProfileRoute
import com.example.helloworldapp.navigation.SuggestRoute
import kotlin.reflect.KClass

enum class MainScreenTabs(
    val route: Any,             // 遷移先に使うインスタンス
    val routeClass: KClass<*>,  // 現在位置の判定に使うクラス
    val icon: ImageVector,
    val label: String
) {
    Home(
        route = HomeRoute,
        routeClass = HomeRoute::class,
        icon = Icons.Default.Home,
        label = "Home"
    ),
    Calendar(
        route = CalendarRoute,
        routeClass = CalendarRoute::class,
        icon = Icons.Default.DateRange,
        label = "Calendar"
    ),
    Suggest(
        route = SuggestRoute,
        routeClass = SuggestRoute::class,
        icon = Icons.Default.Notifications,
        label = "Suggest"
    ),
    UserSetting(
        route = ProfileRoute,
        routeClass = ProfileRoute::class,
        icon = Icons.Default.AccountCircle,
        label = "Profile"
    )
}