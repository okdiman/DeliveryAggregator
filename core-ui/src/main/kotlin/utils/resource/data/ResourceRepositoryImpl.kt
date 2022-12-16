package utils.resource.data

import android.content.Context
import utils.resource.domain.ResourceRepository

class ResourceRepositoryImpl(
    private val context: Context
) : ResourceRepository {

    override fun getStringFromRawResource(resId: Int): String {
        return context.resources.openRawResource(resId).use {
            it.reader().readText()
        }
    }

    override fun getString(resId: Int): String {
        return context.resources.getString(resId)
    }
}