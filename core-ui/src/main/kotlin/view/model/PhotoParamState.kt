package view.model

import android.net.Uri

class PhotoParamState(
    val uri: Uri,
    val date: String,
    val remoteLink: String? = null,
    val isLoading: Boolean = false
)