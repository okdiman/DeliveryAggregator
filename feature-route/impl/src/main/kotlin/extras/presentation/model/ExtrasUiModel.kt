package extras.presentation.model

data class ExtrasUiModel(
    val id: Long,
    val text: String,
    val isActive: Boolean,
    val count: Int
) {
    companion object {
        val Default = ExtrasUiModel(id = Long.MAX_VALUE, isActive = false, text = "Не требуются", count = 1)
    }
}