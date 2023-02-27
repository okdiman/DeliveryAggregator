package neworder.storage.presentation.viewmodel.model

import root.presentation.compose.model.RouteStorageModel

sealed interface NewOrderStorageAction {
    data class UpdateNewOrderScreen(val storage: RouteStorageModel) : NewOrderStorageAction
    object OpenPreviousScreen : NewOrderStorageAction
}