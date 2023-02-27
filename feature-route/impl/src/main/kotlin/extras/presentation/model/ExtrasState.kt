package extras.presentation.model

import view.model.DefaultParamState

data class ExtrasState(
    override val stateText: String = "",
    val uiModel: List<ExtrasUiModel> = emptyList(),
    val extrasActive: List<ExtrasUiModel> = emptyList()
) : DefaultParamState(stateText)