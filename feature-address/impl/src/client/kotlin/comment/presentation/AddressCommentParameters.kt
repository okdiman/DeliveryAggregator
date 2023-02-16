package comment.presentation

import androidx.compose.runtime.Immutable
import presentation.model.AddressUiModel

@Immutable
class AddressCommentParameters(
    val uiModel: AddressUiModel,
    val onSuggestClick: (AddressUiModel) -> Unit
)