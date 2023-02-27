package root.presentation

import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import presentation.AddressSuggestUiModel
import root.presentation.model.AddressState
import root.presentation.view.SuggestItemView
import ru.alexgladkov.odyssey.compose.local.LocalRootController
import theme.Theme
import view.ProgressIndicator
import view.StandardTextField
import trinity_monsters.delivery_aggregator.core_ui.R as R_core

@Suppress("LongMethod")
@Composable
fun AddressBSScreen(
    @StringRes titleRes: Int = R_core.string.common_add_address,
    state: AddressState,
    suggests: List<AddressSuggestUiModel>,
    isNeedComment: Boolean = false,
    onClearClick: () -> Unit,
    onTextFieldChanged: (String) -> Unit,
    onSuggestClick: (AddressSuggestUiModel) -> Unit
) {
    val scrollState = rememberLazyListState()
    val focusRequester = remember { FocusRequester() }
    LazyColumn(
        state = scrollState,
        modifier = Modifier
            .padding(start = 16.dp, end = 16.dp),
    ) {
        item {
            AddressTitleView(titleRes)
        }
        item {
            AddressTextFieldView(state, onClearClick, onTextFieldChanged, focusRequester)
        }
        if (state.isSuggestLoading) {
            item {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    ProgressIndicator()
                }
            }
        } else {
            items(suggests) { SuggestItemView(scrollState, it, isNeedComment, onSuggestClick) }
        }
    }
    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }
}

@Composable
private fun AddressTitleView(@StringRes titleRes: Int) {
    val rootController = LocalRootController.current
    Row(modifier = Modifier.padding(top = 40.dp)) {
        Text(
            modifier = Modifier.weight(1f),
            text = stringResource(id = titleRes),
            style = Theme.fonts.bold.copy(fontSize = 24.sp)
        )
        Icon(
            modifier = Modifier
                .padding(top = 8.dp)
                .clip(Theme.shapes.roundedButton)
                .clickable {
                    rootController
                        .findModalController()
                        .popBackStack(null)
                },
            painter = painterResource(id = R_core.drawable.close_ic),
            contentDescription = null
        )
    }
}

@Composable
private fun AddressTextFieldView(
    state: AddressState,
    onClearClick: () -> Unit,
    onTextFieldChanged: (String) -> Unit,
    focusRequester: FocusRequester
) {
    StandardTextField(
        modifier = Modifier.focusRequester(focusRequester),
        state = state,
        hasTitle = false,
        leadingIcon = {
            Icon(
                painter = painterResource(id = R_core.drawable.search_ic),
                contentDescription = null
            )
        },
        trailingIcon = {
            Icon(
                modifier = Modifier
                    .clip(Theme.shapes.roundedButton)
                    .clickable { onClearClick() },
                painter = painterResource(id = R_core.drawable.delete_ic),
                contentDescription = null
            )
        },
        hint = stringResource(id = R_core.string.common_address_hint),
        onValueChanged = { onTextFieldChanged(it) }
    )
    Spacer(modifier = Modifier.height(24.dp))
}