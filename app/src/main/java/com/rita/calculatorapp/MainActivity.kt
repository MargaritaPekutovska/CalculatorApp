package com.rita.calculatorapp

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.notkamui.keval.keval
import com.rita.calculatorapp.ui.theme.CalculatorAppTheme
import com.rita.calculatorapp.ui.theme.LightGray
import com.rita.calculatorapp.ui.theme.Orange


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CalculatorAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Box(modifier = Modifier.fillMaxSize()) {
                        CalculatorLayout(
                            modifier = Modifier
                                .align(Alignment.BottomCenter)
                                .padding(bottom = 24.dp, start = 8.dp, end = 8.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun CalculatorLayout(modifier: Modifier) {
    Column(modifier = modifier) {
        val inputText = remember {
            mutableStateOf("0")
        }
        val inputTextFontSize = remember {
            mutableStateOf(70)
        }
        val context = LocalContext.current
        val onButtonClick: (String) -> Unit = { buttonValue ->
            val charsNumber = inputText.value.length
            if (charsNumber >= 15) {
                context.showToast()
            } else {
                if (charsNumber >= 8) {
                    inputTextFontSize.value = 40
                } else {
                    inputTextFontSize.value = 70

                }
                inputText.value = combineValues(
                    inputTextValue = inputText.value,
                    newValue = buttonValue
                )
            }
        }
        Text(
            text = inputText.value,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            fontSize = inputTextFontSize.value.sp,
            modifier = Modifier
                .align(Alignment.End)
                .padding(end = 20.dp)
                .horizontalScroll(rememberScrollState())
        )

        Row(modifier = Modifier.fillMaxWidth()) {
            CalculatorButton(
                buttonText = "7",
                onClick = onButtonClick,
                modifier = Modifier.weight(1f),
                containerColor = Color.DarkGray
            )
            CalculatorButton(
                buttonText = "8",
                onClick = onButtonClick,
                modifier = Modifier.weight(1f),
                containerColor = Color.DarkGray
            )
            CalculatorButton(
                buttonText = "9",
                onClick = onButtonClick,
                modifier = Modifier.weight(1f),
                containerColor = Color.DarkGray
            )
            CalculatorButton(
                buttonText = "X",
                onClick = onButtonClick,
                modifier = Modifier.weight(1f),
                containerColor = Orange
            )
        }

        Row {
            CalculatorButton(
                buttonText = "4",
                onClick = onButtonClick,
                modifier = Modifier.weight(1f),
                containerColor = Color.DarkGray
            )
            CalculatorButton(
                buttonText = "5",
                onClick = onButtonClick,
                modifier = Modifier.weight(1f),
                containerColor = Color.DarkGray
            )
            CalculatorButton(
                buttonText = "6",
                onClick = onButtonClick,
                modifier = Modifier.weight(1f),
                containerColor = Color.DarkGray
            )
            CalculatorButton(
                buttonText = "-",
                onClick = onButtonClick,
                modifier = Modifier.weight(1f),
                containerColor = Orange
            )
        }

        Row {
            CalculatorButton(
                buttonText = "1",
                onClick = onButtonClick,
                modifier = Modifier.weight(1f),
                containerColor = Color.DarkGray
            )
            CalculatorButton(
                buttonText = "2",
                onClick = onButtonClick,
                modifier = Modifier.weight(1f),
                containerColor = Color.DarkGray
            )
            CalculatorButton(
                buttonText = "3",
                onClick = onButtonClick,
                modifier = Modifier.weight(1f),
                containerColor = Color.DarkGray
            )
            CalculatorButton(
                buttonText = "+",
                onClick = onButtonClick,
                modifier = Modifier.weight(1f),
                containerColor = Orange
            )
        }

        Row {
            CalculatorButton(
                buttonText = "0",
                onClick = onButtonClick,
                modifier = Modifier.weight(1f),
                containerColor = LightGray
            )
            CalculatorButton(
                buttonText = "÷",
                onClick = onButtonClick,
                modifier = Modifier.weight(1f),
                containerColor = LightGray
            )
            CalculatorButton(
                buttonText = "C",
                onClick = {
                    val currentInputText = inputText.value
                    val removeText = currentInputText.dropLast(1)
                    inputText.value = removeText
                },
                modifier = Modifier.weight(1f),
                containerColor = LightGray
            )
            CalculatorButton(
                buttonText = "=",
                onClick = {
                    val calculationResult = calculateResult(inputText.value)
                    inputText.value = calculationResult.toString()
                },
                modifier = Modifier.weight(1f),
                containerColor = Orange
            )
        }
    }
}

@Composable
fun CalculatorButton(
    buttonText: String,
    onClick: (String) -> Unit,
    modifier: Modifier,
    containerColor: Color
) {
    Button(
        onClick = {
            onClick.invoke(buttonText)
        },
        colors = ButtonDefaults.textButtonColors(
            containerColor = containerColor,
            contentColor = Color.White
        ),
        modifier = modifier
            .padding(8.dp)
    ) {
        Text(
            text = buttonText,
            fontSize = 26.sp
        )
    }
}

fun combineValues(
    inputTextValue: String,
    newValue: String
): String {
    return if (inputTextValue == "0") {
        newValue
    } else {
        inputTextValue + newValue
    }
}

private fun calculateResult(textInput: String): Double {
    val calculationResult = textInput.keval()
    return calculationResult
}

@Preview(showBackground = true)
@Composable
fun CalculatorPreview() {
    CalculatorAppTheme {
        Box(modifier = Modifier.fillMaxSize()) {
            CalculatorLayout(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 24.dp, start = 8.dp, end = 8.dp)
            )
        }
    }
}
