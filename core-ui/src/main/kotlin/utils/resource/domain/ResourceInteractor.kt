package utils.resource.domain

import androidx.annotation.RawRes

class ResourceInteractor(
    private val resourceRepository: ResourceRepository
) {

    fun getStringFromRawResource(@RawRes resId: Int): String {
        return resourceRepository.getStringFromRawResource(resId)
    }
}