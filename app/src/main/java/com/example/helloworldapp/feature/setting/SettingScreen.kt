package com.example.helloworldapp.feature.setting

import AllergenSelectSection
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.helloworldapp.feature.setting.component.AllergenMockData
import com.example.helloworldapp.feature.setting.component.BirthdaySetting
import com.example.helloworldapp.feature.setting.component.CustomAttributeSetting
import com.example.helloworldapp.feature.setting.component.Gender
import com.example.helloworldapp.feature.setting.component.GenderSetting
import com.example.helloworldapp.feature.setting.component.StringListSetting
import com.example.helloworldapp.feature.setting.component.UserNameSetting
import com.example.helloworldapp.feature.setting.component.formatDate
import com.example.helloworldapp.ui.common.PageTitle
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.serialization.json.Json
import java.time.LocalDate

@OptIn(ExperimentalMaterial3Api::class, FlowPreview::class)
@Composable
fun SettingScreen() {
    // state
    val userNameState = rememberTextFieldState("")
    var userName by rememberSaveable { mutableStateOf("") }
    var selectedDate by rememberSaveable { mutableStateOf("YYYY-MM-DD") }
    var selectedGender by rememberSaveable { mutableStateOf<Gender?>(null) }
    var selectedAllergen by rememberSaveable { mutableStateOf( setOf<String>()) }
    var dislikesIngredients by rememberSaveable { mutableStateOf(listOf<String>()) }
    var dislikesDishes by rememberSaveable { mutableStateOf(listOf<String>()) }
    var customAttributes by rememberSaveable { mutableStateOf(mapOf<String, String>("" to "")) }
    var userSettingJson by remember { mutableStateOf("") }

    var isShowDatePicker by rememberSaveable { mutableStateOf(false) }
    val dataPickerState = rememberDatePickerState()


    // hook
    LaunchedEffect(userNameState) {
        snapshotFlow { userNameState.text }
            .debounce(300)
            .distinctUntilChanged()
            .collect { text ->
                userName = text.toString()
            }
    }

    // function
    val datePickerCallback = { year: Int, month: Int, day: Int ->
        selectedDate = formatDate(year, month, day)
    }


    Scaffold(modifier = Modifier
        .fillMaxSize()
    ) { innerPadding ->
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .background(color = MaterialTheme.colorScheme.background)
                .imePadding()
        ) {
            // 実際の設定項目のほう
            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(innerPadding)
            ) {

                PageTitle(title = "設定")

                UserNameSetting(
                    userName = userNameState,
                    currentUserName = userName
                )

                BirthdaySetting(
                    selectedDate = selectedDate,
                    onButtonClick = {
                        isShowDatePicker = true
                    },
                    dismissDatePicker = {
                        isShowDatePicker = false
                    },
                    onDateConfirm = { newDate ->
                        selectedDate = newDate
                    },
                    isShowDialog = isShowDatePicker,
                    datePickerState = dataPickerState
                )

                GenderSetting(
                    selectedGender = selectedGender,
                    onGenderSelected = { gender ->
                        selectedGender = gender
                    }
                )

                AllergenSelectSection(
                    allAllergens = AllergenMockData.all,
                    selectedAllergens = selectedAllergen,
                    onToggle = { clickedValue ->
                        // Setの更新ロジック
                        selectedAllergen = if (selectedAllergen.contains(clickedValue)) {
                            selectedAllergen - clickedValue
                        } else {
                            selectedAllergen + clickedValue
                        }
                    },

                    )

                StringListSetting(
                    label = "嫌いな食材",
                    chips = dislikesIngredients,
                    onChipsChange = { chips ->
                        dislikesIngredients = chips
                    }
                )

                StringListSetting(
                    label = "嫌いな料理",
                    chips = dislikesDishes,
                    onChipsChange = { dislikeDishes ->
                        dislikesDishes = dislikeDishes
                    }
                )

                CustomAttributeSetting(
                    customAttributes = customAttributes,
                    onDataChange = { newMap ->
                        customAttributes = newMap
                    }
                )
            }

            // デバッグ用
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(16.dp)
            ) {
                Button(
                    onClick = {
                        val birthDateLocalDate = try {
                            // "YYYY-MM-DD"形式の文字列をLocalDateにパース(解析)する
                            LocalDate.parse(selectedDate)
                        } catch (e: Exception) {
                            LocalDate.now() // 例として現在の日付を入れる
                        }

                        // 1. 各StateからUserInputインスタンスを生成
                        val userInputData = UserInput(
                            displayName = userName,
                            birthDate = birthDateLocalDate,
                            gender = selectedGender,
                            allergies = selectedAllergen,
                            dislikeIngredients = dislikesIngredients,
                            dislikeDishes = dislikesDishes,
                            customAttributes = customAttributes,
                            intakeTargetVersion = "2024_v1" // 仮の値
                        )

                        // Json Format Setting
                        val prettyJson = Json {
                            prettyPrint = true
                            prettyPrintIndent = "    "
                        }

                        // 2. UserInputオブジェクトをJSON文字列に変換
                        userSettingJson = prettyJson.encodeToString(userInputData )
                    }
                ) {
                    Text("現在の入力内容をJSONで表示")
                }
                // 3. JSON文字列を表示
                if (userSettingJson.isNotEmpty()) {
                    Text(
                        text = userSettingJson,
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(MaterialTheme.colorScheme.surfaceVariant)
                            .padding(8.dp),
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }
    }
}