package presentation.model

import view.model.DefaultParamState

data class AddressState(
    override val stateText: String = "",
    val address: AddressUiModel? = null,
    val isSuggestLoading: Boolean = false
) : DefaultParamState(stateText)