package clipboard.data

import android.content.ClipData
import android.content.ClipboardManager
import clipboard.domain.ClipboardRepository

class ClipboardRepositoryImpl(
    private val clipboardManager: ClipboardManager
) : ClipboardRepository {

    override fun setText(label: CharSequence, text: CharSequence) {
        val clipData = ClipData.newPlainText(label, text)
        clipboardManager.setPrimaryClip(clipData)
    }
}