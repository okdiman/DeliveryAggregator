import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import theme.Theme
import trinity_monsters.delivery_aggregator.core_ui.R

/**
 * принимает градиент в качестве бэкграунда
 */
@Suppress("LongParameterList")
@Composable
fun ActionButton(
    modifier: Modifier = Modifier,
    @StringRes textRes: Int = R.string.common_continue_button,
    additionalText: String? = null,
    height: Dp = 52.dp,
    fontSize: TextUnit = 20.sp,
    additionalTextFontSize: TextUnit = 14.sp,
    gradient: Brush = Theme.gradients.actionButtonGradient,
    alignment: Alignment = Alignment.BottomCenter,
    textColor: Color = Theme.colors.textSecondaryColor,
    enabled: Boolean = true,
    padding: PaddingValues = PaddingValues(start = 16.dp, end = 16.dp, bottom = 44.dp),
    onClick: () -> Unit
) {
    Box(
        modifier = modifier,
        contentAlignment = alignment
    ) {
        ActionButtonView(
            textRes = textRes,
            additionalText = additionalText,
            height = height,
            gradient = gradient,
            fontSize = fontSize,
            additionalTextFontSize = additionalTextFontSize,
            textColor = textColor,
            enabled = enabled,
            padding = padding,
            onClick = onClick
        )
    }
}

/**
 * принимает цвет в качестве бэкграунда
 */
@Suppress("LongParameterList")
@Composable
fun ActionButton(
    modifier: Modifier = Modifier,
    @StringRes textRes: Int = R.string.common_continue_button,
    color: Color,
    alignment: Alignment = Alignment.BottomCenter,
    textColor: Color = Theme.colors.textSecondaryColor,
    enabled: Boolean = true,
    padding: PaddingValues = PaddingValues(start = 16.dp, end = 16.dp, bottom = 44.dp),
    onClick: () -> Unit
) {
    Box(
        modifier = modifier.then(
            Modifier.padding(padding)
        ),
        contentAlignment = alignment
    ) {
        TextButton(
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp)
                .clip(Theme.shapes.roundedButton)
                .background(color),
            enabled = enabled,
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.Transparent,
                disabledBackgroundColor = Color.White.copy(alpha = 0.7f)
            ),
            onClick = { onClick() },
        ) {
            Text(
                text = stringResource(id = textRes),
                style = Theme.fonts.bold.copy(
                    fontSize = 20.sp,
                    color = textColor
                )
            )
        }
    }
}

/**
 * добавляет бэкграунд для плавного скрытия скроллящегося контента под кнопкой
 */
@Suppress("LongParameterList")
@Composable
fun ScrollScreenActionButton(
    @StringRes textRes: Int = R.string.common_continue_button,
    additionalText: String? = null,
    height: Dp = 52.dp,
    gradient: Brush = Theme.gradients.actionButtonGradient,
    alignment: Alignment = Alignment.BottomCenter,
    textColor: Color = Theme.colors.textSecondaryColor,
    enabled: Boolean = true,
    padding: PaddingValues = PaddingValues(start = 16.dp, end = 16.dp, bottom = 44.dp),
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(140.dp)
            .background(Theme.gradients.buttonBackgroundGradient),
        contentAlignment = alignment
    ) {
        ActionButtonView(
            textRes = textRes,
            additionalText = additionalText,
            height = height,
            gradient = gradient,
            textColor = textColor,
            enabled = enabled,
            padding = padding,
            onClick = onClick,
        )
    }
}

@Suppress("LongParameterList")
@Composable
private fun ActionButtonView(
    @StringRes textRes: Int = R.string.common_continue_button,
    additionalText: String? = null,
    height: Dp = 52.dp,
    fontSize: TextUnit = 20.sp,
    additionalTextFontSize: TextUnit = 14.sp,
    gradient: Brush = Theme.gradients.actionButtonGradient,
    textColor: Color = Theme.colors.textSecondaryColor,
    enabled: Boolean = true,
    padding: PaddingValues = PaddingValues(start = 16.dp, end = 16.dp, bottom = 44.dp),
    onClick: () -> Unit
) {
    TextButton(
        modifier = Modifier
            .padding(padding)
            .fillMaxWidth()
            .height(height)
            .clip(Theme.shapes.roundedButton)
            .background(gradient),
        enabled = enabled,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color.Transparent,
            disabledBackgroundColor = Color.White.copy(alpha = 0.7f)
        ),
        onClick = { onClick() },
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(id = textRes),
                style = Theme.fonts.bold.copy(
                    fontSize = fontSize,
                    color = textColor
                )
            )
            if (!additionalText.isNullOrEmpty()) {
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = additionalText,
                    style = Theme.fonts.regular.copy(
                        fontSize = additionalTextFontSize,
                        color = textColor
                    )
                )
            }
        }
    }
}
