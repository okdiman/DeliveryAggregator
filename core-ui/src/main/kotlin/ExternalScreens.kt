import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.provider.Settings
import utils.CommonConstants
import utils.UiConstants.External.EMAIL_PREFIX
import utils.UiConstants.External.PHONE_PREFIX

fun openNotificationSettings(context: Context) {
    val intent = Intent().apply {
        action = Settings.ACTION_APP_NOTIFICATION_SETTINGS
        when {
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.O -> {
                putExtra(Settings.EXTRA_APP_PACKAGE, context.packageName)
            }
            else -> {
                putExtra(CommonConstants.System.APP_PACKAGE, context.packageName)
                putExtra(CommonConstants.System.APP_UID, context.applicationInfo.uid)
            }
        }
    }
    context.startActivity(intent)
}

//FIXME Убрать хардкод номера, как только появится нормальный в ТЗ
fun openDial(context: Context) {
    val intent = Intent().apply {
        action = Intent.ACTION_DIAL
        data = Uri.parse("${PHONE_PREFIX}+79998887766")
    }
    context.startActivity(intent)
}

//FIXME Убрать хардкод email, как только появится нормальный в ТЗ
fun openEmailSender(context: Context) {
    val intent = Intent().apply {
        action = Intent.ACTION_SENDTO
        data = Uri.parse("${EMAIL_PREFIX}test@support.com")
    }
    context.startActivity(intent)
}