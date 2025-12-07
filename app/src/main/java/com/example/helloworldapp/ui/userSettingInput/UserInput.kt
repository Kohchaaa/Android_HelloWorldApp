package com.example.helloworldapp.ui.userSettingInput

import java.time.LocalDate
import java.util.UUID

data class UserInput(
    val userId: String = UUID.randomUUID().toString(),
    val displayName: String,
    val birthDate: LocalDate,
    val gender: Gender,
    val allergies: Array<String>,
    val dislikeIngredients: Array<String>,
    val dislikeDishes: Array<String>,
    val customAttributes: Map<String, String>?, // Key-value形式で柔軟に選択肢して保存（カスタムアトリビュート型作った方が良いのかな）
    val intakeTargetVersion: String
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as UserInput

        if (userId != other.userId) return false
        if (displayName != other.displayName) return false
        if (birthDate != other.birthDate) return false
        if (gender != other.gender) return false
        if (!allergies.contentEquals(other.allergies)) return false
        if (!dislikeIngredients.contentEquals(other.dislikeIngredients)) return false
        if (!dislikeDishes.contentEquals(other.dislikeDishes)) return false
        if (customAttributes != other.customAttributes) return false
        if (intakeTargetVersion != other.intakeTargetVersion) return false

        return true
    }

    override fun hashCode(): Int {
        var result = userId.hashCode()
        result = 31 * result + displayName.hashCode()
        result = 31 * result + birthDate.hashCode()
        result = 31 * result + gender.hashCode()
        result = 31 * result + allergies.contentHashCode()
        result = 31 * result + dislikeIngredients.contentHashCode()
        result = 31 * result + dislikeDishes.contentHashCode()
        result = 31 * result + (customAttributes?.hashCode() ?: 0)
        result = 31 * result + intakeTargetVersion.hashCode()
        return result
    }


}


