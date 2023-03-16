package clipboard.domain

interface ClipboardRepository {
    fun setText(label: CharSequence, text: CharSequence)
}