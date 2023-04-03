package orderdetails.root.domain.model.details

data class OrderDetailsContractorModel(
    val phone: String,
    val email: String,
    val surname: String,
    val name: String,
    val secondName: String,
    val carPlate: String,
    val carModel: String?,
)
