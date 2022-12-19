package view

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldColors
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import theme.Theme

@Composable
fun TitledTextField(
    modifier: Modifier = Modifier,
    title: String,
    text: String,
    hint: String,
    textStyle: TextStyle = Theme.fonts.regular.copy(
        fontSize = 16.sp
    ),
    hintStyle: TextStyle = Theme.fonts.bold.copy(
        color = Theme.colors.hintColor,
        fontSize = 16.sp
    ),
    colors: TextFieldColors = TextFieldDefaults.textFieldColors(
        textColor = Theme.colors.textPrimaryColor,
        focusedIndicatorColor = Color.Transparent,
        unfocusedIndicatorColor = Color.Transparent,
        backgroundColor = Color.White,
        cursorColor = Color.Black
    ),
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    isDigits: Boolean = false,
    maxChar: Int = Int.MAX_VALUE,
    enabled: Boolean = true,
    singleLine: Boolean = false,
    onValueChanged: (String) -> Unit
) {
    Text(
        text = title,
        style = Theme.fonts.regular.copy(
            fontSize = 16.sp,
            color = Theme.colors.textThirdColor
        )
    )
    Spacer(modifier = Modifier.height(4.dp))
    TextField(
        modifier = modifier.then(
            Modifier
                .fillMaxWidth()
        ),
        value = text,
        enabled = enabled,
        placeholder = {
            Text(
                text = hint,
                style = hintStyle
            )
        },
        textStyle = textStyle,
        keyboardOptions = keyboardOptions,
        singleLine = singleLine,
        colors = colors,
        shape = Theme.shapes.textFields,
        onValueChange = { value ->
            when {
                isDigits -> {
                    onValueChanged(value.filter { it.isDigit() }.take(maxChar))
                }
                else -> {
                    onValueChanged(value.take(maxChar))
                }
            }
        }
    )
}