package com.example.helloworldapp.type

data class Ingredient(
    val name: String,
    val amount: String,
    val note: String? = null  // "下味用"，とか補足するやつ
)
