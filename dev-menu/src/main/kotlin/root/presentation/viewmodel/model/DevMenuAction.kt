package root.presentation.viewmodel.model

sealed class DevMenuAction(open val text: String) {
    data class ShowToast(override val text: String) : DevMenuAction(text)
}