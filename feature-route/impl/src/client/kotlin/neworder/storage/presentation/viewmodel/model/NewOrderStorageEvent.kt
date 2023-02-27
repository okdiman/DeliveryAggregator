package neworder.storage.presentation.viewmodel.model

import root.presentation.compose.model.RouteStorageModel

sealed interface NewOrderStorageEvent {
    data class OnStorageClick(val storage: RouteStorageModel) : NewOrderStorageEvent
    object OnBackClick : NewOrderStorageEvent
    object OnRetryClick : NewOrderStorageEvent
    object ResetAction : NewOrderStorageEvent
}