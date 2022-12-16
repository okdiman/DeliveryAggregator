package utils.resource.domain

import androidx.annotation.RawRes
import androidx.annotation.StringRes

class ResourceInteractor(
    private val resourceRepository: ResourceRepository
) {

    fun getString(@StringRes resId: Int): String {
        return resourceRepository.getString(resId)
    }

    fun getStringFromRawResource(@RawRes resId: Int): String {
        return resourceRepository.getStringFromRawResource(resId)
    }
}