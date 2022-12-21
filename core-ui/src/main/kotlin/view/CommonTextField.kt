package view

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.material.TextFieldColors
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import theme.Theme
import utils.DigitVisualTransformation
import utils.Mask

@Suppress("LongParameterList", "LongMethod")
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CommonTextField(
    modifier: Modifier = Modifier,
    text: String,
    hint: String,
    textStyle: TextStyle = Theme.fonts.regular,
    hintStyle: TextStyle = Theme.fonts.bold.copy(
        color = Theme.colors.hintColor,
        fontSize = 24.sp
    ),
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    isSecure: Boolean = false,
    colors: TextFieldColors = TextFieldDefaults.textFieldColors(
        textColor = Theme.colors.textPrimaryColor,
        focusedIndicatorColor = Color.Transparent,
        unfocusedIndicatorColor = Color.Transparent,
        backgroundColor = Color.White,
        cursorColor = Color.Black
    ),
    isPhone: Boolean = false,
    isCode: Boolean = false,
    maxChar: Int = Int.MAX_VALUE,
    enabled: Boolean = true,
    singleLine: Boolean = false,
    trailingIcon: (@Composable () -> Unit) = {},
    onValueChanged: (String) -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }
    val visualTransformation = when {
        isSecure -> PasswordVisualTransformation()
        isPhone -> DigitVisualTransformation(
            mask = Mask.PHONE,
            maxChar = maxChar
        )
        isCode -> DigitVisualTransformation(
            mask = Mask.CODE,
            maxChar = maxChar
        )
        else -> VisualTransformation.None
    }
    BasicTextField(
        modifier = modifier.then(
            Modifier.fillMaxWidth()
        ),
        value = text,
        enabled = enabled,
        textStyle = textStyle,
        keyboardOptions = keyboardOptions,
        visualTransformation = visualTransformation,
        onValueChange = { value ->
            when {
                isPhone || isCode -> {
                    onValueChanged(value.filter { it.isDigit() }.take(maxChar))
                }
                else -> {
                    onValueChanged(value.take(maxChar))
                }
            }
        }
    ) { innerTextField ->
        TextFieldDefaults.TextFieldDecorationBox(
            value = text,
            visualTransformation = visualTransformation,
            innerTextField = innerTextField,
            singleLine = singleLine,
            enabled = enabled,
            trailingIcon = trailingIcon,
            colors = colors,
            interactionSource = interactionSource,
            contentPadding = PaddingValues(0.dp),
            placeholder = {
                Text(
                    text = hint,
                    style = hintStyle
                )
            }
        )
    }
}