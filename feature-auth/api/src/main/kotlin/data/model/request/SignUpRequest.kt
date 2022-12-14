package data.model.request

import com.google.gson.annotations.SerializedName

class SignUpRequest(
    @SerializedName("code")
    val code: Int,
    @SerializedName("phone")
    val phone: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("surname")
    val surname: String,
    @SerializedName("inn")
    val inn: String,
    @SerializedName("kpp")
    val kpp: String,
    @SerializedName("ogrn")
    val ogrn: String,
    @SerializedName("bik")
    val bik: String,
    @SerializedName("legalAddress")
    val legalAddress: String,
    @SerializedName("actualAddress")
    val actualAddress: String,
    @SerializedName("checkingAccount")
    val checkingAccount: String,
    @SerializedName("correspondentAccount")
    val correspondentAccount: String,
    @SerializedName("bank")
    val bank: String,
    @SerializedName("organisationName")
    val organisationName: String,
    @SerializedName("carPlate")
    val carPlate: String,
    @SerializedName("address")
    val address: String,
    @SerializedName("carModel")
    val carModel: String,
    @SerializedName("carCategory")
    val carCategory: String,
    @SerializedName("carLoadCapacity")
    val carLoadCapacity: Int,
    @SerializedName("carPalletCapacity")
    val carPalletCapacity: Int
)