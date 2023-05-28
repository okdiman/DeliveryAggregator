package extras.presentation.model

data class ExtrasUiModel(
    val id: Long,
    val text: String,
    val count: Int
) {
    companion object {
        val Default = ExtrasUiModel(id = Long.MAX_VALUE, text = "Не требуются", 1)
    }
}