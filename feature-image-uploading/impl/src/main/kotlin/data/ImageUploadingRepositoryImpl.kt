package data

import android.content.Context
import android.net.Uri
import domain.ImageUploadingRepository
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File

class ImageUploadingRepositoryImpl(
    private val context: Context,
    private val api: ImageUploadingApi
) : ImageUploadingRepository {
    override suspend fun loadImage(uri: Uri): String {
        val file = File(context.filesDir, FILE_NAME).apply {
            outputStream().use { outputStream ->
                context.contentResolver.openInputStream(uri).use { inputStream ->
                    inputStream?.copyTo(outputStream)
                }
            }
        }
        val requestBody = file.asRequestBody(MULTIPART_MEDIA_TYPE.toMediaType())
        val multipartBody = MultipartBody.Part.createFormData(FILE_NAME, file.name, requestBody)
        return api.loadImage(multipartBody).link
    }

    private companion object {
        const val MULTIPART_MEDIA_TYPE = "multipart/form-data"
        const val FILE_NAME = "file"
    }
}