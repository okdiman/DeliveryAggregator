package editing.presentation.viewmodel.model

import editing.presentation.compose.model.EditProfileParamState

data class EditProfileState(
    val name: EditProfileParamState.NameState = EditProfileParamState.NameState(),
    val surname: EditProfileParamState.SurnameState = EditProfileParamState.SurnameState(),
    val secondName: EditProfileParamState.SecondNameState = EditProfileParamState.SecondNameState(),
    val email: EditProfileParamState.EmailState = EditProfileParamState.EmailState(),
    val phone: EditProfileParamState.PhoneState = EditProfileParamState.PhoneState(),
    val organizationName: EditProfileParamState.OrganizationNameState =
        EditProfileParamState.OrganizationNameState(),
    val isSaveButtonVisible: Boolean = false
)