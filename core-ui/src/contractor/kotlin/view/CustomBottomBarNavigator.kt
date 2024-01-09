package view

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
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import modifiers.advancedShadow
import ru.alexgladkov.odyssey.compose.base.TabNavigator
import ru.alexgladkov.odyssey.compose.controllers.MultiStackRootController
import ru.alexgladkov.odyssey.compose.local.LocalRootController
import theme.Theme

@Suppress("LongMethod")
@Composable
fun CustomBottomBarNavigator(startScreen: String?) {
    val rootController = LocalRootController.current as MultiStackRootController
    val tabItem = rootController.stackChangeObserver.collectAsState().value ?: return

    Column(modifier = Modifier.fillMaxSize()) {
        TabNavigator(
            modifier = Modifier.weight(1f),
            startScreen,
            tabItem
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
                    0 -> PaddingValues(bottom = 10.dp, start = 80.dp)
                    1 -> PaddingValues(bottom = 10.dp, end = 80.dp)
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
