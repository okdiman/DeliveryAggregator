package organization.company.presentation.compose.model

import androidx.annotation.StringRes
import view.model.DefaultParamState
import trinity_monsters.wildberries_delivery_aggregator.feature_registration.impl.R

sealed class CompanyParamState(
    override val stateText: String,
    override val isError: Boolean,
    @StringRes override val stateError: Int
) : DefaultParamState(stateText, isError, stateError) {
    data class NameState(
        val text: String = "",
        val isNameError: Boolean = false,
        @StringRes val error: Int = R.string.few_symbols_error
    ) : CompanyParamState(text, isNameError, error)

    data class InnState(
        val text: String = "",
        val isInnError: Boolean = false,
        @StringRes val error: Int = R.string.inn_error
    ) : CompanyParamState(text, isInnError, error)

    data class KppState(
        val text: String = "",
        val isKppError: Boolean = false,
        @StringRes val error: Int = R.string.kpp_error
    ) : CompanyParamState(text, isKppError, error)

    data class OgrnState(
        val text: String = "",
        val isOgrnError: Boolean = false,
        @StringRes val error: Int = R.string.ogrn_error
    ) : CompanyParamState(text, isOgrnError, error)

    data class ActualAddressState(
        val text: String = "",
        val isAddressError: Boolean = false,
        @StringRes val error: Int = R.string.few_symbols_error
    ) : CompanyParamState(text, isAddressError, error)

    data class LegalAddressState(
        val text: String = "",
        val isAddressError: Boolean = false,
        @StringRes val error: Int = R.string.few_symbols_error
    ) : CompanyParamState(text, isAddressError, error)
}