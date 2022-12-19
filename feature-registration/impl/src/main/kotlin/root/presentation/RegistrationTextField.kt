package root.presentation

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import root.RegistrationConstants.HINT_ALPHA
import state.registration.CompanyParamState
import theme.Theme
import view.TitledTextField

@Suppress("LongParameterList")
@Composable
fun RegistrationTextField(
    modifier: Modifier = Modifier,
    title: String,
    state: CompanyParamState,
    hint: String,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    maxChar: Int = Int.MAX_VALUE,
    onValueChanged: (String) -> Unit
) {
    Spacer(modifier = Modifier.height(24.dp))
    TitledTextField(
        modifier = modifier,
        title = title,
        hint = hint,
        state = state,
        hintStyle = Theme.fonts.regular.copy(
            fontSize = 16.sp,
            color = Theme.colors.textPrimaryColor.copy(alpha = HINT_ALPHA)
        ),
        colors = TextFieldDefaults.textFieldColors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            backgroundColor = Theme.colors.hintBackgroundColor,
            cursorColor = Color.Black,
            errorIndicatorColor = Theme.colors.errorColor
        ),
        keyboardOptions = keyboardOptions,
        maxChar = maxChar
    ) {
        onValueChanged(it)
    }
}