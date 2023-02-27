package camera

import android.content.Context
import android.net.Uri
import androidx.core.content.FileProvider
import trinity_monsters.delivery_aggregator.core_ui.R
import java.io.File

class PhotoFileProvider : FileProvider(R.xml.photo_authorities) {
    companion object {
        fun getImageUri(context: Context): Uri {
            val directory = File(context.cacheDir, FILE_CHILD_DIR).apply {
                mkdirs()
            }
            val file = File.createTempFile(
                FILE_NAME_PREFIX,
                FILE_NAME_SUFFIX,
                directory
            )
            return getUriForFile(
                context,
                AUTHORITY_PATH,
                file,
            )
        }

        private const val FILE_CHILD_DIR = "images"
        private const val FILE_NAME_PREFIX = "selected_photo_"
        private const val FILE_NAME_SUFFIX = ".jpg"
        private const val AUTHORITY_PATH = "trinity_monsters.delivery_aggregator.core_ui.PhotoFileProvider"
    }
}