package com.hub.olefashopapp.components

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.hub.olefashopapp.navigation.OlefaShopScreens
import com.hub.olefashopapp.utils.Constants

@Composable
fun BottomNavigation(
    modifier: Modifier = Modifier,
    navController: NavController
) {
    val items = Constants.NAV_ITEMS

    var selectedItemIndex by rememberSaveable {
        mutableStateOf(OlefaShopScreens.HomeScreen.name)
    }

    NavigationBar {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        items.forEachIndexed { _, navItems ->
            NavigationBarItem(
                selected = currentRoute == navItems.route,
                onClick = {
                    selectedItemIndex = navItems.route
                    navController.navigate(navItems.route){
                        navController.graph.startDestinationRoute?.let {route->
                            popUpTo(route){
                                saveState = true
                            }
                            selectedItemIndex = navItems.route
                        }
                        popUpTo(navController.graph.startDestinationId)
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                alwaysShowLabel = true,
                icon = {
                    (if (selectedItemIndex == navItems.route) navItems.selectedIcon else navItems.unselectedIcon)?.let {
                        Icon(
                            painter = painterResource(id = it),
                            contentDescription = navItems.title
                        )
                    }
                },
                label = {
                    Text(text = navItems.title)
                }
            )
        }
    }
}