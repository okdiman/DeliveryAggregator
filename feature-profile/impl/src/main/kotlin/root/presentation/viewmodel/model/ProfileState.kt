package root.presentation.viewmodel.model

import root.presentation.compose.model.ProfileItemUiModel
import root.presentation.compose.model.ProfileItemUiModel.Companion.defaultList

data class ProfileState(
    val name: String = "",
    val phone: String = "",
    val organizationName: String = "",
    val isLoading: Boolean,
    val isError: Boolean = false,
    val email: String = "",
    val uiModels: List<ProfileItemUiModel> = defaultList
)