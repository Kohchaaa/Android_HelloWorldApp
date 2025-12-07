package com.example.helloworldapp.ui.userSettingInput

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
//import androidx.appcompat.app.AppCompatActivity
//import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.fragment.app.FragmentActivity
import com.example.helloworldapp.ui.theme.HelloWorldAppTheme
import com.example.helloworldapp.R

class UserSettingInputActivity : FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {

            // state
            var selectedDate by remember { mutableStateOf("YYYY-MM-DD") }

            // function
            val datePickerCallback = { year: Int, month: Int, day: Int ->
                selectedDate = formatDate(year, month, day)
            }

            HelloWorldAppTheme {
                Scaffold(modifier = Modifier
                    .fillMaxSize()
                    .border(width = 1.dp, color = Color.Red)
                ) { innerPadding ->
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .fillMaxWidth()
                            .border(width = 1.dp, color = Color.DarkGray)
                            .padding(innerPadding)
                    ) {
                        PageTitle(title = "UserSettings")
                        BirthdaySetting(
                            currentDate = selectedDate,
                            buttonLabel = "Birthday",
                            onButtonClick = {
                                val dialog = DatePicker(datePickerCallback)
                                dialog.show(supportFragmentManager, "datePicker")
                            }
                        )
                        ToggleIconButtonExample()
                    }
                }
            }
        }
    }

    @Composable
    fun PageTitle(title: String) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color.Blue)
                .padding(20.dp)
        ) {
            Text(text = title)
        }
    }

    @Composable
    fun BirthdaySetting(
        currentDate: String,
        buttonLabel: String,
        onButtonClick: () -> Unit
    ) {
        Row() {
            Text(text = currentDate)
            Button(
                onClick = onButtonClick,
                modifier = Modifier
                    .padding(10.dp)
            ) {
                Text(text = buttonLabel)
            }
        }
    }

    fun formatDate(year: Int, month: Int, day: Int): String{
        return String.format("%04d-%02d-%02d", year, month + 1, day)
    }

    @Composable
    fun ToggleIconButtonExample() {
        // isToggled initial value should be read from a view model or persistent storage.
        var isToggled by rememberSaveable { mutableStateOf(false) }

        IconButton (
            onClick = { isToggled = !isToggled }
        ) {
            Icon(
                painter = if (isToggled) painterResource(R.drawable.edit_calender_icon_white) else painterResource(R.drawable.edit_calender_icon_gray),
                contentDescription = if (isToggled) "Selected icon button" else "Unselected icon button."
            )
        }
    }
}