package utils.resource.domain

import androidx.annotation.RawRes

interface ResourceRepository {
    fun getStringFromRawResource(@RawRes resId: Int): String
}