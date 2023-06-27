package utils.resource.domain

import androidx.annotation.RawRes
import androidx.annotation.StringRes

interface ResourceRepository {
    fun getStringFromRawResource(@RawRes resId: Int): String
    fun getString(@StringRes resId: Int): String
    fun getString(resId: Int, vararg args: Any): String
}