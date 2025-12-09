package com.example.helloworldapp.ui.userSettingInput

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.foundation.text.input.setTextAndPlaceCursorAtEnd
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.FragmentActivity
import com.example.helloworldapp.R
import com.example.helloworldapp.ui.theme.HelloWorldAppTheme
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged

class UserSettingInputActivity : FragmentActivity() {
    @OptIn(FlowPreview::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {

            // state
            val userNameState = rememberTextFieldState("")
            var userName by rememberSaveable { mutableStateOf("") }
            var selectedDate by rememberSaveable { mutableStateOf("YYYY-MM-DD") }
            var selectedGender by rememberSaveable { mutableStateOf<Gender?>(null) }
            var dislikesIngredients by rememberSaveable { mutableStateOf(listOf<String>()) }
            var dislikesDishes by rememberSaveable { mutableStateOf(listOf<String>()) }
            var customAttributes by rememberSaveable { mutableStateOf(mapOf<String, String>("" to "")) }


            // hook
            LaunchedEffect(userNameState) {
                snapshotFlow { userNameState.text }
                    .debounce(300)
                    .distinctUntilChanged()
                    .collect { text ->
                        userName = text.toString()
                    }
            }

            // function
            val datePickerCallback = { year: Int, month: Int, day: Int ->
                selectedDate = formatDate(year, month, day)
            }

            HelloWorldAppTheme {
                Scaffold(modifier = Modifier
                    .fillMaxSize()
                ) { innerPadding ->
                    Column(
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .fillMaxSize()
                            .verticalScroll(rememberScrollState())
                            .imePadding()
                    ) {
                        Column(
                            verticalArrangement = Arrangement.spacedBy(16.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier
                                .fillMaxWidth()
                                .border(width = 1.dp, color = Color.DarkGray)
                                .padding(innerPadding)
                        ) {

                            PageTitle(title = "設定")

                            UserNameSetting(
                                userName = userNameState,
                                currentUserName = userName
                            )

                            BirthdaySetting(
                                currentDate = selectedDate,
                                onButtonClick = {
                                    val dialog = DatePicker(datePickerCallback)
                                    dialog.show(supportFragmentManager, "datePicker")
                                }
                            )

                            GenderSetting(
                                selectedGender = selectedGender,
                                onGenderSelected = { gender ->
                                    selectedGender = gender
                                }
                            )

                            StringListSetting (
                                label = "嫌いな食材",
                                chips = dislikesIngredients,
                                onChipsChange = { chips ->
                                    dislikesIngredients = chips
                                }
                            )

                            StringListSetting (
                                label = "嫌いな料理",
                                chips = dislikesDishes,
                                onChipsChange = { dislikeDishes ->
                                    dislikesDishes = dislikeDishes
                                }
                            )

                            CustomAttributeSetting(
                                customAttributes = customAttributes,
                                onDataChange = { newMap ->
                                    customAttributes = newMap
                                }
                            )
                        }

                        Column() {
                            Text(text = userName.ifEmpty { "null" })
                            Text(text = selectedDate)
                            Text(text = selectedGender?.value ?: "null")
                            Text(text = dislikesIngredients.toString())
                            Text(text = dislikesDishes.toString())
                            Text(text = customAttributes.toString())
                        }
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
                .background(color = Color.DarkGray)
                .padding(20.dp)
        ) {
            Text(
                text = title,
                fontSize = 24.sp
            )
        }
    }

    @Composable
    fun UserNameSetting(
        userName: TextFieldState,
        currentUserName: String,
    ) {
        SettingItem(
            itemName = "ニックネーム",
            content = {
                BasicTextField(
                    state = userName,
                    lineLimits = TextFieldLineLimits.SingleLine,
                    textStyle = LocalTextStyle
                        .current
                        .copy(
                            color = Color.White,
                            fontSize = 16.sp,
                            lineHeight = 24.sp
                        ),
                    onKeyboardAction = { userName.setTextAndPlaceCursorAtEnd(currentUserName) },
                    modifier = Modifier
                        .width(200.dp)
                        .border(width = 1.dp, color = Color.Gray, shape = RoundedCornerShape(8.dp))
                        .padding(10.dp)
                )
            }
        )
    }

    @Composable
    fun BirthdaySetting(
        currentDate: String,
        onButtonClick: () -> Unit
    ) {
        SettingItem(
            itemName = "誕生日",
            content = {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .clickable { onButtonClick() }
                        .border(width = 1.dp, color = Color.Gray, shape = RoundedCornerShape(8.dp))
                        .padding(10.dp)
                ) {
                    Text(
                        text = currentDate,
                        textAlign = TextAlign.Center,
                    )
                    Icon(
                        painter = painterResource(R.drawable.edit_calender_icon_white),
                        contentDescription = "Change Birthday"
                    )
                }
            }
        )
    }

    @SuppressLint("DefaultLocale")
    fun formatDate(year: Int, month: Int, day: Int): String{
        return String.format("%04d-%02d-%02d", year, month + 1, day)
    }
}