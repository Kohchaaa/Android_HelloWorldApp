package com.example.helloworldapp.feature.setting.component

import android.annotation.SuppressLint
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.helloworldapp.R
import java.time.Instant
import java.time.ZoneId

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BirthdaySetting(
    selectedDate: String,
    onButtonClick: () -> Unit,
    dismissDatePicker: () -> Unit,
    onDateConfirm: (String) -> Unit,
    isShowDialog: Boolean = false,
    datePickerState: DatePickerState,
) {
    SettingItem(
        itemName = "誕生日",
        content = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier
                    .clickable { onButtonClick() }
                    .border(width = 1.dp, color = Color.Gray, shape = RoundedCornerShape(8.dp))
                    .padding(start = 20.dp, top = 8.dp, bottom = 8.dp, end = 20.dp)
            ) {
                Text(
                    text = selectedDate,
                    textAlign = TextAlign.Center,
                )
                Icon(
                    painter = painterResource(R.drawable.edit_calender_icon_white),
                    contentDescription = "Change Birthday"
                )
            }
        }
    )

    if (isShowDialog) {
        DatePickerDialog (
            onDismissRequest = dismissDatePicker, // ダイアログ外タップで閉じる
            confirmButton = {
                TextButton(
                    onClick = {
                        // 選択確定時の処理
                        datePickerState.selectedDateMillis?.let { millis ->
                            // ミリ秒からYYYY-MM-DDに変換
                            val newDateString = convertMillisToDateString(millis)
                            onDateConfirm(newDateString)
                        }
                        dismissDatePicker()
                    }
                ) {
                    Text("OK")
                }
            },
            dismissButton = {
                TextButton(onClick = dismissDatePicker) {
                    Text("Cancel")
                }
            }
        ) {
            // ダイアログの中身（カレンダー部分）
            DatePicker(state = datePickerState)
        }
    }
}

@SuppressLint("DefaultLocale")
fun formatDate(year: Int, month: Int, day: Int): String{
    return String.format("%04d-%02d-%02d", year, month + 1, day)
}

// ユーティリティ関数: ミリ秒 -> YYYY-MM-DD
fun convertMillisToDateString(millis: Long): String {
    val localDate = Instant.ofEpochMilli(millis)
        .atZone(ZoneId.of("UTC")) // DatePickerはUTC基準なのでUTCで受ける
        .toLocalDate()
    return localDate.toString() // "2025-12-14" のような形式になる
}