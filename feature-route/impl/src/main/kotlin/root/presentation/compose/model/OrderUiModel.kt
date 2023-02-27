package root.presentation.compose.model

import androidx.annotation.StringRes

interface OrderUiModel {
    @get:StringRes
    val statusText: Int?
    val id: Long
    val arrivalDate: String
    val departureAddress: String
    val deliveryAddress: String
}