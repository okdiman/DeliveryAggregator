package root.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import presentation.AddressUiModel
import ru.alexgladkov.odyssey.compose.local.LocalRootController
import theme.Theme
import trinity_monsters.wildberries_delivery_aggregator.feature_registration.impl.R
import view.model.DefaultParamState
import trinity_monsters.wildberries_delivery_aggregator.core_ui.R as R_core

@Composable
fun RegistrationAddressBSScreen(
    state: DefaultParamState,
    suggests: List<AddressUiModel>,
    onClearClick: () -> Unit,
    onTextFieldChanged: (String) -> Unit,
    onSuggestClick: (AddressUiModel) -> Unit
) {
    val rootController = LocalRootController.current
    val scrollState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()
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
            RegistrationTextField(
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
                hint = stringResource(id = R.string.departure_address_hint),
                onValueChanged = { onTextFieldChanged(it) }
            )
            Spacer(modifier = Modifier.height(24.dp))
        }
        items(suggests) {
            Column(
                modifier = Modifier.clickable {
                    onSuggestClick(it)
                    if (it.house.isNotEmpty()) {
                        rootController.findModalController().popBackStack(null)
                    } else {
                        coroutineScope.launch {
                            scrollState.animateScrollToItem(0)
                        }
                    }
                }
            ) {
                Row {
                    Icon(
                        painter = painterResource(id = R_core.drawable.geolocation_ic),
                        contentDescription = null
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Column {
                        Text(
                            text = it.subtitle,
                            style = Theme.fonts.regular.copy(
                                fontSize = 16.sp
                            )
                        )
                        Text(
                            text = it.city, style = Theme.fonts.regular.copy(
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
    }
}