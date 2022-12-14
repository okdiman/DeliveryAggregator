package view

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import theme.Theme

@Composable
fun CommonTextField(
    text: String,
    hint: String,
    isSecure: Boolean = false,
    enabled: Boolean = true,
    trailingIcon: (@Composable () -> Unit) = {},
    onValueChanged: (String) -> Unit
) {
    TextField(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp),
        value = text,
        enabled = enabled,
        placeholder = {
            Text(
                text = hint,
                color = Theme.colors.hintColor
            )
        },
        visualTransformation = if (isSecure) {
            PasswordVisualTransformation()
        } else {
            VisualTransformation.None
        },
        trailingIcon = trailingIcon,
        colors = TextFieldDefaults.textFieldColors(
            textColor = Theme.colors.textPrimaryColor,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            backgroundColor = Color.White,
            cursorColor = Color.Black
        ),
        onValueChange = { onValueChanged(it) }
    )
}