import android.R.attr.fontWeight
import android.R.attr.type
import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BakeryDining
import androidx.compose.material.icons.rounded.ArrowForward
import androidx.compose.material.icons.rounded.DoubleArrow
import androidx.compose.material.icons.rounded.PlayArrow
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.helloworldapp.data.mock.mockMealRecord
import com.example.helloworldapp.feature.calendar.DailyMealStatus
import com.example.helloworldapp.feature.calendar.MealCalendar
import com.example.helloworldapp.feature.calendar.viewmodel.CalendarViewModel
import com.example.helloworldapp.type.MealRecord
import com.example.helloworldapp.type.MealType
import com.example.helloworldapp.type.backgroundColor
import com.example.helloworldapp.type.color
import com.example.helloworldapp.type.icon
import com.example.helloworldapp.ui.theme.HelloWorldAppTheme
import com.kizitonwose.calendar.compose.rememberCalendarState
import com.kizitonwose.calendar.core.OutDateStyle
import com.kizitonwose.calendar.core.daysOfWeek
import com.kizitonwose.calendar.core.firstDayOfWeekFromLocale
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.DateTimeFormatter

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CalendarScreen(
    viewModel : CalendarViewModel = viewModel()
) {
    Scaffold(modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colorScheme.background)
    ) {

        val dailyRecord by viewModel.dailyRecord.collectAsState()
        val mealStatus by viewModel.mealStatus.collectAsState()

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

        Column(
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

            // 選択した日付の食事レコードを表示
            val selectedMealRecord = dailyRecord[selectedDate] ?: emptyList()

            Column(

            ) {
                Text(
                    text = selectedMealRecord.firstOrNull()
                        ?.date
                        ?.format(DateTimeFormatter.ofPattern("MM月dd日"))
                        ?: selectedDate.format(DateTimeFormatter.ofPattern("MM月dd日")), // リストが空だった場合
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.surface)
                        .padding(16.dp)
                )

                if (selectedMealRecord.isNotEmpty()) {
                    LazyColumn(
                        contentPadding = PaddingValues(16.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(selectedMealRecord.size) { index ->
                            val record = selectedMealRecord[index]
                            Card(
                                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
                            ) {
                                ListItem(
                                    headlineContent = {
                                        Text(
                                            record.mealName,
                                            fontWeight = FontWeight.Bold
                                        )
                                    },
                                    supportingContent = {
                                        Text(
                                            text = record.date.format(
                                                DateTimeFormatter.ofPattern(
                                                    "HH:mm"
                                                )
                                            ),
                                            color = MaterialTheme.colorScheme.onSurface.copy(0.5f)
                                        )
                                    },
                                    leadingContent = {
                                        val type = record.mealType
                                        val mainColor = type.color
                                        val bgColor = type.backgroundColor
                                        val icon = type.icon

                                        // アイコン部分（丸い背景付き）
                                        Box(
                                            modifier = Modifier
                                                .size(40.dp) // アイコンのタッチエリアサイズ
                                                .clip(CircleShape) // 丸く切り抜く
                                                .background(bgColor), // 薄い背景色
                                            contentAlignment = Alignment.Center
                                        ) {
                                            Icon(
                                                imageVector = icon,
                                                contentDescription = type.label,
                                                tint = mainColor, // アイコン本体は濃い色
                                                modifier = Modifier.size(24.dp)
                                            )
                                        }
                                    },
                                    trailingContent = {
                                        Icon(
                                            imageVector = Icons.Rounded.DoubleArrow,
                                            contentDescription = "詳細",
                                            tint = MaterialTheme.colorScheme.primary
                                        )
                                    }
                                )
                            }
                        }
                    }
                } else {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text("データがありません😭", color = Color.Gray)
                    }
                }
            }

        }
    }
}