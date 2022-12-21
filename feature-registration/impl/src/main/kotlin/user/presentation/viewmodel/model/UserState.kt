package user.presentation.viewmodel.model

import user.presentation.compose.model.UserParamState

data class UserState(
    val name: UserParamState.NameState = UserParamState.NameState(),
    val surname: UserParamState.SurnameState = UserParamState.SurnameState(),
    val secondName: UserParamState.SecondNameState = UserParamState.SecondNameState(),
    val email: UserParamState.EmailState = UserParamState.EmailState(),
    val isCreateAccButtonEnabled: Boolean = false
)