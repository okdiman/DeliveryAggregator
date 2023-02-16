package comment.presentation.compose

import ActionButton
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import comment.presentation.viewmodel.model.AddressCommentEvent
import comment.presentation.viewmodel.model.AddressCommentState
import theme.Theme
import trinity_monsters.delivery_aggregator.feature_address.impl.R
import utils.CommonConstants.LIMITS.Common.MAX_DESCRIPTION_CHARS
import view.BackButton
import view.StandardTextField

@Composable
fun AddressCommentView(state: AddressCommentState, eventHandler: (AddressCommentEvent) -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 16.dp, top = 32.dp, end = 16.dp, bottom = 16.dp)
    ) {
        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            item {
                Box(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    BackButton(modifier = Modifier.padding(top = 3.dp)) {
                        eventHandler(AddressCommentEvent.OnBackClick)
                    }
                    Text(
                        modifier = Modifier.align(Alignment.Center),
                        text = stringResource(id = R.string.address_comment_title),
                        style = Theme.fonts.bold.copy(fontSize = 20.sp)
                    )
                }
            }
            item {
                StandardTextField(
                    modifier = Modifier.defaultMinSize(minHeight = 90.dp),
                    state = state.comment,
                    hint = stringResource(R.string.address_comment_hint),
                    maxChar = MAX_DESCRIPTION_CHARS,
                    maxLines = Int.MAX_VALUE,
                    hasTitle = false
                ) { eventHandler(AddressCommentEvent.OnCommentChanged(it)) }
            }
        }
        ActionButton(
            modifier = Modifier.fillMaxSize(),
            textRes = trinity_monsters.delivery_aggregator.core_ui.R.string.common_complete,
            padding = PaddingValues(0.dp)
        ) { eventHandler(AddressCommentEvent.OnDoneClick) }
    }
}