package com.example.helloworldapp.feature.main

import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun TestButton(label: String = "Button", onClick: () -> Unit){
    Button(
        onClick = onClick,
    ) {
        Text(
            text = label,
            color = MaterialTheme.colorScheme.onPrimary
        )
    }
}