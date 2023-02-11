package domain

import android.net.Uri

class LoadImageUseCaseImpl(
    private val repository: ImageUploadingRepository
) : LoadImageUseCase {
    override suspend fun invoke(uri: Uri) = repository.loadImage(uri)
}