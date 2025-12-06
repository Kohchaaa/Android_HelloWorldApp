package com.example.helloworldapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.helloworldapp.ui.theme.HelloWorldAppTheme
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.helloworldapp.ui.userSettingInput.UserSettingInputActivity

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HelloWorldAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    RootLayout(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun RootLayout(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Greeting(
            name = "kohcha",
        )
        TestButton({ changeActivity(context) })
    }
}

@Preview
@Composable
fun RootLayoutPreview() {
    HelloWorldAppTheme() {
        RootLayout()
    }
}


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "hello my name is $name",
        modifier = Modifier
            .background(Color.Cyan)
            .border(1.dp, color = Color.Blue)
            .padding(20.dp)
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    HelloWorldAppTheme {
        Greeting("test")
    }
}

@Composable
fun TestButton(onClick: () -> Unit){
    Button(
        onClick = onClick
    ) {
        Text(text = "Button")
    }
}

fun changeActivity(context: Context) {
    val intent = Intent(context, UserSettingInputActivity::class)
    context.startActivity(intent)
}

@Preview(showBackground = true)
@Composable
fun TestButtonPreview() {
    HelloWorldAppTheme() {
        TestButton({})
    }
}