package com.example.helloworldapp.ui.userSettingInput

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SettingItem(
    itemName: String,
    content: @Composable () -> Unit,
    isRow: Boolean = true,
    modifier: Modifier = Modifier
        .fillMaxWidth()
        .background(color = MaterialTheme.colorScheme.surface)
        .padding(horizontal = 20.dp, vertical = 20.dp)
) {
    if(isRow) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = modifier
        ) {
            Text(
                text = itemName,
                modifier = Modifier.padding(end = 16.dp)
            )
            content()
        }
    } else {
        Column (
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.Start,
            modifier = modifier
        ) {
            Text(
                text = itemName,
                modifier = Modifier.padding(end = 16.dp)
            )
            content()
        }
    }

}