package editing.presentation.compose

import ScrollScreenActionButton
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import editing.presentation.viewmodel.model.EditProfileEvent
import editing.presentation.viewmodel.model.EditProfileState
import modifiers.autoScrollInFocus
import theme.Theme
import trinity_monsters.delivery_aggregator.feature_profile.impl.R
import utils.CommonConstants
import view.BackButton
import view.StandardTextField
import trinity_monsters.delivery_aggregator.core_ui.R as R_core

@Composable
internal fun EditProfileView(state: EditProfileState, eventHandler: (EditProfileEvent) -> Unit) {
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
                eventHandler(EditProfileEvent.OnBackClick)
            }
            Text(
                modifier = Modifier.align(Alignment.Center),
                text = stringResource(R.string.editing_profile),
                style = Theme.fonts.bold.copy(fontSize = 20.sp)
            )
        }
        Spacer(Modifier.height(24.dp))
        EditProfileFieldsBlock(
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
            ) { eventHandler(EditProfileEvent.OnSaveEditingClick) }
        }
    }
}

@Suppress("LongMethod")
@Composable
private fun EditProfileFieldsBlock(
    modifier: Modifier,
    state: EditProfileState,
    eventHandler: (EditProfileEvent) -> Unit
) {
    StandardTextField(
        modifier = modifier,
        title = stringResource(R_core.string.user_name),
        state = state.name,
        hint = stringResource(R_core.string.user_name_hint),
        maxChar = CommonConstants.LIMITS.User.MAX_USER_NAME_CHARS
    ) { eventHandler(EditProfileEvent.OnNameChanged(it)) }
    StandardTextField(
        modifier = modifier,
        title = stringResource(R_core.string.user_surname),
        state = state.surname,
        hint = stringResource(R_core.string.user_surname_hint),
        maxChar = CommonConstants.LIMITS.User.MAX_USER_NAME_CHARS
    ) { eventHandler(EditProfileEvent.OnSurnameChanged(it)) }
    StandardTextField(
        modifier = modifier,
        title = stringResource(R_core.string.user_second_name),
        state = state.secondName,
        hint = stringResource(R_core.string.user_second_name_hint),
        maxChar = CommonConstants.LIMITS.User.MAX_USER_NAME_CHARS
    ) { eventHandler(EditProfileEvent.OnSecondNameChanged(it)) }
    StandardTextField(
        modifier = modifier,
        title = stringResource(R_core.string.user_email),
        state = state.email,
        hint = stringResource(R_core.string.user_email_hint),
        maxChar = CommonConstants.LIMITS.Common.MAX_NAME_CHARS
    ) { eventHandler(EditProfileEvent.OnEmailChanged(it)) }
    StandardTextField(
        title = stringResource(R.string.editing_mobile),
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
        title = stringResource(R_core.string.common_company_name),
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
        text = stringResource(id = R.string.editing_delete_profile),
        style = Theme.fonts.bold
    )
    Spacer(modifier = Modifier.height(if (state.isSaveButtonVisible) 120.dp else 24.dp))
}