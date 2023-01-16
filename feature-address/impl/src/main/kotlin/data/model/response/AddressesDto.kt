package data.model.response

import com.google.gson.annotations.SerializedName

class AddressesDto(
    @SerializedName("Addresses")
    val addresses: List<AddressDto>
)