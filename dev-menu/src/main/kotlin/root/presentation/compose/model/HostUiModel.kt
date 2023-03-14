package root.presentation.compose.model

data class HostUiModel(
    val baseUrl: String,
    val name: String,
    var isActive: Boolean
)