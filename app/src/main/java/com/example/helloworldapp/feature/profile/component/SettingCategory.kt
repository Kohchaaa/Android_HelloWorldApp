package com.example.helloworldapp.feature.profile.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.helloworldapp.ui.theme.HelloWorldAppTheme
import java.time.LocalDate

@Composable
fun SettingCategory(
    categoryName: String,
    categoryIcon: ImageVector,
    content: @Composable () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        // Category Title
        Row(
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = categoryIcon,
                contentDescription = categoryName,
                modifier = Modifier.size(20.dp),
                tint = MaterialTheme.colorScheme.secondary)
            Text(
                text = categoryName,
                color = MaterialTheme.colorScheme.secondary,
                lineHeight = 24.sp
            )
        }

        Column(
            verticalArrangement = Arrangement.spacedBy(20.dp),
            modifier = Modifier.padding(top = 12.dp)
        ) {
            content()
        }
    }
}



@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun SettingCategoryPre(){
    HelloWorldAppTheme {
        Column() {
            SettingCategory(
                categoryName = "テスト",
                categoryIcon = Icons.Default.Info,
            ) {
                // SettingCategoryのcontentラムダ内に、
                // プレビューしたいコンポーザブルを直接配置します。

                // ここで実際のコンポーザブルを呼び出す
                UserNameSetting(
                    userName = rememberTextFieldState("Taro Yamada"),
                    currentUserName = "Taro Yamada"
                )

                BirthdaySetting(
                    selectedDate = LocalDate.now(),
                    onButtonClick = {},
                    dismissDatePicker = {},
                    onDateConfirm = {},
                    isShowDialog = false,
                    datePickerState = rememberDatePickerState()
                )

                GenderSetting(
                    selectedGender = Gender.MALE,
                    onGenderSelected = {}
                )

            }

            SettingCategory(
                categoryName = "Account",
                categoryIcon = Icons.Default.AccountBox
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.padding(top = 8.dp) // カテゴリ名との間に余白を追加
                ) {
                    Text("アカウント設定してここで")
                }
            }
        }
    }
}
