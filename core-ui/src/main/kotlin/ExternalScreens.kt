import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.provider.Settings
import androidx.browser.customtabs.CustomTabColorSchemeParams
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.content.ContextCompat
import trinity_monsters.delivery_aggregator.core_ui.R
import utils.CommonConstants
import utils.UiConstants.External.EMAIL_PREFIX
import utils.UiConstants.External.PHONE_PREFIX
import utils.ext.getBitmapFromVector

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

fun openApplicationSettings(context: Context) {
    val intent = Intent().apply {
        action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
        data = Uri.fromParts("package", context.packageName, null)
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

fun openBrowser(context: Context, uri: Uri) {
    val intent = getCustomTabIntent(context, uri)
    context.startActivity(intent)
}

private fun getCustomTabIntent(context: Context, uri: Uri): Intent {
    val closeIcon = ContextCompat.getDrawable(context, R.drawable.back_ic)?.getBitmapFromVector()
    val colorScheme = CustomTabColorSchemeParams.Builder()
        .setToolbarColor(android.graphics.Color.WHITE)
        .build()
    return CustomTabsIntent.Builder()
        .setShareState(CustomTabsIntent.SHARE_STATE_OFF)
        .setCloseButtonIcon(closeIcon!!)
        .setDefaultColorSchemeParams(colorScheme)
        .setShowTitle(true)
        .build()
        .intent
        .setAction(Intent.ACTION_VIEW)
        .setData(uri)
        .setBrowser(context)
}

/**
 * метод для проверки присутствия и при необходимости выбора
 * на устройстве браузера для обработки необходимого интента
 */
private fun Intent.setBrowser(context: Context): Intent {
    val packageManager = context.packageManager
    val defaultBrowser = packageManager.resolveActivity(this, PackageManager.MATCH_DEFAULT_ONLY)
    if (defaultBrowser == null) {
        val allBrowsers = packageManager.queryIntentActivities(this, PackageManager.MATCH_ALL)
        when {
            allBrowsers.any { it.activityInfo.packageName == ContextConstants.CHROME_PACKAGE } -> {
                this.setPackage(ContextConstants.CHROME_PACKAGE)
            }
            allBrowsers.isNotEmpty() -> {
                this.setPackage(allBrowsers.first().activityInfo.packageName)
            }
        }
    }
    return this
}

object ContextConstants {
    const val CHROME_PACKAGE = "com.android.chrome"
}