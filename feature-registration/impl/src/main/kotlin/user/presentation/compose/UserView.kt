package user.presentation.compose

import ScrollScreenActionButton
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import root.RegistrationConstants.Step.FOUR
import root.RegistrationConstants.Step.THREE
import root.presentation.RegistrationTitleView
import trinity_monsters.delivery_aggregator.feature_registration.impl.BuildConfig
import trinity_monsters.delivery_aggregator.feature_registration.impl.R
import user.presentation.viewmodel.model.UserEvent
import user.presentation.viewmodel.model.UserState
import utils.CommonConstants
import utils.CommonConstants.LIMITS.Common.MAX_NAME_CHARS
import utils.CommonConstants.LIMITS.User.MAX_USER_NAME_CHARS
import view.StandardTextField
import trinity_monsters.delivery_aggregator.core_ui.R as R_core

@Composable
internal fun UserView(state: UserState, eventHandler: (UserEvent) -> Unit) {
    Column(
        modifier = Modifier
            .padding(PaddingValues(start = 16.dp, end = 16.dp))
            .verticalScroll(rememberScrollState())
    ) {
        RegistrationTitleView(
            step = if (BuildConfig.FLAVOR == CommonConstants.Flavors.client) {
                THREE
            } else {
                FOUR
            },
            imageRes = R.drawable.user_info_ic,
            titleRes = R_core.string.user_title,
            isBackButtonVisible = true,
            onButtonClick = { eventHandler(UserEvent.OnBackClick) }
        )
        UserTextFieldsBlock(state, eventHandler)
    }
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomCenter
    ) {
        ScrollScreenActionButton(
            textRes = R.string.registration_create_acc_button,
            enabled = state.isCreateAccButtonEnabled
        ) { eventHandler(UserEvent.OnCreateAccClick) }
    }
}

@Composable
fun UserTextFieldsBlock(state: UserState, eventHandler: (UserEvent) -> Unit) {
    StandardTextField(
        title = stringResource(R_core.string.user_name),
        state = state.name,
        hint = stringResource(R_core.string.user_name_hint),
        maxChar = MAX_USER_NAME_CHARS
    ) {
        eventHandler(UserEvent.OnNameChanged(it))
    }
    StandardTextField(
        title = stringResource(R_core.string.user_surname),
        state = state.surname,
        hint = stringResource(R_core.string.user_surname_hint),
        maxChar = MAX_USER_NAME_CHARS
    ) {
        eventHandler(UserEvent.OnSurnameChanged(it))
    }
    StandardTextField(
        title = stringResource(R_core.string.user_second_name),
        state = state.secondName,
        hint = stringResource(R_core.string.user_second_name_hint),
        maxChar = MAX_USER_NAME_CHARS
    ) {
        eventHandler(UserEvent.OnSecondNameChanged(it))
    }
    StandardTextField(
        title = stringResource(R_core.string.user_email),
        state = state.email,
        hint = stringResource(R_core.string.user_email_hint),
        maxChar = MAX_NAME_CHARS
    ) {
        eventHandler(UserEvent.OnEmailChanged(it))
    }
    Spacer(modifier = Modifier.height(120.dp))
}