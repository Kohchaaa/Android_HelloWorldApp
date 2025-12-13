package com.example.helloworldapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.helloworldapp.feature.main.MainScreen
import com.example.helloworldapp.ui.theme.HelloWorldAppTheme
import com.example.helloworldapp.feature.setting.UserSettingInputActivity

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HelloWorldAppTheme {
                MainScreen()
            }
        }
    }
}

fun changeActivity(context: Context) {
    val intent = Intent(context, UserSettingInputActivity::class.java)
    context.startActivity(intent)
}
