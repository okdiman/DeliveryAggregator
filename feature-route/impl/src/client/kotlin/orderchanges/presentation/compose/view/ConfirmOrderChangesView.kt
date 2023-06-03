package orderchanges.presentation.compose.view

import CommonErrorScreen
import ScrollScreenActionButton
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import orderchanges.presentation.viewmodel.model.ConfirmOrderChangesEvent
import orderchanges.presentation.viewmodel.model.ConfirmOrderChangesState
import theme.Theme
import trinity_monsters.delivery_aggregator.feature_route.impl.R
import utils.CommonConstants.Helpers.NUMBER
import utils.ext.asPriceInRubles
import view.BackButtonView

@Composable
internal fun ConfirmOrderChangesView(
    state: ConfirmOrderChangesState,
    eventHandler: (ConfirmOrderChangesEvent) -> Unit,
) {
    if (state.isError) {
        CommonErrorScreen { eventHandler(ConfirmOrderChangesEvent.OnRetryClick) }
    } else {
        val startState = remember { MutableTransitionState(false) }.also {
            it.targetState = true
        }
        AnimatedVisibility(visibleState = startState, enter = slideInVertically()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Column(
                    modifier = Modifier
                        .verticalScroll(rememberScrollState())
                        .padding(
                            top = 16.dp,
                            start = 16.dp,
                            end = 16.dp,
                            bottom = 70.dp
                        )
                ) {
                    Title(state.orderId, eventHandler)
                    Spacer(modifier = Modifier.height(16.dp))
                    if (state.isLoading) {
                        ConfirmOrderChangesLoadingView()
                    } else {
                        BeforeAfterText()
                        Spacer(modifier = Modifier.height(22.dp))
                        ChangedDetail(
                            title = stringResource(R.string.order_changes_boxes_count),
                            oldValue = state.changes?.before?.boxes?.toString().orEmpty(),
                            newValue = state.changes?.after?.boxes?.toString().orEmpty(),
                        )
                        Spacer(modifier = Modifier.height(22.dp))
                        ChangedDetail(
                            title = stringResource(R.string.order_changes_pallets_count),
                            oldValue = state.changes?.before?.pallets?.toString().orEmpty(),
                            newValue = state.changes?.after?.pallets?.toString().orEmpty(),
                        )
                        Spacer(modifier = Modifier.height(22.dp))
                        ChangedDetail(
                            title = stringResource(R.string.order_changes_cargo_type),
                            oldValue = state.changes?.before?.cargoType?.text.orEmpty(),
                            newValue = state.changes?.after?.cargoType?.text.orEmpty(),
                        )
                        Spacer(modifier = Modifier.height(22.dp))
                        ChangedDetail(
                            title = stringResource(R.string.order_changes_extra),
                            oldValue = state.changes?.before?.extras
                                ?.joinToString(separator = "\n") { it.text }
                                .orEmpty(),
                            newValue = state.changes?.after?.extras
                                ?.joinToString(separator = "\n") { it.text }
                                .orEmpty()
                        )
                        Spacer(modifier = Modifier.height(22.dp))
                        ChangedDetail(
                            title = stringResource(R.string.order_changes_price),
                            oldValue = state.changes?.before?.price?.asPriceInRubles().orEmpty(),
                            newValue = state.changes?.after?.price?.asPriceInRubles().orEmpty()
                        )
                    }
                }

                ConfirmChangesButtonView(state, eventHandler)
            }
        }
    }
}

@Composable
private fun BeforeAfterText() {
    Row {
        Text(
            modifier = Modifier.fillMaxWidth(0.5f),
            text = stringResource(R.string.order_changes_before),
            style = Theme.fonts.bold.copy(fontSize = 24.sp)
        )
        Text(
            modifier = Modifier.fillMaxWidth(0.5f),
            text = stringResource(R.string.order_changes_after),
            style = Theme.fonts.bold.copy(fontSize = 24.sp)
        )
    }
}

@Composable
private fun ChangedDetail(title: String, oldValue: String, newValue: String) {
    val hasValueChanged = (oldValue != newValue)

    Text(
        text = title,
        style = Theme.fonts.regular.copy(color = Theme.colors.darkLabelColor, fontSize = 16.sp)
    )
    Spacer(modifier = Modifier.height(10.dp))
    Row(modifier = Modifier.height(IntrinsicSize.Min)) {
        Column(
            modifier = Modifier
                .weight(0.5f)
                .fillMaxHeight(), verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = oldValue,
                style = Theme.fonts.regular,
            )
            Divider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                color = Theme.colors.dividerColor,
            )
        }
        Spacer(modifier = Modifier.width(32.dp))
        Column(
            modifier = Modifier
                .weight(0.5f)
                .fillMaxHeight(), verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = newValue,
                style = if (hasValueChanged) Theme.fonts.bold else Theme.fonts.regular,
            )
            Divider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                color = Theme.colors.dividerColor,
            )
        }
    }
}

@Composable
private fun ConfirmChangesButtonView(
    state: ConfirmOrderChangesState,
    eventHandler: (ConfirmOrderChangesEvent) -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 8.dp),
        contentAlignment = Alignment.BottomCenter
    ) {
        ScrollScreenActionButton(
            text = stringResource(R.string.order_confirm_changes),
            enabled = !state.isConfirming && !state.isLoading && !state.isError,
            alignment = Alignment.BottomCenter,
            padding = PaddingValues(horizontal = 8.dp, vertical = 16.dp)
        ) { eventHandler(ConfirmOrderChangesEvent.OnConfirmClick) }
    }
}

@Composable
private fun Title(id: Long, eventHandler: (ConfirmOrderChangesEvent) -> Unit) {
    Box(modifier = Modifier.fillMaxWidth()) {
        BackButtonView { eventHandler(ConfirmOrderChangesEvent.OnBackClick) }
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = buildString { append(NUMBER + id) },
            style = Theme.fonts.bold.copy(
                fontSize = 20.sp
            )
        )
    }
    Spacer(modifier = Modifier.height(24.dp))
}