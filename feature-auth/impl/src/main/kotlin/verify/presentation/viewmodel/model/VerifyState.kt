package verify.presentation.viewmodel.model

data class VerifyState(
    val title: String = "",
    val code: String = "",
    val isCodeError: Boolean = false,
    val isLoading: Boolean = false,
    val isRetryButtonAvailable: Boolean = false,
    val isTimerVisible: Boolean = true,
    val renewalPeriod: Int = BASE_RENEWAL_PERIOD
) {
    companion object {
        private const val BASE_RENEWAL_PERIOD = 59
    }
}