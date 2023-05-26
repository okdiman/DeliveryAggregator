package view.calendar.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import trinity_monsters.delivery_aggregator.core_ui.R
import view.calendar.constants.CalendarConstants.ID_ARROW_PAST

@Composable
internal fun CalendarMonthView(
    month: String,
    textStyle: TextStyle,
    onClick: (idArrow: Int) -> Unit,
) {
    Row(
        modifier = Modifier.padding(bottom = 20.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = { onClick(ID_ARROW_PAST) }) {
            Image(
                painter = painterResource(id = R.drawable.calendar_arrow_ic),
                contentDescription = ""
            )
        }
        Text(
            modifier = Modifier.weight(1f),
            text = month,
            textAlign = TextAlign.Center,
            style = textStyle
        )
        IconButton(onClick = { onClick(ID_ARROW_PAST) }) {
            Image(
                modifier = Modifier.rotate(180f) ,
                painter = painterResource(id = R.drawable.calendar_arrow_ic),
                contentDescription = ""
            )
        }
    }
}