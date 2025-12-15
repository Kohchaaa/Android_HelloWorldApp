package com.example.helloworldapp.feature.setting.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedIconToggleButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.helloworldapp.feature.setting.component.Gender

@Composable
fun GenderSetting(
    selectedGender: Gender?,
    onGenderSelected: (Gender) -> Unit
) {
    SettingItem(
        itemName = "性別",
        content = {
            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Gender.entries.forEach { gender ->
                    OutlinedIconToggleButton(
                        checked = gender == selectedGender,
                        onCheckedChange = { onGenderSelected(gender) },
                        modifier = Modifier
                            .height(50.dp)
                            .weight(1f),
                        shape = RoundedCornerShape(12.dp),
                        colors = IconButtonDefaults.outlinedIconToggleButtonColors(
                            containerColor = MaterialTheme.colorScheme.surface,
                            checkedContainerColor = MaterialTheme.colorScheme.primary,
                            checkedContentColor = MaterialTheme.colorScheme.onPrimary
                        )
                    ) {
                        Text(
                            text = gender.label,
                            fontSize = 14.sp
                        )
                    }
                }
            }
        }
    )
}