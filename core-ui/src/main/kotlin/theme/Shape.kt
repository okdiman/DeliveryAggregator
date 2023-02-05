package theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp

data class DeliveryAggregatorShapes(
    val small: Shape,
    val textFields: Shape,
    val photo: Shape,
    val card: Shape,
    val bigCard: Shape,
    val roundedButton: Shape
)

val shapes = DeliveryAggregatorShapes(
    small = RoundedCornerShape(5.dp),
    photo = RoundedCornerShape(14.dp),
    textFields = RoundedCornerShape(8.dp),
    card = RoundedCornerShape(16.dp),
    bigCard = RoundedCornerShape(20.dp),
    roundedButton = RoundedCornerShape(40.dp)
)

val LocalShapeProvider =
    staticCompositionLocalOf<DeliveryAggregatorShapes> { error("No default implementation") }