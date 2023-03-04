package transport.presentation.compose

import ScrollScreenActionButton
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import modifiers.autoScrollInFocus
import theme.Theme
import transport.presentation.viewmodel.model.TransportProfileEvent
import transport.presentation.viewmodel.model.TransportProfileState
import trinity_monsters.delivery_aggregator.feature_profile.impl.R
import utils.CommonConstants
import view.BackButton
import view.StandardTextField
import trinity_monsters.delivery_aggregator.core_ui.R as R_core

@Composable
internal fun TransportProfileView(
    state: TransportProfileState,
    eventHandler: (TransportProfileEvent) -> Unit
) {
    val buttonHeight = remember {
        mutableStateOf(0f)
    }
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .padding(PaddingValues(start = 16.dp, end = 16.dp, top = 4.dp))
            .verticalScroll(scrollState)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp)
        ) {
            BackButton(modifier = Modifier.padding(top = 3.dp)) {
                eventHandler(TransportProfileEvent.OnBackButtonClick)
            }
            Text(
                modifier = Modifier.align(Alignment.Center),
                text = stringResource(R.string.editing_profile),
                style = Theme.fonts.bold.copy(fontSize = 20.sp)
            )
        }
        Spacer(Modifier.height(24.dp))
        TransportTextFieldsBlock(
            modifier = Modifier.autoScrollInFocus(scrollState, buttonHeight),
            state = state,
            eventHandler = eventHandler
        )
    }
    if (state.isSaveButtonVisible) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.BottomCenter
        ) {
            ScrollScreenActionButton(
                textRes = R_core.string.common_save,
                onPositioned = { buttonHeight.value = it }
            ) { eventHandler(TransportProfileEvent.OnSaveButtonClick) }
        }
    }
}

@Composable
fun TransportTextFieldsBlock(
    modifier: Modifier,
    state: TransportProfileState,
    eventHandler: (TransportProfileEvent) -> Unit
) {
    StandardTextField(
        modifier = modifier,
        title = stringResource(R_core.string.transport_license_plate),
        state = state.licencePlate,
        hint = stringResource(R_core.string.transport_license_plate_hint),
        maxChar = CommonConstants.LIMITS.Transport.LICENCE_PLATE_MAX_CHARS
    ) { eventHandler(TransportProfileEvent.OnLicencePlateChanged(it)) }
    StandardTextField(
        modifier = modifier,
        title = stringResource(R_core.string.transport_car_brand),
        state = state.carBrand,
        hint = stringResource(R_core.string.transport_car_brand_hint),
        maxChar = CommonConstants.LIMITS.Transport.CAR_BRAND_MAX_CHARS
    ) { eventHandler(TransportProfileEvent.OnCarBrandChanged(it)) }
    StandardTextField(
        modifier = modifier,
        title = stringResource(R_core.string.transport_car_category),
        state = state.carCategory,
        hint = stringResource(R_core.string.transport_car_category_hint),
        maxChar = CommonConstants.LIMITS.Transport.CAR_CATEGORY_MAX_CHARS
    ) { eventHandler(TransportProfileEvent.OnCarCategoryChanged(it)) }
    StandardTextField(
        modifier = modifier,
        title = stringResource(R_core.string.transport_car_load_capacity),
        state = state.carLoadCapacity,
        hint = stringResource(R_core.string.transport_car_load_capacity_hint),
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Decimal),
        maxChar = CommonConstants.LIMITS.Transport.CAR_CAPACITY_MAX_CHARS
    ) { eventHandler(TransportProfileEvent.OnCarLoadCapacityChanged(it)) }
    StandardTextField(
        modifier = modifier,
        title = stringResource(R_core.string.transport_car_capacity),
        state = state.carCapacity,
        hint = stringResource(R_core.string.transport_car_capacity_hint),
        isDigits = true,
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
        maxChar = CommonConstants.LIMITS.Transport.CAR_CAPACITY_MAX_CHARS
    ) {
        eventHandler(TransportProfileEvent.OnCarCapacityChanged(it))
    }
    Spacer(modifier = Modifier.height(if (state.isSaveButtonVisible) 120.dp else 24.dp))
}