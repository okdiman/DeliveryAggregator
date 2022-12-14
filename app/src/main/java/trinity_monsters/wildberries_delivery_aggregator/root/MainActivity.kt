package trinity_monsters.wildberries_delivery_aggregator.root

import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import setupThemedNavigation

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        setupThemedNavigation()
    }
}