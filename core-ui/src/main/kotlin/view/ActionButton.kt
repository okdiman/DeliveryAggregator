import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import theme.Theme

@Composable
fun ActionButton(
    gradient: Brush,
    enabled: Boolean = true,
    text: String,
    onClick: () -> Unit
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
            text = text,
            style = Theme.fonts.bold.copy(fontSize = 20.sp, color = Theme.colors.textSecondaryColor)
        )
    }
}