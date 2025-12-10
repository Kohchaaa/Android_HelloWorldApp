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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.helloworldapp.ui.theme.HelloWorldAppTheme
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
        modifier = modifier.fillMaxSize()
    ) {
        Greeting(
            name = "kohcha",
        )
        TestButton(label = "Go to UserInput", onClick ={ changeActivity(context) })
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
        modifier = modifier
            .background(MaterialTheme.colorScheme.surface)
            .border(1.dp, color = MaterialTheme.colorScheme.outline)
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
fun TestButton(label: String = "Button", onClick: () -> Unit){
    Button(
        onClick = onClick,
    ) {
        Text(
            text = label,
            color = MaterialTheme.colorScheme.onPrimary
        )
    }
}

fun changeActivity(context: Context) {
    val intent = Intent(context, UserSettingInputActivity::class.java)
    context.startActivity(intent)
}

@Preview(showBackground = true)
@Composable
fun TestButtonPreview() {
    HelloWorldAppTheme() {
        TestButton("", onClick = {})
    }
}