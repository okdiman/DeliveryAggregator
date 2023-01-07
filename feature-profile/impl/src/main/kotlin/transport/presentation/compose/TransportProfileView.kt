package transport.presentation.compose

import ActionButton
import androidx.compose.foundation.clickable
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
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import theme.Theme
import transport.presentation.viewmodel.model.TransportProfileEvent
import transport.presentation.viewmodel.model.TransportProfileState
import trinity_monsters.wildberries_delivery_aggregator.feature_profile.impl.R
import utils.CommonConstants
import view.BackButton
import view.StandardTextField
import trinity_monsters.wildberries_delivery_aggregator.core_ui.R as R_core

@Composable
internal fun TransportProfileView(
    state: TransportProfileState,
    eventHandler: (TransportProfileEvent) -> Unit
) {
    Column(
        modifier = Modifier
            .padding(PaddingValues(start = 16.dp, end = 16.dp, top = 4.dp))
            .verticalScroll(rememberScrollState())
    ) {
        Box(modifier = Modifier.fillMaxWidth()) {
            BackButton(modifier = Modifier.padding(top = 3.dp)) {
                eventHandler(TransportProfileEvent.OnBackButtonClick)
            }
            Text(
                modifier = Modifier.align(Alignment.Center),
                text = stringResource(R.string.profile),
                style = Theme.fonts.bold.copy(fontSize = 20.sp)
            )
        }
        Spacer(Modifier.height(24.dp))
        TransportTextFieldsBlock(
            state = state,
            eventHandler = eventHandler
        )
    }
    if (state.isSaveButtonVisible) {
        ActionButton(
            modifier = Modifier.fillMaxSize(),
            textRes = R_core.string.save
        ) { eventHandler(TransportProfileEvent.OnSaveButtonClick) }
    }
}

@Composable
fun TransportTextFieldsBlock(
    state: TransportProfileState,
    eventHandler: (TransportProfileEvent) -> Unit
) {
    StandardTextField(
        title = stringResource(R_core.string.license_plate),
        state = state.licencePlate,
        hint = stringResource(R_core.string.license_plate_hint),
        maxChar = CommonConstants.LIMITS.Transport.LICENCE_PLATE_MAX_CHARS
    ) { eventHandler(TransportProfileEvent.OnLicencePlateChanged(it)) }
    StandardTextField(
        modifier = Modifier.clickable { eventHandler(TransportProfileEvent.OnDepartAddressClick) },
        title = stringResource(R_core.string.departure_address),
        state = state.departureAddress,
        hint = stringResource(R_core.string.departure_address_hint),
        enabled = false,
        trailingIcon = {
            Icon(
                painter = painterResource(id = R_core.drawable.chevron_ic),
                contentDescription = null
            )
        }
    )
    StandardTextField(
        title = stringResource(R_core.string.car_brand),
        state = state.carBrand,
        hint = stringResource(R_core.string.car_brand_hint),
        maxChar = CommonConstants.LIMITS.Transport.CAR_BRAND_MAX_CHARS
    ) { eventHandler(TransportProfileEvent.OnCarBrandChanged(it)) }
    StandardTextField(
        title = stringResource(R_core.string.car_category),
        state = state.carCategory,
        hint = stringResource(R_core.string.car_category_hint),
        maxChar = CommonConstants.LIMITS.Transport.CAR_CATEGORY_MAX_CHARS
    ) { eventHandler(TransportProfileEvent.OnCarCategoryChanged(it)) }
    StandardTextField(
        title = stringResource(R_core.string.car_load_capacity),
        state = state.carLoadCapacity,
        hint = stringResource(R_core.string.car_load_capacity_hint),
        isDigits = true,
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
        maxChar = CommonConstants.LIMITS.Transport.CAR_CAPACITY_MAX_CHARS
    ) { eventHandler(TransportProfileEvent.OnCarLoadCapacityChanged(it)) }
    StandardTextField(
        title = stringResource(R_core.string.car_capacity),
        state = state.carCapacity,
        hint = stringResource(R_core.string.car_capacity_hint),
        isDigits = true,
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
        maxChar = CommonConstants.LIMITS.Transport.CAR_CAPACITY_MAX_CHARS
    ) {
        eventHandler(TransportProfileEvent.OnCarCapacityChanged(it))
    }
    Spacer(modifier = Modifier.height(if (state.isSaveButtonVisible) 120.dp else 24.dp))
}