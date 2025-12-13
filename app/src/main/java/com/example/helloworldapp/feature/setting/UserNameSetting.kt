package com.example.helloworldapp.feature.setting

import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.setTextAndPlaceCursorAtEnd
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun UserNameSetting(
    userName: TextFieldState,
    currentUserName: String,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isFocused by interactionSource.collectIsFocusedAsState()

    val borderColor = if (isFocused) {
        MaterialTheme.colorScheme.primary
    } else {
        MaterialTheme.colorScheme.outline
    }

    SettingItem(
        itemName = "ニックネーム",
        content = {
            BasicTextField(
                state = userName,
                interactionSource = interactionSource,
                lineLimits = TextFieldLineLimits.SingleLine,
                textStyle = LocalTextStyle
                    .current
                    .copy(
                        color = MaterialTheme.colorScheme.onSurface,
                        fontSize = 16.sp,
                        lineHeight = 24.sp
                    ),
                onKeyboardAction = { userName.setTextAndPlaceCursorAtEnd(currentUserName) },
                modifier = Modifier
                    .width(200.dp)
                    .border(
                        width = 1.dp,
                        color = borderColor,
                        shape = RoundedCornerShape(8.dp)
                    )
                    .padding(10.dp)
            )
        }
    )
}