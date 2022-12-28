package user.presentation.compose

import ActionButton
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import root.RegistrationConstants.Step.FOUR
import view.StandardTextField
import root.presentation.TitleRegistrationView
import trinity_monsters.wildberries_delivery_aggregator.feature_registration.impl.R
import user.presentation.viewmodel.model.UserEvent
import user.presentation.viewmodel.model.UserState
import utils.CommonConstants.LIMITS.Common.MAX_NAME_CHARS
import utils.CommonConstants.LIMITS.User.MAX_USER_NAME_CHARS
import trinity_monsters.wildberries_delivery_aggregator.core_ui.R as R_core

@Composable
fun UserView(state: UserState, eventHandler: (UserEvent) -> Unit) {
    Column(
        modifier = Modifier
            .padding(PaddingValues(start = 16.dp, end = 16.dp))
            .verticalScroll(rememberScrollState())
    ) {
        TitleRegistrationView(
            step = FOUR,
            imageRes = R.drawable.user_info_ic,
            titleRes = R_core.string.user_title,
            isBackButtonVisible = true,
            onButtonClick = { eventHandler(UserEvent.OnBackClick) }
        )
        UserTextFieldsBlock(state, eventHandler)
    }
    ActionButton(
        modifier = Modifier.fillMaxSize(),
        textRes = R.string.create_acc_button,
        enabled = state.isCreateAccButtonEnabled
    ) { eventHandler(UserEvent.OnCreateAccClick) }
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