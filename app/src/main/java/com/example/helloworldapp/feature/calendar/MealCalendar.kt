package com.example.helloworldapp.feature.calendar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.helloworldapp.type.MealRecord
import com.kizitonwose.calendar.compose.CalendarState
import com.kizitonwose.calendar.compose.HorizontalCalendar
import java.time.DayOfWeek

@Composable
fun MealCalendar(
    mealRecords: List<MealRecord>,
    state: CalendarState,
    daysOfWeek: List<DayOfWeek>
) {
    HorizontalCalendar(
        monthBody = { _, content ->
            Box(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.surface)
            ) {
                content() // Render the provided content!
            }

        },
        monthContainer = { _, container ->
            val configuration = LocalConfiguration.current
            val screenWidth = configuration.screenWidthDp.dp
            Box(
                modifier = Modifier
                    .width(screenWidth)
                    .padding(4.dp)
            ) {
                container() // Render the provided container!
            }
        },
        state = state,
        dayContent = { CalendarCell(day = it) },
        monthHeader = { month ->
            Text(
                text = "${month.yearMonth.year}年 ${month.yearMonth.month.value}月",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            DaysOfWeekTitle(daysOfWeek = daysOfWeek)
        }
    )
}