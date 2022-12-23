package transport.presentation.compose

import ActionButton
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import root.RegistrationConstants
import root.RegistrationConstants.Limits.Common.MAX_NAME_CHARS
import root.RegistrationConstants.Limits.Transport.CAR_BRAND_MAX_CHARS
import root.RegistrationConstants.Limits.Transport.CAR_CAPACITY_MAX_CHARS
import root.RegistrationConstants.Limits.Transport.CAR_CATEGORY_MAX_CHARS
import root.RegistrationConstants.Limits.Transport.LICENCE_PLATE_MAX_CHARS
import root.presentation.RegistrationTextField
import root.presentation.TitleRegistrationView
import transport.presentation.viewmodel.model.TransportEvent
import transport.presentation.viewmodel.model.TransportState
import trinity_monsters.wildberries_delivery_aggregator.feature_registration.impl.R

@Composable
fun TransportView(state: TransportState, eventHandler: (TransportEvent) -> Unit) {
    Column(
        modifier = Modifier
            .padding(PaddingValues(start = 16.dp, end = 16.dp))
            .verticalScroll(rememberScrollState())
    ) {
        TitleRegistrationView(
            isBackButtonVisible = true,
            onButtonClick = { eventHandler(TransportEvent.OnBackButtonClick) },
            step = RegistrationConstants.Step.THREE,
            imageRes = R.drawable.transport_info_ic,
            titleRes = R.string.transport_title
        )
        TransportTextFieldsBlock(state = state, eventHandler = eventHandler)
    }
    ActionButton(enabled = state.isContinueButtonEnabled) {
        eventHandler(TransportEvent.OnContinueButtonClick)
    }
}

@Composable
fun TransportTextFieldsBlock(state: TransportState, eventHandler: (TransportEvent) -> Unit) {
    RegistrationTextField(
        title = stringResource(R.string.license_plate),
        state = state.licencePlate,
        hint = stringResource(R.string.license_plate_hint),
        maxChar = LICENCE_PLATE_MAX_CHARS
    ) {
        eventHandler(TransportEvent.OnLicencePlateChanged(it))
    }
    RegistrationTextField(
        title = stringResource(R.string.departure_address),
        state = state.departureAddress,
        hint = stringResource(R.string.departure_address_hint),
        maxChar = MAX_NAME_CHARS
    ) {
        eventHandler(TransportEvent.OnDepartureAddressChanged(it))
    }
    RegistrationTextField(
        title = stringResource(R.string.car_brand),
        state = state.carBrand,
        hint = stringResource(R.string.car_brand_hint),
        maxChar = CAR_BRAND_MAX_CHARS
    ) {
        eventHandler(TransportEvent.OnCarBrandChanged(it))
    }
    RegistrationTextField(
        title = stringResource(R.string.car_category),
        state = state.carCategory,
        hint = stringResource(R.string.car_category_hint),
        maxChar = CAR_CATEGORY_MAX_CHARS
    ) {
        eventHandler(TransportEvent.OnCarCategoryChanged(it))
    }
    RegistrationTextField(
        title = stringResource(R.string.car_load_capacity),
        state = state.carLoadCapacity,
        hint = stringResource(R.string.car_load_capacity_hint),
        isDigits = true,
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
        maxChar = CAR_CAPACITY_MAX_CHARS
    ) {
        eventHandler(TransportEvent.OnCarLoadCapacityChanged(it))
    }
    RegistrationTextField(
        title = stringResource(R.string.car_capacity),
        state = state.carCapacity,
        hint = stringResource(R.string.car_capacity_hint),
        isDigits = true,
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
        maxChar = CAR_CAPACITY_MAX_CHARS
    ) {
        eventHandler(TransportEvent.OnCarCapacityChanged(it))
    }
    Spacer(modifier = Modifier.height(120.dp))
}