import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import theme.Theme
import trinity_monsters.wildberries_delivery_aggregator.core_ui.R

@Suppress("LongParameterList")
@Composable
fun ActionButton(
    modifier: Modifier = Modifier,
    @StringRes textRes: Int = R.string.continue_button,
    gradient: Brush = Theme.gradients.actionButtonGradient,
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
                .background(gradient),
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

@Suppress("LongParameterList")
@Composable
fun ActionButton(
    modifier: Modifier = Modifier,
    @StringRes textRes: Int = R.string.continue_button,
    color: Color,
    alignment: Alignment = Alignment.BottomCenter,
    textColor: Color = Theme.colors.textSecondaryColor,
    enabled: Boolean = true,
    padding: PaddingValues = PaddingValues(start = 16.dp, end = 16.dp, bottom = 44.dp),
    onClick: () -> Unit
) {
    Box(
        modifier = modifier.then(
            Modifier
                .padding(padding)
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