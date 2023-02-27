package extras.presentation.model

data class ExtrasUiModel(
    val id: Long,
    val text: String
) {
    companion object {
        val Default = ExtrasUiModel(id = Long.MAX_VALUE, text = "Не требуются")
    }
}