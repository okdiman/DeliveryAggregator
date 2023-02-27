package view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.saveable.rememberSaveableStateHolder
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import modifiers.advancedShadow
import ru.alexgladkov.odyssey.compose.base.AnimatedHost
import ru.alexgladkov.odyssey.compose.controllers.MultiStackRootController
import ru.alexgladkov.odyssey.compose.controllers.TabNavigationModel
import ru.alexgladkov.odyssey.compose.extensions.present
import ru.alexgladkov.odyssey.compose.local.LocalRootController
import ru.alexgladkov.odyssey.core.toScreenBundle
import theme.Theme

@Suppress("LongMethod")
@Composable
fun CustomBottomBarNavigator(startScreen: String?, alternativeScreen: String? = null) {
    val rootController = LocalRootController.current as MultiStackRootController
    val tabItem = rootController.stackChangeObserver.collectAsState().value ?: return

    Column(modifier = Modifier.fillMaxSize()) {
        CustomTabNavigator(
            modifier = Modifier.weight(1f),
            startScreen,
            tabItem,
            alternativeScreen
        )
        BottomNavigation(
            modifier = Modifier
                .defaultMinSize(minHeight = 70.dp)
                .advancedShadow(
                    alpha = 0.2f,
                    cornersRadius = 20.dp,
                    shadowBlurRadius = 20.dp
                )
                .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)),
            backgroundColor = Color.White
        ) {
            rootController.tabItems.forEachIndexed { index, currentItem ->
                val configuration = currentItem.tabInfo.tabItem.configuration
                val position = rootController.tabItems.indexOf(currentItem)
                val isSelected = tabItem == currentItem
                val padding = when (index) {
                    0 -> PaddingValues(bottom = 10.dp, start = 40.dp)
                    1 -> PaddingValues(bottom = 10.dp)
                    2 -> PaddingValues(bottom = 10.dp, end = 40.dp)
                    else -> PaddingValues()
                }
                BottomNavigationItem(
                    modifier = Modifier.padding(padding),
                    selected = isSelected,
                    selectedContentColor = configuration.selectedColor
                        ?: Theme.colors.textPrimaryColor,
                    unselectedContentColor = configuration.unselectedColor
                        ?: Theme.colors.textPrimaryColor.copy(alpha = 0.3f),
                    icon = {
                        if (isSelected) {
                            configuration.selectedIcon?.let {
                                Icon(
                                    painter = it,
                                    contentDescription = configuration.title
                                )
                            }
                        } else {
                            configuration.unselectedIcon?.let {
                                Icon(
                                    painter = it,
                                    contentDescription = configuration.title
                                )
                            }
                        }

                    },
                    label = {
                        Text(
                            text = configuration.title,
                            style = configuration.titleStyle ?: LocalTextStyle.current
                        )
                    },
                    onClick = {
                        rootController.switchTab(position)
                    })
            }
        }
    }
    rootController.tabsNavModel.launchedEffect.invoke()
}

@Composable
private fun CustomTabNavigator(
    modifier: Modifier = Modifier,
    startScreen: String?,
    currentTab: TabNavigationModel,
    alternativeScreen: String?
) {
    val rootController = LocalRootController.current
    val configuration = currentTab.rootController.currentScreen.collectAsState()
    val saveableStateHolder = rememberSaveableStateHolder()

    saveableStateHolder.SaveableStateProvider(currentTab.tabInfo.tabItem.name) {
        Box(modifier = modifier) {
            CompositionLocalProvider(
                LocalRootController provides currentTab.rootController
            ) {
                configuration.value?.let { navConfig ->
                    AnimatedHost(
                        currentScreen = navConfig.screen.toScreenBundle(),
                        animationType = navConfig.screen.animationType,
                        screenToRemove = navConfig.screenToRemove?.toScreenBundle(),
                        isForward = navConfig.screen.isForward,
                        onScreenRemove = currentTab.rootController.onScreenRemove
                    ) { currentTab.rootController.renderScreen(it.realKey, it.params) }
                }
            }
        }
    }

    LaunchedEffect(currentTab) {
        val tabStartScreen = currentTab.rootController.getFirstScreenName()
        if (tabStartScreen != null) {
            currentTab.rootController.drawCurrentScreen(startScreen = startScreen)
        } else {
            rootController.findRootController().present(
                screen = alternativeScreen.toString()
            )
        }
    }
}