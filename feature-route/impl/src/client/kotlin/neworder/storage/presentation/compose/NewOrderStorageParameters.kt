package neworder.storage.presentation.compose

import androidx.compose.runtime.Immutable
import root.presentation.compose.model.RouteStorageModel
import view.model.DefaultParamState

@Immutable
class NewOrderStorageParameters(
    val paramState: DefaultParamState,
    val onStorageClick: (RouteStorageModel) -> Unit
)