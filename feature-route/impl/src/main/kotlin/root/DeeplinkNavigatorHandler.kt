package root

import android.net.Uri
import notifications.NotificationsConstant.Route.DESTINATION
import utils.ext.getSafeQueryParameter

/**
 * класс, отвественный за обработку диплинков (должен отдавать на выход конечный destination для
 * навигатора или null, на данный момент получение параметров не реализовано, но, по идее, легко
 * реализуется в данной архитектуре)
 */
class DeeplinkNavigatorHandler {
    /**
     * Последний обработанный диплинк
     */
    private var latestHandledDeeplinkUri: Uri? = null

    fun getDestination(uri: Uri?): String? {
        latestHandledDeeplinkUri = uri
        return uri?.getSafeQueryParameter(DESTINATION)
    }

    /**
     * Сравнивает [uri] и [latestHandledDeeplinkUri] по ссылкам на объекты. Если это новый диплинк,
     * то для него был создан новый объект
     */
    fun isAlreadyHandled(uri: Uri) = (uri === latestHandledDeeplinkUri)
}
