package view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import theme.DeliveryAggregatorTheme
import theme.Theme
import trinity_monsters.delivery_aggregator.core_ui.R

@Composable
fun ExtrasTextField(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit
) {
    Column(
        modifier = modifier.then(
            Modifier
                .fillMaxWidth()
                .padding(top = 24.dp)
        )
    ) {
        Text(
            text = stringResource(R.string.common_extras),
            style = Theme.fonts.regular.copy(
                color = Theme.colors.textThirdColor
            )
        )
        Spacer(modifier = Modifier.height(4.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(Theme.shapes.textFields)
                .background(Theme.colors.hintBackgroundColor)
                .clickable {
                    onClick()
                }
        ) {
            Text(
                modifier = Modifier
                    .padding(16.dp)
                    .weight(1f),
                text = text.ifEmpty { stringResource(R.string.common_choose) },
                style = Theme.fonts.regular.copy(
                    color = Theme.colors.textPrimaryColor.copy(
                        alpha = if (text.isEmpty()) 0.7f else 1f
                    )
                ),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Icon(
                modifier = Modifier
                    .padding(top = 16.dp, bottom = 16.dp, end = 12.dp),
                painter = painterResource(id = R.drawable.chevron_ic),
                contentDescription = null
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
private fun ExtrasTextField_Preview() {
    DeliveryAggregatorTheme {
        ExtrasTextField(
            text = "Test text"
        ) {}
    }
}
