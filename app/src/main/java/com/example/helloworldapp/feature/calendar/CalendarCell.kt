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
import com.example.helloworldapp.type.MealType
import com.example.helloworldapp.type.color
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
        val isCurrentMonth = day.position == DayPosition.MonthDate

        // 日付表示
        DayText(
            day = day,
            isSelected = isSelected,
            isToday = isToday
        )

        if (status != null) {
            MealDotsCanvas(
                status = status,
                isCurrentMonth = isCurrentMonth
            )
        } else {
            // ステータスがない時のスペース確保（Canvasと同じ高さ）
            Spacer(Modifier.height(14.dp))
        }

/*        // 3食分のドット
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            val dotSize = 6.dp
            val spacerWidth = 2.dp

            if (status != null) {
                RoundedSquareDot(active = status.hasBreakfast, color = breakfastDotColor, size = dotSize, cornerSize = 2.dp)
                Spacer(Modifier.width(spacerWidth))
                RoundedSquareDot(active = status.hasLunch, color = lunchDotColor, size = dotSize, cornerSize = 2.dp)
                Spacer(Modifier.width(spacerWidth))
                RoundedSquareDot(active = status.hasDinner, color = dinnerDotColor, size = dotSize, cornerSize = 2.dp)
            } else {
                Spacer(Modifier.height(dotSize))
            }
        }

        Spacer(Modifier.height(6.dp))

        // 間食と夜食の分
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            val dotSize = 4.dp
            val spacerWidth = 2.dp



            if (status != null) {
                RoundedSquareDot(active = status.hasSnack, color = snackDotColor, size = dotSize, cornerSize = 2.dp)
                Spacer(Modifier.width(spacerWidth))
                RoundedSquareDot(active = status.hasMidnight, color = midnightDotColor, size = dotSize, cornerSize = 2.dp)
            } else {
                Spacer(Modifier.height(dotSize))
            }
        }*/
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
        MaterialTheme.colorScheme.onBackground.copy(0.3f)
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


private const val MISSING_MEAL_ALPHA = 0.15f // 未摂取時の薄さ (15%)
private const val INACTIVE_MONTH_ALPHA = 0.3f // 当月以外の日の薄さ (30%)
val MISSING_MEAL_COLOR = Color(0xFF222222)


@Composable
fun MealDotsCanvas(
    status: DailyMealStatus,
    isCurrentMonth: Boolean
) {
    androidx.compose.foundation.Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .height(14.dp)
    ) {
        val dotSizeMain = 6.dp.toPx()
        val dotSizeSub = 4.dp.toPx()
        val cornerRadius = 2.dp.toPx()
        val spacing = 2.dp.toPx()
        val centerX = size.width / 2

        // ■ 色決定ロジック
        fun getDotColor(mealColor: Color, hasEaten: Boolean): Color {
            // 1. ベースの色を決める (食べてればその色、食べてなければ指定のグレー)
            val baseColor = if (hasEaten) mealColor else MISSING_MEAL_COLOR

            // 2. 当月以外なら薄くする (グレーの場合も、当月以外のグレーはさらに薄くするのが自然)
            return if (isCurrentMonth) {
                baseColor
            } else {
                baseColor.copy(alpha = INACTIVE_MONTH_ALPHA)
            }
        }

        // 共通描画関数
        fun drawMealDot(
            mealType: MealType,
            hasEaten: Boolean,
            x: Float,
            y: Float,
            dotSize: Float
        ) {
            drawRoundRect(
                color = getDotColor(mealType.color, hasEaten),
                topLeft = androidx.compose.ui.geometry.Offset(x, y),
                size = androidx.compose.ui.geometry.Size(dotSize, dotSize),
                cornerRadius = androidx.compose.ui.geometry.CornerRadius(cornerRadius)
            )
        }

        // ==========================================
        // 上段: 朝・昼・夕
        // ==========================================
        val row1Y = 0f
        val row1TotalWidth = (dotSizeMain * 3) + (spacing * 2)
        val row1StartX = centerX - (row1TotalWidth / 2)

        drawMealDot(MealType.BREAKFAST, status.hasBreakfast, row1StartX, row1Y, dotSizeMain)
        drawMealDot(MealType.LUNCH, status.hasLunch, row1StartX + dotSizeMain + spacing, row1Y, dotSizeMain)
        drawMealDot(MealType.DINNER, status.hasDinner, row1StartX + (dotSizeMain + spacing) * 2, row1Y, dotSizeMain)

        // ==========================================
        // 下段: 間食・夜食
        // ==========================================
        val row2Y = dotSizeMain + 4.dp.toPx()
        val row2TotalWidth = (dotSizeSub * 2) + spacing
        val row2StartX = centerX - (row2TotalWidth / 2)

        drawMealDot(MealType.SNACK, status.hasSnack, row2StartX, row2Y, dotSizeSub)
        drawMealDot(MealType.MIDNIGHT, status.hasMidnight, row2StartX + dotSizeSub + spacing, row2Y, dotSizeSub)
    }
}