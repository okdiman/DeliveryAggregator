package utils.resource.data

import android.content.Context
import utils.resource.domain.ResourceRepository

class ResourceRepositoryImpl(
    private val context: Context
) : ResourceRepository {

    override fun getStringFromRawResource(resId: Int) = context.resources.openRawResource(resId).use {
        it.reader().readText()
    }

    override fun getString(resId: Int) = context.resources.getString(resId)

    override fun getString(resId: Int, vararg args: Any) = context.resources.getString(resId, *args)
}