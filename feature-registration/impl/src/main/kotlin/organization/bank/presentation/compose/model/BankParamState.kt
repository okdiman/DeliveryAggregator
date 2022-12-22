package organization.bank.presentation.compose.model

import androidx.annotation.StringRes
import view.model.DefaultParamState
import trinity_monsters.wildberries_delivery_aggregator.feature_registration.impl.R

sealed class BankParamState(
    override val stateText: String,
    override val isError: Boolean,
    @StringRes override val stateError: Int
) : DefaultParamState(stateText, isError, stateError) {
    data class PaymentAccState(
        val text: String = "",
        val isPaymentAccError: Boolean = false,
        @StringRes val error: Int = R.string.payment_acc_error
    ) : BankParamState(text, isPaymentAccError, error)

    data class CorrAccState(
        val text: String = "",
        val isCorrAccError: Boolean = false,
        @StringRes val error: Int = R.string.corr_acc_error
    ) : BankParamState(text, isCorrAccError, error)

    data class BikState(
        val text: String = "",
        val isBikError: Boolean = false,
        @StringRes val error: Int = R.string.bik_error
    ) : BankParamState(text, isBikError, error)

    data class BankNameState(
        val text: String = "",
        val isBankNameError: Boolean = false,
        @StringRes val error: Int = R.string.few_symbols_error
    ) : BankParamState(text, isBankNameError, error)
}