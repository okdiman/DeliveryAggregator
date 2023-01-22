package presentation

import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import presentation.model.AddressState
import presentation.model.AddressUiModel
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
    suggests: List<AddressUiModel>,
    onClearClick: () -> Unit,
    onTextFieldChanged: (String) -> Unit,
    onSuggestClick: (AddressUiModel) -> Unit
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
            items(suggests) { SuggestsItem(scrollState, it, onSuggestClick) }
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
        hint = stringResource(id = R_core.string.transport_departure_address_hint),
        onValueChanged = { onTextFieldChanged(it) }
    )
    Spacer(modifier = Modifier.height(24.dp))
}

@Composable
private fun SuggestsItem(
    scrollState: LazyListState,
    item: AddressUiModel,
    onSuggestClick: (AddressUiModel) -> Unit
) {
    val rootController = LocalRootController.current
    val coroutineScope = rememberCoroutineScope()
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(Theme.shapes.textFields)
                .clickable {
                    onSuggestClick(item)
                    if (item.house.isNotEmpty()) {
                        rootController
                            .findModalController()
                            .popBackStack(null)
                    } else {
                        coroutineScope.launch {
                            scrollState.animateScrollToItem(0)
                        }
                    }
                }
        ) {
            Icon(
                painter = painterResource(id = R_core.drawable.geolocation_ic),
                contentDescription = null
            )
            Spacer(modifier = Modifier.width(12.dp))
            Column {
                Text(
                    text = item.subtitle,
                    style = Theme.fonts.regular
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = item.city, style = Theme.fonts.regular.copy(
                        fontSize = 14.sp,
                        color = Theme.colors.textFourthColor
                    )
                )
            }
        }
        Spacer(modifier = Modifier.height(14.dp))
        Divider(
            modifier = Modifier.padding(start = 36.dp),
            color = Theme.colors.dividerColor
        )
        Spacer(modifier = Modifier.height(14.dp))
    }
}