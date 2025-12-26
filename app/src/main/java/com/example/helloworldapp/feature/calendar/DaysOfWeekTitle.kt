package com.example.helloworldapp.feature.calendar

import android.R.attr.bottom
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.time.DayOfWeek
import java.time.format.TextStyle
import java.util.Locale

@Composable
fun DaysOfWeekTitle(daysOfWeek: List<DayOfWeek>) {

    val weekTitlePadding = 8.dp

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = weekTitlePadding, bottom = weekTitlePadding)
    ) {
        for (dayOfWeek in daysOfWeek) {

            val color = if (dayOfWeek == DayOfWeek.SATURDAY) Color.Blue else if (dayOfWeek == DayOfWeek.SUNDAY) Color.Red else Color.Gray

            Text(
                fontSize = 12.sp,
                color = color,
                textAlign = TextAlign.Center,
                text = dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.getDefault()),
                modifier = Modifier.weight(1f),
            )
        }
    }
}