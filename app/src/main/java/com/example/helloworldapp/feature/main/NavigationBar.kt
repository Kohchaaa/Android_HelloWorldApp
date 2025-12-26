package com.example.helloworldapp.feature.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import com.example.helloworldapp.ui.theme.HelloWorldAppTheme

@Composable
fun NavigationBar(
    currentDestination: NavDestination?,
    onNavigateOtherTab: (Any) -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.SpaceAround,
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surface)
    ) {
        MainScreenTabs.entries.forEachIndexed { index, tab ->

            val isSelected = currentDestination?.hasRoute(tab.routeClass) == true

            NavigationTabItem(
                icon = tab.icon,
                label = tab.label,
                isSelected = isSelected,
                onClick = {
                    onNavigateOtherTab(tab.route)
                },
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NavigationBarPreview() {
    HelloWorldAppTheme {
        Scaffold(
            modifier = Modifier.fillMaxSize()
        ) {
            Row(
                modifier = Modifier.padding(it)
            ) {
                Box(modifier = Modifier.padding(it))
                MainScreen(
                    onNavigateSetting = {}
                )
            }

        }
    }
}