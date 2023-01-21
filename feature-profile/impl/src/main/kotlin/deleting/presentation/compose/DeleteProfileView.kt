package deleting.presentation.compose

import ActionButton
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import deleting.presentation.viewmodel.model.DeleteProfileEvent
import theme.Theme
import trinity_monsters.wildberries_delivery_aggregator.feature_profile.impl.R

@Composable
internal fun DeleteProfileView(eventHandler: (DeleteProfileEvent) -> Unit) {
    Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 40.dp)) {
        Text(
            text = stringResource(id = R.string.deleting_are_u_sure_want_delete),
            style = Theme.fonts.bold.copy(fontSize = 20.sp),
            textAlign = TextAlign.Center
        )
        ActionButton(
            textRes = R.string.deleting_delete_acc,
            alignment = Alignment.Center,
            padding = PaddingValues(top = 24.dp)
        ) { eventHandler(DeleteProfileEvent.OnDeleteClick) }
        ActionButton(
            textRes = R.string.deleting_not_delete,
            textColor = Theme.colors.textPrimaryColor,
            color = Theme.colors.disabledButtonColor,
            alignment = Alignment.Center,
            padding = PaddingValues(top = 16.dp)
        ) { eventHandler(DeleteProfileEvent.OnBackClick) }
    }
}