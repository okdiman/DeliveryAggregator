package root.presentation.model

import presentation.AddressSuggestUiModel
import view.model.DefaultParamState

data class AddressState(
    override val stateText: String = "",
    val activeId: Int? = null,
    val address: AddressSuggestUiModel? = null,
    val isSuggestLoading: Boolean = false
) : DefaultParamState(stateText)