package com.example.helloworldapp.type

// MealUiExt.kt (UI用の拡張定義ファイル)
// UI層でだけ有効な「見た目」の定義をここに書く
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.helloworldapp.LocalThemeController

// ==========================================
// アイコン定義
// ==========================================
val MealType.icon: ImageVector
    get() = when (this) {
        MealType.BREAKFAST -> Icons.Default.BakeryDining
        MealType.LUNCH     -> Icons.Default.RiceBowl
        MealType.DINNER    -> Icons.Default.Restaurant
        MealType.SNACK     -> Icons.Default.Cookie
        MealType.MIDNIGHT  -> Icons.Default.Tapas
    }

// ==========================================
// 色定義
// ==========================================
val MealType.color: Color
    @Composable
    get() = if (LocalThemeController.current.isDarkTheme) {
        // ダークモード用の色 (鮮やか・元の指定色)
        when (this) {
            MealType.BREAKFAST -> Color(0xFFFF9800) // オレンジ 500
            MealType.LUNCH     -> Color(0xFF8BC34A) // ライトグリーン 500
            MealType.DINNER    -> Color(0xFF3F51B5) // インディゴ 500
            MealType.SNACK     -> Color(0xFFE91E63) // ピンク 500
            MealType.MIDNIGHT  -> Color(0xFF673AB7) // ディープパープル 500
        }
    } else {
        // ライトモード用の色 (白背景で見やすいように少し濃く・深く調整)
        when (this) {
            MealType.BREAKFAST -> Color(0xFFFB8C00) // オレンジ 600
            MealType.LUNCH     -> Color(0xFF7CB342) // ライトグリーン 600
            MealType.DINNER    -> Color(0xFF3949AB) // インディゴ 600
            MealType.SNACK     -> Color(0xFFD81B60) // ピンク 600
            MealType.MIDNIGHT  -> Color(0xFF5E35B1) // ディープパープル 600
        }
    }

// ==========================================
// その他カラー
// ==========================================

// 背景用：現在のテーマ色を15%の薄さに
val MealType.backgroundColor: Color
    @Composable
    get() = this.color.copy(alpha = 0.15f)

// 薄くした色（カレンダーのドット等で使用）
// プロパティとして定義しなおすことで、呼び出し側は .dimmedColor でアクセス可能
val MealType.dimmedColor: Color
    @Composable
    get() = this.color.copy(alpha = 0.3f)

// 未摂取時のグレー色 (テーマ連動)
val MissingMealColor: Color
    @Composable
    get() = if (LocalThemeController.current.isDarkTheme) {
        Color(0xFF222222) // ダークモード: 濃いグレー
    } else {
        Color(0xFFE7E7E7) // ライトモード: 薄いグレー
    }