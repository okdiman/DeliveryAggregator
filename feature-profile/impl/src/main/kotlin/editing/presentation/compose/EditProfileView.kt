package editing.presentation.compose

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
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import editing.presentation.viewmodel.model.EditProfileEvent
import editing.presentation.viewmodel.model.EditProfileState
import theme.Theme
import trinity_monsters.wildberries_delivery_aggregator.feature_profile.impl.R
import utils.CommonConstants
import view.BackButton
import view.StandardTextField
import trinity_monsters.wildberries_delivery_aggregator.core_ui.R as R_core

@Composable
fun EditProfileView(state: EditProfileState, eventHandler: (EditProfileEvent) -> Unit) {
    Column(
        modifier = Modifier
            .padding(PaddingValues(start = 16.dp, end = 16.dp, top = 4.dp))
            .verticalScroll(rememberScrollState())
    ) {
        Box(modifier = Modifier.fillMaxWidth()) {
            BackButton(modifier = Modifier.padding(top = 3.dp)) {
                eventHandler(EditProfileEvent.OnBackClick)
            }
            Text(
                modifier = Modifier.align(Alignment.Center),
                text = stringResource(R.string.profile),
                style = Theme.fonts.bold.copy(fontSize = 20.sp)
            )
        }
        Spacer(Modifier.height(24.dp))
        EditProfileFieldsBlock(state, eventHandler)
    }
    if (state.isSaveButtonVisible) {
        ActionButton(
            modifier = Modifier.fillMaxSize(),
            textRes = R_core.string.save
        ) { eventHandler(EditProfileEvent.OnSaveEditingClick) }
    }
}

@Composable
fun EditProfileFieldsBlock(state: EditProfileState, eventHandler: (EditProfileEvent) -> Unit) {
    StandardTextField(
        title = stringResource(R_core.string.user_name),
        state = state.name,
        hint = stringResource(R_core.string.user_name_hint),
        maxChar = CommonConstants.LIMITS.User.MAX_USER_NAME_CHARS
    ) { eventHandler(EditProfileEvent.OnNameChanged(it)) }
    StandardTextField(
        title = stringResource(R_core.string.user_surname),
        state = state.surname,
        hint = stringResource(R_core.string.user_surname_hint),
        maxChar = CommonConstants.LIMITS.User.MAX_USER_NAME_CHARS
    ) { eventHandler(EditProfileEvent.OnSurnameChanged(it)) }
    StandardTextField(
        title = stringResource(R_core.string.user_second_name),
        state = state.secondName,
        hint = stringResource(R_core.string.user_second_name_hint),
        maxChar = CommonConstants.LIMITS.User.MAX_USER_NAME_CHARS
    ) { eventHandler(EditProfileEvent.OnSecondNameChanged(it)) }
    StandardTextField(
        title = stringResource(R_core.string.user_email),
        state = state.email,
        hint = stringResource(R_core.string.user_email_hint),
        maxChar = CommonConstants.LIMITS.Common.MAX_NAME_CHARS
    ) { eventHandler(EditProfileEvent.OnEmailChanged(it)) }
    StandardTextField(
        title = stringResource(R.string.mobile),
        state = state.phone,
        enabled = false,
        textStyle = Theme.fonts.regular.copy(
            color = Theme.colors.disabledTextColor
        ),
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.Transparent,
            disabledIndicatorColor = Theme.colors.disabledTextColor
        )
    )
    StandardTextField(
        title = stringResource(R_core.string.company_name),
        state = state.organizationName,
        enabled = false,
        textStyle = Theme.fonts.regular.copy(
            color = Theme.colors.disabledTextColor
        ),
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.Transparent,
            disabledIndicatorColor = Theme.colors.disabledTextColor
        )
    )
    Spacer(modifier = Modifier.height(32.dp))
    Text(
        modifier = Modifier
            .clip(Theme.shapes.textFields)
            .clickable { eventHandler(EditProfileEvent.OnDeleteAccClick) },
        text = stringResource(id = R.string.delete_profile),
        style = Theme.fonts.bold
    )
    Spacer(modifier = Modifier.height(if (state.isSaveButtonVisible) 120.dp else 24.dp))
}