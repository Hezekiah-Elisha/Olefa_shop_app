package com.hub.olefashopapp.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.hub.olefashopapp.navigation.BottomNav
import com.hub.olefashopapp.screens.cart.CartScreen
import com.hub.olefashopapp.screens.home.HomeScreen
import com.hub.olefashopapp.screens.home.HomeViewModel
import com.hub.olefashopapp.screens.order.OrderScreen
import com.hub.olefashopapp.screens.profile.ProfileScreen
import com.hub.olefashopapp.screens.settings.SettingsScreen
import com.hub.olefashopapp.ui.theme.manrope
import com.hub.olefashopapp.utils.Constants

@Composable
fun CustomScaffold(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        bottomBar = {
            BottomNavigation(navController = navController)
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
        ) {
            NavHost(navController = navController, startDestination = BottomNav.Home.route) {
                composable(BottomNav.Cart.route) {
                    CartScreen()
                }
                composable(BottomNav.Home.route) {
                    val viewModel = hiltViewModel<HomeViewModel>()
                     HomeScreen(viewModel = viewModel)
                }
                composable(BottomNav.Profile.route) {
                    ProfileScreen()
                }
                composable(BottomNav.Settings.route) {
                    SettingsScreen()
                }
                composable(BottomNav.Order.route) {
                    OrderScreen()
                }
            }
        }
    }
}

@Composable
fun BottomNavigation(navController: NavController) {
    val items = Constants.NAV_ITEMS
    var selectedItemIndex by rememberSaveable {
        mutableStateOf("home")
    }

    NavigationBar {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        items.forEachIndexed { _, navItems ->
            NavigationBarItem(
                selected = currentRoute == navItems.route,
                onClick = {
                    selectedItemIndex = navItems.route
                    navController.navigate(navItems.route) {
                        navController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route) {
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
                    Text(
                        text = navItems.title,
                        fontFamily = manrope,
                        fontWeight = if (currentRoute == navItems.route) FontWeight.Bold else FontWeight.Normal
                    )
                }
            )
        }
    }
}


