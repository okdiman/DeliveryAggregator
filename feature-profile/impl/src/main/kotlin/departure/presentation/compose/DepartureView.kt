package departure.presentation.compose

import ErrorScreen
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.RadioButton
import androidx.compose.material.RadioButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import departure.presentation.viewmodel.model.DepartureEvent
import departure.presentation.viewmodel.model.DepartureState
import theme.Theme
import trinity_monsters.wildberries_delivery_aggregator.feature_profile.impl.R
import view.BackButton
import view.ProgressIndicator

@Suppress("LongMethod")
@Composable
internal fun DepartureView(state: DepartureState, eventHandler: (DepartureEvent) -> Unit) {
    when {
        state.isLoading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                ProgressIndicator()
            }
        }
        state.isError -> {
            ErrorScreen { eventHandler(DepartureEvent.OnRetryClick) }
        }
        else -> {
            LazyColumn(
                modifier = Modifier
                    .padding(PaddingValues(start = 16.dp, end = 16.dp))
            ) {
                item {
                    Box(modifier = Modifier.fillMaxWidth()) {
                        BackButton(modifier = Modifier.padding(top = 3.dp)) {
                            eventHandler(DepartureEvent.OnBackClick)
                        }
                        Text(
                            modifier = Modifier.align(Alignment.Center),
                            text = stringResource(R.string.depart_address),
                            style = Theme.fonts.bold.copy(fontSize = 20.sp)
                        )
                    }
                    Spacer(Modifier.height(24.dp))
                }
                items(state.addresses) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(
                            modifier = Modifier.size(20.dp),
                            selected = it.isSelected,
                            onClick = { eventHandler(DepartureEvent.OnAddressClick(it.id)) },
                            colors = RadioButtonDefaults.colors(
                                selectedColor = Theme.colors.radioButtonColor,
                                unselectedColor = Theme.colors.radioButtonColor
                            )
                        )
                        Column(
                            modifier = Modifier
                                .padding(start = 10.dp)
                                .weight(1f)
                        ) {
                            Text(
                                text = it.address
                            )
                            if (it.comment.isNotEmpty()) Text(
                                text = it.comment
                            )
                        }
                        Icon(
                            modifier = Modifier
                                .clip(Theme.shapes.roundedButton)
                                .clickable {
                                    eventHandler(DepartureEvent.OnEditClick(it.id))
                                },
                            painter = painterResource(id = R.drawable.profile_edit_ic),
                            contentDescription = null
                        )
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                }
                item {
                    Row(modifier = Modifier.fillMaxWidth()) {
                        Text(
                            modifier = Modifier.weight(1f),
                            text = stringResource(id = R.string.add_new_address)
                        )
                        Icon(
                            modifier = Modifier
                                .padding(start = 16.dp)
                                .clip(Theme.shapes.roundedButton)
                                .clickable { eventHandler(DepartureEvent.OnAddAddressClick) },
                            painter = painterResource(id = R.drawable.profile_add_ic),
                            contentDescription = null
                        )
                    }
                }
            }
        }
    }
}