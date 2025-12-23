package com.example.helloworldapp.feature.setting.viewmodel

import com.example.helloworldapp.feature.setting.UserSetting

data class SettingUiState(
    val isLoading: Boolean = false,
    val setting: UserSetting = UserSetting.createNew()
)