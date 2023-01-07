package presentation

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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
import trinity_monsters.wildberries_delivery_aggregator.core_ui.R as R_core

@Composable
fun AddressBSScreen(
    state: AddressState,
    suggests: List<AddressUiModel>,
    onClearClick: () -> Unit,
    onTextFieldChanged: (String) -> Unit,
    onSuggestClick: (AddressUiModel) -> Unit
) {
    val rootController = LocalRootController.current
    val scrollState = rememberLazyListState()
    LazyColumn(
        state = scrollState,
        modifier = Modifier
            .padding(start = 16.dp, end = 16.dp),
    ) {
        item {
            Row(modifier = Modifier.padding(top = 30.dp)) {
                Text(
                    modifier = Modifier.weight(1f),
                    text = stringResource(id = R_core.string.add_address),
                    style = Theme.fonts.bold.copy(fontSize = 24.sp)
                )
                Icon(
                    modifier = Modifier
                        .padding(top = 8.dp)
                        .clickable {
                            rootController
                                .findModalController()
                                .popBackStack(null)
                        },
                    painter = painterResource(id = R_core.drawable.close_ic),
                    contentDescription = null
                )
            }
            StandardTextField(
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
                        modifier = Modifier.clickable { onClearClick() },
                        painter = painterResource(id = R_core.drawable.delete_ic),
                        contentDescription = null
                    )
                },
                hint = stringResource(id = R_core.string.departure_address_hint),
                onValueChanged = { onTextFieldChanged(it) }
            )
            Spacer(modifier = Modifier.height(24.dp))
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
                Text(
                    text = item.city, style = Theme.fonts.regular.copy(
                        fontSize = 14.sp,
                        color = Theme.colors.textFourthColor
                    )
                )
            }
        }
        Spacer(modifier = Modifier.height(12.dp))
        Divider(
            modifier = Modifier.padding(start = 36.dp),
            color = Theme.colors.dividerColor
        )
        Spacer(modifier = Modifier.height(12.dp))
    }
}