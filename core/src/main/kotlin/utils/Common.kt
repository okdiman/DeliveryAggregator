package utils

import android.content.Context
import android.content.Intent
import android.os.Build
import android.provider.Settings
import android.util.Patterns
import utils.CommonConstants.APP_PACKAGE
import utils.CommonConstants.APP_UID

fun isTextFieldFilled(newText: String, minChar: Int) = newText.length >= minChar

fun isEmailCorrect(email: String) = Patterns.EMAIL_ADDRESS.matcher(email).matches()

fun openNotificationSettings(context: Context) {
    val intent = Intent().apply {
        action = Settings.ACTION_APP_NOTIFICATION_SETTINGS
        when {
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.O -> {
                putExtra(Settings.EXTRA_APP_PACKAGE, context.packageName)
            }
            else -> {
                putExtra(APP_PACKAGE, context.packageName)
                putExtra(APP_UID, context.applicationInfo.uid)
            }
        }
    }
    context.startActivity(intent)
}