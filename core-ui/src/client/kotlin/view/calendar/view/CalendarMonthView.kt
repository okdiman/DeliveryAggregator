package view.calendar.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import theme.Theme
import trinity_monsters.delivery_aggregator.core_ui.R
import view.calendar.constants.CalendarConstants.ID_ARROW_NEXT
import view.calendar.constants.CalendarConstants.ID_ARROW_PAST

@Composable
internal fun CalendarMonthView(
    month: String,
    textStyle: TextStyle,
    onClick: (idArrow: Int) -> Unit,
) {
    Row(
        modifier = Modifier.padding(bottom = 28.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            modifier = Modifier
                .clip(shape = Theme.shapes.roundedButton)
                .clickable { onClick(ID_ARROW_PAST) },
            painter = painterResource(id = R.drawable.calendar_arrow_ic),
            contentDescription = ""
        )
        Text(
            modifier = Modifier.weight(1f),
            text = month,
            textAlign = TextAlign.Center,
            style = textStyle
        )
        Image(
            modifier = Modifier
                .clip(shape = Theme.shapes.roundedButton)
                .rotate(180f)
                .clickable { onClick(ID_ARROW_NEXT) },
            painter = painterResource(id = R.drawable.calendar_arrow_ic),
            contentDescription = ""
        )
    }
}