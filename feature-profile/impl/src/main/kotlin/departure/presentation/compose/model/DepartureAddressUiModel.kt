package departure.presentation.compose.model

data class DepartureAddressUiModel(
    val id: String,
    val address: String,
    val comment: String,
    val isSelected: Boolean
)