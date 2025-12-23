package com.example.helloworldapp.feature.setting

import AllergenSelectSection
import android.os.Build
import androidx.annotation.RequiresApi
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.helloworldapp.feature.setting.component.AllergenMockData
import com.example.helloworldapp.feature.setting.component.BirthdaySetting
import com.example.helloworldapp.feature.setting.component.CustomAttributeSetting
import com.example.helloworldapp.feature.setting.component.GenderSetting
import com.example.helloworldapp.feature.setting.component.SettingCategory
import com.example.helloworldapp.feature.setting.component.StringListSetting
import com.example.helloworldapp.feature.setting.component.UserNameSetting
import com.example.helloworldapp.feature.setting.viewmodel.SettingUiState
import com.example.helloworldapp.feature.setting.viewmodel.SettingViewModel
import com.example.helloworldapp.ui.common.PageTitle
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalSerializationApi::class)
@Composable
fun SettingScreen(
    viewModel: SettingViewModel = viewModel()
) {
    // これで外にあるデータをStateとしてインポートできる
    val uiState by viewModel.uiState.collectAsState()

    SettingScreenContent(
        uiState = uiState,
        onUpdateInput = viewModel::updateUserInput
    )


}

// 分離したことでプレビューもしやすくなる
@RequiresApi(Build.VERSION_CODES.O)
@OptIn(FlowPreview::class, ExperimentalMaterial3Api::class, ExperimentalSerializationApi::class)
@Composable
fun SettingScreenContent(
    uiState: SettingUiState,
    onUpdateInput: ((UserSetting) -> UserSetting) -> Unit
){
    // uiStateの取り出し
    val currentInput = uiState.setting

    // 個別のState
    val userNameState = rememberTextFieldState(currentInput.displayName)
    var userSettingJson by remember { mutableStateOf("") }
    var isShowDatePicker by rememberSaveable { mutableStateOf(false) }
    val dataPickerState = rememberDatePickerState()

    // hook
    LaunchedEffect(userNameState) {
        snapshotFlow { userNameState.text }
            .debounce(300)
            .distinctUntilChanged()
            .collect { text ->
                onUpdateInput { it.copy(displayName = text.toString()) }
            }
    }

    // function
    val datePickerCallback = { year: Int, month: Int, day: Int ->
        onUpdateInput {
            it.copy(birthDate = LocalDate.of(year, month, day))
        }
    }


    Scaffold(modifier = Modifier
        .fillMaxSize()
    ) { innerPadding ->
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .background(color = MaterialTheme.colorScheme.surface)
                .imePadding()
        ) {
            // 設定項目
            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(innerPadding)
            ) {

                PageTitle(title = "設定")

                SettingCategory(
                    categoryName = "ユーザー設定",
                    categoryIcon = Icons.Default.AccountCircle
                ) {
                    UserNameSetting(
                        userName = userNameState,
                        currentUserName = currentInput.displayName
                    )

                    BirthdaySetting(
                        selectedDate = currentInput.birthDate ?: LocalDate.now(),
                        onButtonClick = {
                            isShowDatePicker = true
                        },
                        dismissDatePicker = {
                            isShowDatePicker = false
                        },
                        onDateConfirm = { newDate ->
                            onUpdateInput { it.copy(birthDate = LocalDate.parse(newDate)) }
                        },
                        isShowDialog = isShowDatePicker,
                        datePickerState = dataPickerState
                    )
                    GenderSetting(
                        selectedGender = currentInput.gender,
                        onGenderSelected = { newGender ->
                            onUpdateInput { it.copy(gender = newGender) }
                        }
                    )
                }


                SettingCategory(
                    categoryName = "アレルギー",
                    categoryIcon = Icons.Default.Warning
                ) {
                    AllergenSelectSection(
                        allAllergens = AllergenMockData.all,
                        selectedAllergens = uiState.setting.allergies,
                        onToggle = { clickedValue ->
                            // Setの更新ロジック
                            onUpdateInput { input ->
                                val currentSet = input.allergies
                                val newSet = if (currentSet.contains(clickedValue)) {
                                    currentSet - clickedValue
                                } else {
                                    currentSet + clickedValue
                                }
                                input.copy(allergies = newSet)
                            }
                        }
                    )
                }

                SettingCategory(
                    categoryName = "きらいなもの",
                    categoryIcon = Icons.Default.Clear
                ) {
                    StringListSetting(
                        label = "嫌いな食材",
                        chips = currentInput.dislikeIngredients,
                        onChipsChange = { chips ->
                            onUpdateInput { it.copy(dislikeIngredients = chips) }
                        }
                    )

                    StringListSetting(
                        label = "嫌いな料理",
                        chips = currentInput.dislikeDishes,
                        onChipsChange = { dislikeDishes ->
                            onUpdateInput { it.copy(dislikeDishes = dislikeDishes) }
                        }
                    )
                }

                SettingCategory(
                    categoryName = "カスタム属性",
                    categoryIcon = Icons.Default.Build
                ) {
                    CustomAttributeSetting(
                        customAttributes = currentInput.customAttributes,
                        onDataChange = { newAttribute ->
                            onUpdateInput { it.copy(customAttributes = newAttribute) }
                        }
                    )
                }

            }

            // デバッグ用
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(16.dp)
            ) {
                Button(
                    onClick = {

                        // 1. uiStateのsetting項目からデータを作成
                        val userInputData = currentInput

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