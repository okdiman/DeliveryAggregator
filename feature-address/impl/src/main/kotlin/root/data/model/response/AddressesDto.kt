package root.data.model.response

import com.google.gson.annotations.SerializedName
import root.data.model.AddressDto

class AddressesDto(
    @SerializedName("Addresses")
    val addresses: List<AddressDto>?
)