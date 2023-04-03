package trinity_monsters.delivery_aggregator.root.presentation

import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.ActivityInfo
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.CompositionContext
import androidx.compose.runtime.rememberCompositionContext
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import generateGraph
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import presentation.DeeplinkParameters
import ru.alexgladkov.odyssey.compose.setup.OdysseyConfiguration
import ru.alexgladkov.odyssey.compose.setup.StartScreen
import ru.alexgladkov.odyssey.compose.setup.setNavigationContent
import theme.DeliveryAggregatorTheme

@SuppressLint("SourceLockedOrientationActivity")
class MainActivity : ComponentActivity() {
    private val viewModel by viewModel<MainViewModel>()
    private var currentCompositionContext: CompositionContext? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setUi()
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        /**
         * к сожалению, на момент написания приложения (02.2023) библиотека навигации Одиссей не умеет в диплинки.
         * приходится придумывать что то для этого
         */
        lifecycleScope.launch {
            viewModel.getStartDeeplinkDestination(intent)?.let { startDeeplinkDestination ->
                navigate(StartScreen.Custom(startDeeplinkDestination), intent?.data)
            }
        }
    }

    private fun setUi() {
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        navigate(StartScreen.First, intent.data)
    }

    private fun navigate(startScreen: StartScreen, uri: Uri?) {
        setContent(currentCompositionContext) {
            currentCompositionContext = rememberCompositionContext()
            DeliveryAggregatorTheme {
                val configuration = OdysseyConfiguration(
                    canvas = this@MainActivity,
                    startScreen = startScreen
                )
                setNavigationContent(
                    configuration = configuration,
                    onApplicationFinish = {
                        finish()
                    }) {
                    generateGraph(DeeplinkParameters(uri))
                }
            }
        }
    }
}