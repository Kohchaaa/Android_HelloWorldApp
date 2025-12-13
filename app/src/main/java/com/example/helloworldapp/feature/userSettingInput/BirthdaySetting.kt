package com.example.helloworldapp.feature.userSettingInput

import android.annotation.SuppressLint
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.helloworldapp.R

@Composable
fun BirthdaySetting(
    currentDate: String,
    onButtonClick: () -> Unit
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
                    text = currentDate,
                    textAlign = TextAlign.Center,
                )
                Icon(
                    painter = painterResource(R.drawable.edit_calender_icon_white),
                    contentDescription = "Change Birthday"
                )
            }
        }
    )
}

@SuppressLint("DefaultLocale")
fun formatDate(year: Int, month: Int, day: Int): String{
    return String.format("%04d-%02d-%02d", year, month + 1, day)
}