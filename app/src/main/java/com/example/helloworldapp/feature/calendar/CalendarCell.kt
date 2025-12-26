package com.example.helloworldapp.feature.calendar

import android.R.attr.bottom
import android.R.attr.textColor
import android.R.attr.top
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SegmentedButtonDefaults.borderStroke
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.helloworldapp.ui.common.Dot
import com.example.helloworldapp.ui.common.RoundedSquareDot
import com.kizitonwose.calendar.core.CalendarDay
import com.kizitonwose.calendar.core.DayPosition
import java.time.LocalDate

@Composable
fun CalendarCell(
    day: CalendarDay,
    status: DailyMealStatus?,
    isSelected: Boolean,
    onDateSelected: () -> Unit,
    isToday: Boolean
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .aspectRatio(1 / 1.3f)
            .clickable(onClick = onDateSelected),
    ) {
        DayText(
            day = day,
            isSelected = isSelected,
            isToday = isToday
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

@Composable
fun DayText(
    day: CalendarDay,
    isSelected: Boolean,
    isToday: Boolean
) {
    /*===========================================
     * 色設定
     *===========================================*/
    val textColor = if (day.position == DayPosition.MonthDate) {
        MaterialTheme.colorScheme.onPrimaryContainer
    } else {
        MaterialTheme.colorScheme.onBackground
    }

    // 選択されていたら塗りつぶし色、そうでなければ透明
    val backgroundColor = if (isSelected) MaterialTheme.colorScheme.primaryContainer else Color.Transparent

    // 今日の場合のみ枠線を引く（選択されている時は枠線なし＝塗りつぶし優先）
    val borderStroke = if (isToday && !isSelected) {
        BorderStroke(1.dp, MaterialTheme.colorScheme.primary)
    } else null


    /*===========================================
     * UI
     *===========================================*/
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .padding(4.dp)
            .size(width = 40.dp, height = 30.dp) // 丸のサイズ
            .clip(RoundedCornerShape(6.dp)) // 丸く切り抜く
            .background(backgroundColor) // 選択時の背景
            .then( // 今日の枠線 (条件付きModifier)
                if (borderStroke != null) Modifier.border(borderStroke, RoundedCornerShape(6.dp))
                else Modifier
            )
    ) {
        Text(
            text = day.date.dayOfMonth.toString(),
            color = textColor,
        )
    }

}