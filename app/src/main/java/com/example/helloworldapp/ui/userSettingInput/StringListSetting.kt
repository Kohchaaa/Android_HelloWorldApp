package com.example.helloworldapp.ui.userSettingInput

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