package com.example.helloworldapp.feature.setting.viewmodel

import androidx.lifecycle.ViewModel
import com.example.helloworldapp.feature.setting.UserInput
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class SettingViewModel: ViewModel() {

    private val _uiState = MutableStateFlow(SettingUiState())
    val uiState: StateFlow<SettingUiState> = _uiState.asStateFlow()

    /**
     * UserInputを更新する汎用関数
     *
     * **使用例:**
     * ```
     * @Composable
     * fun SettingScreenContent(
     *     uiState: SettingUiState,
     *     onUpdateInput: ((UserInput) -> UserInput) -> Unit
     * ) {
     *      GenderSelector(
     *          selected = uiState.setting.gender,
     *          onSelect = { newGender ->
     *              onUpdateInput { it.copy(gender = newGender) }
     *          }
     *      )
     * }
     * ```
     * ※`SettingScreenContent`は外から`updateUserInput`を`onUpdateInput`として受け取ってる
     *
     * @param transformFunc 更新ロジックをラムダ式で受け取る
     * @return Unit
     */
    fun updateUserInput(transformFunc: (UserInput) -> UserInput) {
        _uiState.update { currentState ->
            currentState.copy(
                setting = transformFunc(currentState.setting)
            )
        }
    }
}