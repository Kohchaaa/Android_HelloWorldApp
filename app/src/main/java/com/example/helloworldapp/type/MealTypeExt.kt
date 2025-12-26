package com.example.helloworldapp.type

// MealUiExt.kt (UI用の拡張定義ファイル)
// UI層でだけ有効な「見た目」の定義をここに書く
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import com.kizitonwose.calendar.core.DayPosition

// 「MealType型にiconというプロパティを生やす」書き方
val MealType.icon: ImageVector
    get() = when (this) {
        MealType.BREAKFAST -> Icons.Default.BakeryDining
        MealType.LUNCH     -> Icons.Default.RiceBowl
        MealType.DINNER    -> Icons.Default.Restaurant
        MealType.SNACK     -> Icons.Default.Cookie
        MealType.MIDNIGHT -> Icons.Default.Tapas
    }

// 色の定義
val MealType.color: Color
    get() = when (this) {
        MealType.BREAKFAST -> Color(0xFFFF9800) // オレンジ（朝日）
        MealType.LUNCH     -> Color(0xFF8BC34A) // ライトグリーン（活発・サラダ）
        MealType.DINNER    -> Color(0xFF3F51B5) // インディゴ（落ち着いた夜）
        MealType.SNACK     -> Color(0xFFE91E63) // ピンク（甘味・楽しみ）
        MealType.MIDNIGHT -> Color(0xFF673AB7) // ディープパープル（深夜）
    }

val DimmedBreakfastColor = MealType.BREAKFAST.color.copy(0.3f)
val DimmedLunchColor = MealType.LUNCH.color.copy(0.3f)
val DimmedDinnerColor = MealType.DINNER.color.copy(0.3f)
val DimmedSnackColor = MealType.SNACK.color.copy(0.3f)
val DimmedMidnightColor = MealType.MIDNIGHT.color.copy(0.3f)

// 補助：背景用に少し薄くした色を自動生成するプロパティもあると便利
val MealType.backgroundColor: Color
    get() = this.color.copy(alpha = 0.15f) // 元の色を15%の薄さに透かす