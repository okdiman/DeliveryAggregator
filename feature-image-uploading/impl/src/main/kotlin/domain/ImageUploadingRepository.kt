package domain

import android.net.Uri

interface ImageUploadingRepository {
    suspend fun loadImage(uri: Uri): String
}