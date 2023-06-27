package root.presentation.compose.model

data class RouteStorageModel(
    val address: String,
    val id: Int,
    val name: String,
    val weekWorkDays: List<String>,
    val dayOffs: List<String>
)