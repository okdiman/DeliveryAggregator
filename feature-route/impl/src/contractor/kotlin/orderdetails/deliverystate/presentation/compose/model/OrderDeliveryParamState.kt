package orderdetails.deliverystate.presentation.compose.model

import view.model.DefaultParamState

sealed class OrderDeliveryParamState(
    override val stateText: String
) : DefaultParamState(stateText) {
    data class CommentState(override val stateText: String = "") : OrderDeliveryParamState(stateText)
}