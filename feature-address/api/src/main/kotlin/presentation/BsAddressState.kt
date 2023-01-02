package presentation

import androidx.annotation.StringRes
import presentation.model.AddressUiModel
import trinity_monsters.wildberries_delivery_aggregator.core_ui.R
import view.model.DefaultParamState

data class BsAddressState(
    val text: String = "",
    val address: AddressUiModel? = null,
    val isBsAddressError: Boolean = false,
    val isSuggestLoading: Boolean = false,
    @StringRes val error: Int = R.string.incorrect_address
) : DefaultParamState(text, isBsAddressError, error)