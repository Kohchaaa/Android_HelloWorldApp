package com.example.helloworldapp.type

import java.time.LocalDateTime

data class SuggestionRecord(
    val id: String,  // UUID
    val userId: String,  // UUID
    val date: LocalDateTime,
    val customRequests: CustomRequests, // 提案ごとにユーザーが入力するリクエスト格納
    val recipeDetails: RecipeDetails,
    val adviceComment: String
)

data class CustomRequests(
    val requestText: String,  // 任意のリクエストプロンプト
    val servings: Int,  // 品数
    val targetIngredients: List<String>  // 入れたい食材（食材消費したいニーズ）
)

// mealType Enumと数を一致させたい
// BREAKFAST, LUNCH, DINNER, SNACK, MIDNIGHT
data class RecipeDetails(
    val breakfast: Recipe?,
    val lunch: Recipe?,
    val dinner: Recipe?,
    val snack: Recipe?,
    val midnight: Recipe?
)

data class Recipe(
    val name: String,
    val ingredients: List<Ingredient>,
    val instructions: List<String>
)