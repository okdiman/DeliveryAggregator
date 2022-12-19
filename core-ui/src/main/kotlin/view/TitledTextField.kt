package view

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldColors
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import state.registration.CompanyParamState
import theme.Theme

@Suppress("LongParameterList")
@Composable
fun TitledTextField(
    modifier: Modifier = Modifier,
    title: String,
    state: CompanyParamState,
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
    val isFocused = remember {
        mutableStateOf(false)
    }
    Text(
        text = title,
        style = Theme.fonts.regular.copy(
            fontSize = 16.sp,
            color = Theme.colors.textThirdColor
        )
    )
    Spacer(modifier = Modifier.height(4.dp))
    OutlinedTextField(
        modifier = modifier.then(
            Modifier
                .fillMaxWidth()
                .onFocusEvent {
                    isFocused.value = it.isFocused
                }
        ),
        isError = state.isError && !isFocused.value,
        value = state.stateText,
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
    if (state.isError && !isFocused.value) {
        Text(
            text = stringResource(state.stateError), style = Theme.fonts.regular.copy(
                color = Theme.colors.errorColor,
                fontSize = 12.sp
            )
        )
    }
}