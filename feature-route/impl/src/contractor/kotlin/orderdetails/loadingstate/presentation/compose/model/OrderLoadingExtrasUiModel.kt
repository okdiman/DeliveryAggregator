package orderdetails.loadingstate.presentation.compose.model

data class OrderLoadingExtrasUiModel(
    val id: Long,
    val text: String
) {
    companion object {
        val Default = OrderLoadingExtrasUiModel(id = Long.MAX_VALUE, text = "Не требуются")
    }
}