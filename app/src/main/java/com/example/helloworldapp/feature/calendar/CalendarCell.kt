package com.example.helloworldapp.feature.calendar

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.helloworldapp.ui.common.Dot
import com.example.helloworldapp.ui.common.RoundedSquareDot
import com.kizitonwose.calendar.core.CalendarDay
import com.kizitonwose.calendar.core.DayPosition

@Composable
fun CalendarCell(
    day: CalendarDay,
    status: DailyMealStatus?
) {
    Box(
        modifier = Modifier
            .aspectRatio(1f), // This is important for square sizing!
        contentAlignment = Alignment.Center
    ) {
        val textColor = if (day.position == DayPosition.MonthDate) {
            MaterialTheme.colorScheme.onSurface
        } else {
            MaterialTheme.colorScheme.primaryContainer
        }

        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = day.date.dayOfMonth.toString(),
                color = textColor
            )
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                val dotSize = 6.dp
                val spacerWidth = 2.dp

                if (status != null) {
                    RoundedSquareDot(active = status.hasLunch, color = Color.Blue, size = dotSize, cornerSize = 2.dp)
                    Spacer(Modifier.width(spacerWidth))
                    RoundedSquareDot(active = status.hasBreakfast, color = Color.Green, size = dotSize, cornerSize = 2.dp)
                    Spacer(Modifier.width(spacerWidth))
                    RoundedSquareDot(active = status.hasDinner, color = Color.Red, size = dotSize, cornerSize = 2.dp)
                } else {
                    Spacer(Modifier.height(dotSize))
                }
            }
        }

    }
}