package com.example.helloworldapp.feature.setting

import MapDataInput
import androidx.compose.runtime.Composable

@Composable
fun CustomAttributeSetting(
    customAttributes: Map<String, String>,
    onDataChange: (Map<String, String>) -> Unit
) {
    SettingItem(
        itemName = "カスタム属性",
        content = {
            MapDataInput(
                mapData = customAttributes,
                onDataChange = onDataChange
            )
        },
        isRow = false
    )
}