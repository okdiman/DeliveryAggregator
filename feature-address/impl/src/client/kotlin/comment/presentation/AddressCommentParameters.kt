package comment.presentation

import androidx.compose.runtime.Immutable
import presentation.AddressSuggestUiModel

@Immutable
class AddressCommentParameters(
    val uiModel: AddressSuggestUiModel,
    val onSuggestClick: (AddressSuggestUiModel) -> Unit
)