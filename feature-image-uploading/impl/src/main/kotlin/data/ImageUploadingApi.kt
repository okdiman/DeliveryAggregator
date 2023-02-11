package data

import data.model.ImageUploadingDto
import okhttp3.MultipartBody
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface ImageUploadingApi {
    @Multipart
    @POST("api/contractors/upload/image")
    suspend fun loadImage(
        @Part file: MultipartBody.Part
    ): ImageUploadingDto
}