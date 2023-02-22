package root.presentation.compose.model

import androidx.annotation.StringRes

interface OrderUiModel {
    val id: Long

    val arrivalDate: String

    @get:StringRes
    val statusText: Int?

    val departureAddress: String

    val deliveryAddress: String
}
