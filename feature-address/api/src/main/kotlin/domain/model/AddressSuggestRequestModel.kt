package domain.model

data class AddressSuggestRequestModel(
    val query: String,
    val code: Int,
    val phone: String
)