package login.presentation.viewmodel.model

data class LoginState(
    val phone: String = "",
    val isAgreementChecked: Boolean = false,
    val isButtonEnabled: Boolean = false,
    val isLoading: Boolean = false
)