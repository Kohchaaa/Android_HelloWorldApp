package com.example.helloworldapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.helloworldapp.feature.main.Greeting
import com.example.helloworldapp.feature.main.MainScreen
import com.example.helloworldapp.feature.main.TestButton
import com.example.helloworldapp.ui.theme.HelloWorldAppTheme
import com.example.helloworldapp.feature.userSettingInput.UserSettingInputActivity

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
