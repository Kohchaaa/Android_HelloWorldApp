package com.example.helloworldapp.ui.common

import android.R.attr.fontWeight
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun PageTitle(title: String) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colorScheme.primary)
            .padding(20.dp)
    ) {
        Text(
            text = title,
            fontSize = 24.sp,
            fontWeight = MaterialTheme.typography.titleLarge.fontWeight,
            color = MaterialTheme.colorScheme.onPrimary
        )
    }
}