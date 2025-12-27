package com.example.helloworldapp.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.IntOffset
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.example.helloworldapp.LocalThemeController
import com.example.helloworldapp.feature.main.MainScreen
import com.example.helloworldapp.feature.profile.ProfileScreen

// ■ アニメーションの時間（ms）
// iOSはもっと速いですが、パララックスを目立たせるなら400-500msくらいがリッチに見えます
private const val ANIM_DURATION = 250

// ■ 動きのカーブ（イージング）
// Android標準のFastOutSlowInよりも、iOSに近い「最初が速くて最後がゆっくり」なカーブ
private val iOS_EASING = CubicBezierEasing(0.25f, 0.1f, 0.25f, 1.0f)

// ■ パララックスの度合い（分母）
// 3 = 画面幅の1/3移動する。
// 値を大きくする（4, 5...） -> 背景があまり動かなくなる（重厚感）
// 値を小さくする（2...） -> 背景がたくさん動く（軽快感）
private const val PARALLAX_FACTOR = 2

// 共通のアニメーションスペック（これを全箇所で使うことでズレを防ぐ）
private val animSpec = tween<IntOffset>(durationMillis = ANIM_DURATION, easing = iOS_EASING)

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NavigationScreen() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = MainRoute,
    ) {

        // メイン画面
        mainScreen(
            { navController.navigate(ProfileRoute) }
        )

        // 設定画面
        composable<ProfileRoute>(
            // 右から入ってくる
            enterTransition = {
                slideInHorizontally(
                    initialOffsetX = { it }, // 画面幅分しっかり移動
                    animationSpec = animSpec
                )
            },
            // 右へ出ていく
            popExitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { it },
                    animationSpec = animSpec
                )
            }
        ) {
            ProfileScreen()
        }
    }
}


// mainScreen用のサブナビゲーション
@RequiresApi(Build.VERSION_CODES.O)
private fun NavGraphBuilder.mainScreen(
    onNavigateUserSetting: () -> Unit
) {
    navigation<MainRoute>(startDestination = HomeRoute){
        composable<HomeRoute>(
            // 【重要】設定画面へ行くとき、すぐ消えずに「ゆっくり左へズレる（パララックス）」
            // 設定へ行くとき：左へ少しズレるだけ（★fadeOutを削除しました）
            exitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { -it / PARALLAX_FACTOR },
                    animationSpec = animSpec
                )
            },
            // 設定から戻るとき：左のズレた位置から戻ってくる（★fadeInも削除）
            popEnterTransition = {
                slideInHorizontally(
                    initialOffsetX = { -it / PARALLAX_FACTOR },
                    animationSpec = animSpec
                )
            }
        ){
            MainScreen(
                onNavigateSetting = onNavigateUserSetting
            )
        }
    }
}