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
import theme.Theme
import view.model.DefaultParamState

@Suppress("LongParameterList")
@Composable
fun TitledTextField(
    modifier: Modifier = Modifier,
    hasTitle: Boolean = true,
    title: String,
    state: DefaultParamState,
    hint: String,
    readOnly: Boolean = false,
    trailingIcon: @Composable (() -> Unit)? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    isDigits: Boolean = false,
    maxChar: Int = Int.MAX_VALUE,
    enabled: Boolean = true,
    singleLine: Boolean = false,
    onValueChanged: (String) -> Unit,
    textStyle: TextStyle,
    hintStyle: TextStyle = Theme.fonts.bold.copy(
        color = Theme.colors.hintColor
    ),
    colors: TextFieldColors = TextFieldDefaults.textFieldColors(
        textColor = Theme.colors.textPrimaryColor,
        focusedIndicatorColor = Color.Transparent,
        unfocusedIndicatorColor = Color.Transparent,
        backgroundColor = Color.White,
        cursorColor = Color.Black
    ),
) {
    val isFocused = remember {
        mutableStateOf(false)
    }
    if (hasTitle) {
        Text(
            text = title,
            style = Theme.fonts.regular.copy(
                color = Theme.colors.textThirdColor
            )
        )
    }
    OutlinedTextField(
        modifier = modifier.then(
            Modifier
                .fillMaxWidth()
                .onFocusEvent {
                    isFocused.value = it.isFocused
                }
        ),
        maxLines = 1,
        isError = state.isError && !isFocused.value,
        value = state.stateText,
        enabled = enabled,
        textStyle = textStyle,
        keyboardOptions = keyboardOptions,
        singleLine = singleLine,
        colors = colors,
        shape = Theme.shapes.textFields,
        readOnly = readOnly,
        trailingIcon = trailingIcon,
        leadingIcon = leadingIcon,
        placeholder = {
            Text(
                text = hint,
                style = hintStyle
            )
        },
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
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = stringResource(state.stateError), style = Theme.fonts.regular.copy(
                color = Theme.colors.errorColor,
                fontSize = 12.sp,
            )
        )
    }
}