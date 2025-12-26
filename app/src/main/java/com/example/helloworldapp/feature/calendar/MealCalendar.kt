package com.example.helloworldapp.feature.calendar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.helloworldapp.data.mock.mockMealRecord
import com.example.helloworldapp.type.MealRecord
import com.example.helloworldapp.type.MealType
import com.example.helloworldapp.ui.theme.HelloWorldAppTheme
import com.kizitonwose.calendar.compose.CalendarState
import com.kizitonwose.calendar.compose.HorizontalCalendar
import com.kizitonwose.calendar.compose.rememberCalendarState
import com.kizitonwose.calendar.core.CalendarMonth
import com.kizitonwose.calendar.core.daysOfWeek
import com.kizitonwose.calendar.core.firstDayOfWeekFromLocale
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.YearMonth

@Composable
fun MealCalendar(
    //mealRecords: List<MealRecord>,
    mealStatus: Map<LocalDate, DailyMealStatus>,
    state: CalendarState,
    daysOfWeek: List<DayOfWeek>
) {


    HorizontalCalendar(
        monthBody = { _, content -> MonthBody(content = content) },
        monthContainer = { _, container -> MonthContainer(container = container) },
        state = state,
        dayContent = {
            CalendarCell(day = it, status = mealStatus[it.date])
        },
        monthHeader = { MonthHeader(it, daysOfWeek) }
    )
}

// カレンダー全体のコンテナ
@Composable
fun MonthContainer(
    container: @Composable () -> Unit
) {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    Box(
        modifier = Modifier
            .width(screenWidth)
            .padding(4.dp)
    ) {
        container() // Render the provided container!
    }
}

// カレンダーのヘッダー
@Composable
fun MonthHeader(
    month: CalendarMonth,
    daysOfWeek: List<DayOfWeek>
) {
    Column (
        modifier = Modifier.background(MaterialTheme.colorScheme.surface)
    ) {
        // 年月表示
        Text(
            text = "${month.yearMonth.year}年 ${month.yearMonth.month.value}月",
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )
        // 曜日表示
        DaysOfWeekTitle(daysOfWeek = daysOfWeek)
    }
}

// 日付部分
@Composable
fun MonthBody(
    content: @Composable () -> Unit
) {
    Box(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
    ) {
        content() // Render the provided content!
    }
}


@Preview
@Composable
fun MealCalendarPre() {
    HelloWorldAppTheme{

        val currentMonth = remember { YearMonth.now() }
        val startMonth = remember { currentMonth.minusMonths(24) }
        val endMonth = remember { currentMonth.plusMonths(1) }
        val firstDayOfWeek = remember { firstDayOfWeekFromLocale() }
        val daysOfWeek = remember { daysOfWeek() }

        val state = rememberCalendarState(
            startMonth = startMonth,
            endMonth = endMonth,
            firstVisibleMonth = currentMonth,
            firstDayOfWeek = firstDayOfWeek
        )

        val mealRecord = mockMealRecord

        // 食事レコードを，カレンダー表示に最適なデータに
        // 日付をキーとして，その日のMealTypeごとに食べたかどうかが真偽値で保存
        val mealStatus: Map<LocalDate, DailyMealStatus> = mealRecord
            .groupBy { it.date.toLocalDate() }
            .mapValues { (_, records) ->
                DailyMealStatus(
                    hasBreakfast = records.any { it.mealType == MealType.BREAKFAST },
                    hasLunch = records.any { it.mealType == MealType.LUNCH },
                    hasDinner = records.any { it.mealType == MealType.DINNER },
                    hasSnack = records.any { it.mealType == MealType.SNACK },
                    hasMidnight = records.any { it.mealType == MealType.MIDNIGHT }
                )
            }

        MealCalendar(
            mealStatus = mealStatus,
            state = state,
            daysOfWeek = daysOfWeek
        )
    }
}