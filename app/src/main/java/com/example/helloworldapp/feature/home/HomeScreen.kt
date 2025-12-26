package com.example.helloworldapp.feature.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.helloworldapp.feature.main.Greeting
import com.example.helloworldapp.feature.main.TestButton

@Composable
fun HomeScreen(
    onNavigateSetting: () -> Unit
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
    }
}