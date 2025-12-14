package com.example.helloworldapp.feature.setting.viewmodel

import com.example.helloworldapp.feature.setting.UserInput

data class SettingUiState(
    val isLoading: Boolean = false,
    val setting: UserInput = UserInput.createNew()
)