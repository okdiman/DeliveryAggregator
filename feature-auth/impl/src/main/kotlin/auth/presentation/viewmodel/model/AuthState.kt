package auth.presentation.viewmodel.model

data class AuthState(
    val phone: String = "",
    val isAgreementChecked: Boolean = false,
    val isButtonEnabled: Boolean = false,
    val isLoading: Boolean = false
)