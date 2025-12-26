package com.example.helloworldapp.feature.calendar

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kizitonwose.calendar.compose.HorizontalCalendar
import com.kizitonwose.calendar.compose.rememberCalendarState
import com.kizitonwose.calendar.core.CalendarDay
import com.kizitonwose.calendar.core.DayPosition
import com.kizitonwose.calendar.core.firstDayOfWeekFromLocale
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.TextStyle
import java.util.Locale

// --- 1. データモデル（仮） ---
// 本来はRoomなどのDBから取得するデータクラス
data class DailyRecord(
    val id: String,
    val date: LocalDate,
    val title: String,
    val value: String // 何かの数値や記録
)

@Composable
fun AICalendar() {
    // --- 2. 状態管理とダミーデータ ---
    val currentMonth = remember { YearMonth.now() }
    val startMonth = remember { currentMonth.minusMonths(12) } // 前後1年分
    val endMonth = remember { currentMonth.plusMonths(12) }

    // 選択された日付（初期値は今日）
    var selectedDate by remember { mutableStateOf(LocalDate.now()) }

    // ダミーデータ生成（データがある日を作る）
    // ※実際はViewModelから flow.collectAsState() 等で受け取る
    val events = remember {
        mapOf(
            LocalDate.now() to listOf(
                DailyRecord("1", LocalDate.now(), "朝の運動", "30分"),
                DailyRecord("2", LocalDate.now(), "昼食", "パスタ")
            ),
            LocalDate.now().minusDays(2) to listOf(
                DailyRecord("3", LocalDate.now().minusDays(2), "読書", "1時間")
            ),
            LocalDate.now().plusDays(5) to listOf(
                DailyRecord("4", LocalDate.now().plusDays(5), "買い物", "スーパー")
            )
        )
    }

    // カレンダーの状態
    val state = rememberCalendarState(
        startMonth = startMonth,
        endMonth = endMonth,
        firstVisibleMonth = currentMonth,
        firstDayOfWeek = firstDayOfWeekFromLocale() // スマホの言語設定に合わせて月曜/日曜始まりを自動設定
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // --- 3. カレンダーヘッダー（曜日） ---
        // ライブラリにはヘッダーがないので自作する
        DaysOfWeekHeader()

        // --- 4. カレンダー本体 ---
        HorizontalCalendar(
            state = state,
            dayContent = { day ->
                val isSelected = selectedDate == day.date
                val hasData = events.containsKey(day.date)

                DayCell(
                    day = day,
                    isSelected = isSelected,
                    hasData = hasData,
                    onClick = { selectedDate = day.date }
                )
            },
            monthHeader = { month ->
                // 月のタイトル（例: 2025年 12月）
                Text(
                    text = "${month.yearMonth.year}年 ${month.yearMonth.month.value}月",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 10.dp),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
            }
        )

        HorizontalDivider(color = Color.LightGray)

        // --- 5. 選択された日のデータ一覧 ---
        // カレンダーの下にリストを表示
        val selectedEvents = events[selectedDate] ?: emptyList()

        if (selectedEvents.isEmpty()) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text("データがありません", color = Color.Gray)
            }
        } else {
            LazyColumn(
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(selectedEvents) { event ->
                    Card(
                        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                        colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F5F5))
                    ) {
                        ListItem(
                            headlineContent = { Text(event.title, fontWeight = FontWeight.Bold) },
                            supportingContent = { Text("記録: ${event.value}") },
                            leadingContent = {
                                Box(
                                    modifier = Modifier
                                        .size(40.dp)
                                        .clip(CircleShape)
                                        .background(MaterialTheme.colorScheme.primary),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = event.date.dayOfMonth.toString(),
                                        color = Color.White
                                    )
                                }
                            }
                        )
                    }
                }
            }
        }
    }
}

// --- コンポーネント: 1日分のセル ---
@Composable
fun DayCell(
    day: CalendarDay,
    isSelected: Boolean,
    hasData: Boolean,
    onClick: (CalendarDay) -> Unit,
) {
    // 別の月の日付（カレンダーの埋め合わせ）はタップ無効＆薄く表示
    if (day.position != DayPosition.MonthDate) {
        // 薄く数字だけ出すか、何も出さないか選べる。ここでは薄く出す
        Box(
            modifier = Modifier
                .aspectRatio(1f)
                .padding(6.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(text = day.date.dayOfMonth.toString(), color = Color.LightGray)
        }
        return
    }

    Box(
        modifier = Modifier
            .aspectRatio(1f) // 正方形を維持
            .padding(6.dp)
            .clip(RoundedCornerShape(8.dp)) // 角丸
            .background(if (isSelected) MaterialTheme.colorScheme.primary else Color.Transparent)
            .clickable { onClick(day) },
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // 日付テキスト
            Text(
                text = day.date.dayOfMonth.toString(),
                color = if (isSelected) Color.White else Color.Black,
                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
            )

            // データがある場合のドット（選択中は白、未選択はアクセント色）
            if (hasData) {
                Spacer(modifier = Modifier.height(4.dp))
                Box(
                    modifier = Modifier
                        .size(6.dp)
                        .clip(CircleShape)
                        .background(if (isSelected) Color.White else Color(0xFFFF5722)) // ドットの色
                )
            }
        }
    }
}

// --- コンポーネント: 曜日ヘッダー ---
@Composable
fun DaysOfWeekHeader() {
    val daysOfWeek = remember {
        val firstDayOfWeek = firstDayOfWeekFromLocale()
        // 曜日リストを並び替えて取得
        val days = DayOfWeek.values()
        // firstDayOfWeekに合わせて並び替えるロジックが必要だが、
        // 簡易的に月～日で固定、またはLocaleに従って生成
        // ここでは簡易実装として「月火水木金土日」を表示する例
        // ※厳密には firstDayOfWeekFromLocale() に合わせて回転させる必要があります
        listOf(DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY, DayOfWeek.THURSDAY, DayOfWeek.FRIDAY, DayOfWeek.SATURDAY, DayOfWeek.SUNDAY)
    }

    Row(modifier = Modifier.fillMaxWidth()) {
        for (dayOfWeek in daysOfWeek) {
            Text(
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Center,
                text = dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.JAPAN), // "月", "火"...
                fontSize = 12.sp,
                color = if (dayOfWeek == DayOfWeek.SUNDAY) Color.Red else Color.Gray,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

@Preview
@Composable
fun AICalendarPreview() {
    AICalendar()
}