package com.example.helloworldapp.feature.main

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "hello my name is $name",
        modifier = modifier
            .background(MaterialTheme.colorScheme.surface)
            .border(1.dp, color = MaterialTheme.colorScheme.outline)
            .padding(20.dp)
    )
}