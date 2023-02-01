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
    fun getDestination(uri: Uri?) = uri?.getSafeQueryParameter(DESTINATION)
}