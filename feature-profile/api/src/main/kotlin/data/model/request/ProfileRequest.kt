package data.model.request

import kotlinx.serialization.Serializable

@Serializable
data class ProfileRequest(
    val email: String,
    val name: String,
    val surname: String,
    val secondName: String,
    val inn: String,
    val kpp: String,
    val ogrn: String,
    val bik: String,
    val legalAddress: String,
    val actualAddress: String,
    val checkingAccount: String,
    val correspondentAccount: String,
    val bank: String,
    val organisationName: String,
    val carPlate: String,
    val carModel: String,
    val carCategory: String,
    val carCapacity: Int,
    val carLoadCapacity: Int
)