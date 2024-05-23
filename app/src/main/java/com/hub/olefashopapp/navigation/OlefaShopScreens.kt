package com.hub.olefashopapp.navigation

enum class OlefaShopScreens {
    HomeScreen,
    ProductDetailScreen,
    CartScreen,
    ProfileScreen,
    OrderScreen,
    SettingsScreen,
    MainScreen;

    companion object{
        fun fromRoute(route: String?): OlefaShopScreens
           = when(route?.substringBefore("/")){
                HomeScreen.name -> HomeScreen
                ProductDetailScreen.name -> ProductDetailScreen
                CartScreen.name -> CartScreen
                ProfileScreen.name -> ProfileScreen
                OrderScreen.name -> OrderScreen
                SettingsScreen.name -> SettingsScreen
                null -> MainScreen
                else -> throw IllegalArgumentException("Route $route is not recognized.")
           }
    }
}