package com.example.helloworldapp.feature.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign

import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.helloworldapp.ui.theme.HelloWorldAppTheme

@Composable
fun NavigationTabItem(
    isSelected : Boolean,
    onClick : () -> Unit,
    icon : ImageVector,
    label : String
) {
    Surface(
        onClick = onClick,
        modifier = Modifier
            .background(MaterialTheme.colorScheme.surface)
            .padding(8.dp)

    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .background(if (isSelected) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.surface, shape = MaterialTheme.shapes.small)
                .padding(start = 16.dp, end = 16.dp, top = 4.dp, bottom = 4.dp)
        ) {
            Icon(
                imageVector = icon,
                contentDescription = label,
                tint = if (isSelected) MaterialTheme.colorScheme.onPrimaryContainer else MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.size(8.dp))
            Text(
                text = label,
                color = if (isSelected) MaterialTheme.colorScheme.onPrimaryContainer else MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.labelSmall,
                textAlign = TextAlign.Center
            )
        }

    }
}

@Preview
@Composable
fun NavigationTabItemPreview() {
    HelloWorldAppTheme() {
        NavigationTabItem(
            isSelected = true,
            onClick = {},
            icon = Icons.Default.Home,
            label = "Home"
        )
    }

}