import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.helloworldapp.data.mock.mockMealRecord
import com.example.helloworldapp.feature.calendar.DailyMealStatus
import com.example.helloworldapp.feature.calendar.MealCalendar
import com.example.helloworldapp.type.MealType
import com.example.helloworldapp.ui.theme.HelloWorldAppTheme
import com.kizitonwose.calendar.compose.rememberCalendarState
import com.kizitonwose.calendar.core.OutDateStyle
import com.kizitonwose.calendar.core.daysOfWeek
import com.kizitonwose.calendar.core.firstDayOfWeekFromLocale
import java.time.LocalDate
import java.time.YearMonth

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CalendarScreen(

) {
    Scaffold(modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colorScheme.background)
    ) {
        var selectedDate by remember { mutableStateOf(LocalDate.now()) }

        val currentMonth = remember { YearMonth.now() }
        val startMonth = remember { currentMonth.minusMonths(24) }
        val endMonth = remember { currentMonth.plusMonths(1) }
        val firstDayOfWeek = remember { firstDayOfWeekFromLocale() }
        val daysOfWeek = remember { daysOfWeek() }

        val state = rememberCalendarState(
            startMonth = startMonth,
            endMonth = endMonth,
            firstVisibleMonth = currentMonth,
            firstDayOfWeek = firstDayOfWeek,
            outDateStyle = OutDateStyle.EndOfGrid
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

        Column(
            //verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            MealCalendar(
                mealStatus = mealStatus,
                state = state,
                daysOfWeek = daysOfWeek,
                selectedDate = selectedDate,
                onDateSelected = { selectedDate = it }
            )

            HorizontalDivider(thickness = 2.dp)
        }
    }
}