package com.example.helloworldapp.feature.profile.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.helloworldapp.ui.theme.HelloWorldAppTheme

@Composable
fun SettingItem(
    itemName: String? = null,
    content: @Composable () -> Unit,
    isRow: Boolean = false,
) {
    val modifier: Modifier
        = Modifier.fillMaxWidth()

    if(isRow) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = modifier
        ) {
            if (itemName != null) {
                Text(
                    text = itemName,
                    modifier = Modifier.padding(end = 16.dp)
                )
            }
            content()
        }
    } else {
        Column (
            verticalArrangement = Arrangement.spacedBy(4.dp),
            horizontalAlignment = Alignment.Start,
            modifier = modifier
        ) {
            if (itemName != null) {
                Text(
                    text = itemName,
                    fontSize = 14.sp,
                    fontWeight = MaterialTheme.typography.titleMedium.fontWeight,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(end = 16.dp)
                )
            }
            content()
        }
    }
}


@Composable
@Preview
fun UserNameSettingPre(){
    HelloWorldAppTheme {
        UserNameSetting(
            userName = rememberTextFieldState("test field state"),
            currentUserName = "text name"
        )
    }
}