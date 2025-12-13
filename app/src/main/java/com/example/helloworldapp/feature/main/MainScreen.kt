package com.example.helloworldapp.feature.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.example.helloworldapp.changeActivity

@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            Greeting(
                name = "kohcha",
            )
            TestButton(label = "Go to UserInput", onClick = { changeActivity(context) })
        }
    }
}