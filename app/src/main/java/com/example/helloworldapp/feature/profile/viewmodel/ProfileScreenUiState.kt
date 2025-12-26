package com.example.helloworldapp.feature.profile.viewmodel

import com.example.helloworldapp.feature.profile.UserProfile

data class ProfileScreenUiState(
    val isLoading: Boolean = false,
    val setting: UserProfile = UserProfile.createNew()
)