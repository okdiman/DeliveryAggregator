package state.registration

import androidx.annotation.StringRes
import trinity_monsters.wildberries_delivery_aggregator.core_ui.R

sealed class CompanyParamState(
    val stateText: String,
    val isError: Boolean,
    @StringRes val stateError: Int
) {
    data class NameState(
        val text: String = "",
        val isNameError: Boolean = false,
        @StringRes val error: Int = R.string.company_name_error
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
        @StringRes val error: Int = R.string.address_error
    ) : CompanyParamState(text, isAddressError, error)

    data class LegalAddressState(
        val text: String = "",
        val isAddressError: Boolean = false,
        @StringRes val error: Int = R.string.address_error
    ) : CompanyParamState(text, isAddressError, error)
}