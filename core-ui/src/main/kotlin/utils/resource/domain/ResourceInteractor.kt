package utils.resource.domain

import android.text.Spanned
import androidx.annotation.RawRes
import androidx.annotation.StringRes
import utils.SpannableFormatter

class ResourceInteractor(
    private val resourceRepository: ResourceRepository
) {

    fun getString(@StringRes resId: Int) = resourceRepository.getString(resId)

    fun getStringFromRawResource(@RawRes resId: Int) = resourceRepository.getStringFromRawResource(resId)

    fun getString(@StringRes resId: Int, vararg args: Any) = resourceRepository.getString(resId, *args)

    /**
     * Отличается от getString тем, что в качестве аргументов может принимать Spanned и нормально вставять в текст
     */
    fun getSpannedString(@StringRes resId: Int, vararg arguments: Any): Spanned {
        val rawString = resourceRepository.getString(resId)
        return SpannableFormatter.format(rawString, *arguments)
    }
}