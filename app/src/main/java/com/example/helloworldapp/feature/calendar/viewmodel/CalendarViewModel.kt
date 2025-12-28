package com.example.helloworldapp.feature.calendar.viewmodel

import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.helloworldapp.data.mock.mockMealRecord
import com.example.helloworldapp.feature.calendar.DailyMealStatus
import com.example.helloworldapp.type.MealRecord
import com.example.helloworldapp.type.MealType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import java.time.LocalDate

class CalendarViewModel : ViewModel() {

    // 実際はRepository付くってRoom等から取得する予定
    private val _mealRecords = MutableStateFlow<List<MealRecord>>(mockMealRecord)


    val dailyRecord: StateFlow< Map<LocalDate, List<MealRecord>> > = _mealRecords.map { records ->
        records.groupBy { it.date.toLocalDate() }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyMap()
    )

    // 食事レコードを，カレンダー表示に最適なデータに
    // 日付をキーとして，その日のMealTypeごとに食べたかどうかが真偽値で保存
    val mealStatus: StateFlow<Map<LocalDate, DailyMealStatus>> = dailyRecord.map { statuses ->
        statuses.mapValues { (_, records) ->
            DailyMealStatus(
                hasBreakfast = records.any { it.mealType == MealType.BREAKFAST },
                hasLunch = records.any { it.mealType == MealType.LUNCH },
                hasDinner = records.any { it.mealType == MealType.DINNER },
                hasSnack = records.any { it.mealType == MealType.SNACK },
                hasMidnight = records.any { it.mealType == MealType.MIDNIGHT }
            )
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyMap()
    )
}