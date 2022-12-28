package trinity_monsters.wildberries_delivery_aggregator.root

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.CompositionLocalProvider
import com.adeo.kviewmodel.odyssey.setupWithViewModels
import generateGraph
import navigation.NavigationTree
import ru.alexgladkov.odyssey.compose.base.Navigator
import ru.alexgladkov.odyssey.compose.extensions.setupWithActivity
import ru.alexgladkov.odyssey.compose.local.LocalRootController
import ru.alexgladkov.odyssey.compose.navigation.RootComposeBuilder
import ru.alexgladkov.odyssey.compose.navigation.modal_navigation.ModalNavigator
import theme.DeliveryAggregatorTheme

fun ComponentActivity.setupThemedNavigation() {
    val rootController =
        RootComposeBuilder().apply { generateGraph() }.build()
    rootController.setupWithActivity(this)
    rootController.setupWithViewModels()

    setContent {
        DeliveryAggregatorTheme {
            CompositionLocalProvider(
                LocalRootController provides rootController
            ) {
                ModalNavigator {
                    Navigator(startScreen = NavigationTree.Splash.Splash.name)
                }
            }
        }
    }
}