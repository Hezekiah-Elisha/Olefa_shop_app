package com.hub.olefashopapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController

@Composable
fun OlefaShopNavigation(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

//    NavHost(
//        navController = navController,
//        startDestination = OlefaShopScreens.MainScreen.name,
//    ){
//        composable(OlefaShopScreens.HomeScreen.name){
//            val viewModel = hiltViewModel<OlefaShopViewModel>()
//            HomeScreen(viewModel = viewModel)
//        }
//        composable(OlefaShopScreens.MainScreen.name){
//            CustomScaffold()
//        }
//    }
}