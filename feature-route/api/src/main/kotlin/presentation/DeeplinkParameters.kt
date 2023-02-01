package presentation

import android.net.Uri
import androidx.compose.runtime.Immutable

/**
 * класс, отвечающий за передачу параметров диплинка, по которому был совершен переход в приложение
 */
@Immutable
class DeeplinkParameters(
    val uri: Uri?
)