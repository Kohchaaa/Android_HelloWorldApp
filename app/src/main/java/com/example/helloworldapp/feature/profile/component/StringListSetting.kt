package com.example.helloworldapp.feature.profile.component

import ChipTextField
import androidx.compose.runtime.Composable

@Composable
fun StringListSetting(
    label: String,
    chips: List<String>,
    onChipsChange: (List<String>) -> Unit,
) {
    SettingItem(
        itemName = label,
        content = {
            ChipTextField(
                chips = chips,
                onChipsChange = onChipsChange
            )
        }
    )
}