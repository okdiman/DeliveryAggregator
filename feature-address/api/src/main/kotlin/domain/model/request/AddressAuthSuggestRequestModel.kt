package domain.model.request

data class AddressAuthSuggestRequestModel(
    val query: String,
    val code: Int,
    val phone: String
)