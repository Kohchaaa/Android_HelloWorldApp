package com.example.helloworldapp.ui.userSettingInput

import java.time.LocalDate
import java.util.UUID

data class UserInput(
    val userId: String = UUID.randomUUID().toString(),
    val displayName: String,
    val birthDate: LocalDate?,
    val gender: Gender?,
    val allergies: Set<String>,
    val dislikeIngredients: List<String>,
    val dislikeDishes: List<String>,
    val customAttributes: Map<String, String>?, // Key-value形式で柔軟に選択肢して保存（カスタムアトリビュート型作った方が良いのかな）
    val intakeTargetVersion: String
) {



}


