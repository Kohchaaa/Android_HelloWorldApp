package com.example.helloworldapp.type

import java.time.LocalDateTime

data class MealRecord(
    val id: String,  // UUID
    val userId: String,  // UUID
    val date: LocalDateTime,
    val mealType: MealType,
    val inputMethod: InputMethod,
    val inputData: String?, // InputMethod == TEXTの時に入る
    val imagePath: String?, // InputMethod == IMAGEの時に入る
    val mealName: String,
    val nutrients: Nutrients,
    val estimationDetail: List<Ingredient> // 推定された材料を構造化して記録
)

enum class MealType { BREAKFAST, LUNCH, DINNER, SNACK, MIDNIGHT }
enum class InputMethod { TEXT, IMAGE }

data class Nutrients(
    val calories: Float,     // カロリー [Kcal]
    val protein: Float,      // タンパク質 [g]
    val fat: Float,          // 脂質 [g]
    val carbohydrate: Float, // 炭水化物 [g]
    val salt: Float          // 塩分 [g]
)