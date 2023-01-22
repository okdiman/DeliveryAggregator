package transport.presentation.compose

import ScrollScreenActionButton
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import root.RegistrationConstants
import root.presentation.RegistrationTitleView
import transport.presentation.viewmodel.model.TransportEvent
import transport.presentation.viewmodel.model.TransportState
import trinity_monsters.delivery_aggregator.feature_registration.impl.R
import utils.CommonConstants.LIMITS.Transport.CAR_BRAND_MAX_CHARS
import utils.CommonConstants.LIMITS.Transport.CAR_CAPACITY_MAX_CHARS
import utils.CommonConstants.LIMITS.Transport.CAR_CATEGORY_MAX_CHARS
import utils.CommonConstants.LIMITS.Transport.LICENCE_PLATE_MAX_CHARS
import view.StandardTextField
import trinity_monsters.delivery_aggregator.core_ui.R as R_core

@Composable
fun TransportView(state: TransportState, eventHandler: (TransportEvent) -> Unit) {
    Column(
        modifier = Modifier
            .padding(PaddingValues(start = 16.dp, end = 16.dp))
            .verticalScroll(rememberScrollState())
    ) {
        RegistrationTitleView(
            isBackButtonVisible = true,
            onButtonClick = { eventHandler(TransportEvent.OnBackButtonClick) },
            step = RegistrationConstants.Step.THREE,
            imageRes = R.drawable.transport_info_ic,
            titleRes = R_core.string.transport_title
        )
        TransportTextFieldsBlock(state = state, eventHandler = eventHandler)
    }
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomCenter
    ) {
        ScrollScreenActionButton(
            enabled = state.isContinueButtonEnabled
        ) {
            eventHandler(TransportEvent.OnContinueButtonClick)
        }
    }
}

@Composable
fun TransportTextFieldsBlock(state: TransportState, eventHandler: (TransportEvent) -> Unit) {
    StandardTextField(
        title = stringResource(R_core.string.transport_license_plate),
        state = state.licencePlate,
        hint = stringResource(R_core.string.transport_license_plate_hint),
        maxChar = LICENCE_PLATE_MAX_CHARS
    ) { eventHandler(TransportEvent.OnLicencePlateChanged(it)) }
    StandardTextField(
        modifier = Modifier.clickable { eventHandler(TransportEvent.OnDepartAddressClick) },
        title = stringResource(R_core.string.transport_departure_address),
        state = state.departureAddress,
        hint = stringResource(R_core.string.transport_departure_address_hint),
        enabled = false,
        trailingIcon = {
            Icon(
                painter = painterResource(id = R_core.drawable.chevron_ic),
                contentDescription = null
            )
        }
    )
    StandardTextField(
        title = stringResource(R_core.string.transport_car_brand),
        state = state.carBrand,
        hint = stringResource(R_core.string.transport_car_brand_hint),
        maxChar = CAR_BRAND_MAX_CHARS
    ) { eventHandler(TransportEvent.OnCarBrandChanged(it)) }
    StandardTextField(
        title = stringResource(R_core.string.transport_car_category),
        state = state.carCategory,
        hint = stringResource(R_core.string.transport_car_category_hint),
        maxChar = CAR_CATEGORY_MAX_CHARS
    ) { eventHandler(TransportEvent.OnCarCategoryChanged(it)) }
    StandardTextField(
        title = stringResource(R_core.string.transport_car_load_capacity),
        state = state.carLoadCapacity,
        hint = stringResource(R_core.string.transport_car_load_capacity_hint),
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
        maxChar = CAR_CAPACITY_MAX_CHARS
    ) { eventHandler(TransportEvent.OnCarLoadCapacityChanged(it)) }
    StandardTextField(
        title = stringResource(R_core.string.transport_car_capacity),
        state = state.carCapacity,
        hint = stringResource(R_core.string.transport_car_capacity_hint),
        discription = stringResource(id = R_core.string.transport_car_capacity_subtitle),
        isDigits = true,
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
        maxChar = CAR_CAPACITY_MAX_CHARS
    ) { eventHandler(TransportEvent.OnCarCapacityChanged(it)) }
    Spacer(modifier = Modifier.height(120.dp))
}