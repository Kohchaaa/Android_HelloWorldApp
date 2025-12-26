package com.example.helloworldapp.feature.calendar

data class DailyMealStatus(
    val hasBreakfast: Boolean,
    val hasLunch: Boolean,
    val hasDinner: Boolean,
    val hasSnack: Boolean,
    val hasMidnight: Boolean
)
