package com.example.helloworldapp.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

// セルに表示する用ドット
@Composable
fun RoundedSquareDot(
    active: Boolean,
    color: Color,
    size: Dp,
    cornerSize: Dp = 2.dp
) {
    Box(
        modifier = Modifier
            .size(size)
            .background(
                color = if (active) color else Color.Gray.copy(alpha = 0.1f),
                shape = RoundedCornerShape(cornerSize)
            )
    )
}