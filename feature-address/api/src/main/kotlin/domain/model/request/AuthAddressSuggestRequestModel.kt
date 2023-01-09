package domain.model.request

data class AuthAddressSuggestRequestModel(
    val query: String,
    val code: Int,
    val phone: String
)