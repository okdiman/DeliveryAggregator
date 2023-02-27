package neworder.storage.presentation.viewmodel.model

import root.presentation.compose.model.RouteStorageModel

data class NewOrderStorageState(
    val storages: List<RouteStorageModel> = emptyList(),
    val activeStorage: String = "",
    val isError: Boolean = false,
    val isLoading: Boolean = true
)