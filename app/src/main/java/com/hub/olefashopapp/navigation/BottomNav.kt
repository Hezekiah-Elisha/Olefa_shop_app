package com.hub.olefashopapp.navigation


sealed class BottomNav(val route: String, val title: String) {
    object Home : BottomNav("home", "Home")
    object Settings : BottomNav("settings", "Settings")
    object Cart : BottomNav("cart", "Cart")
    object Profile : BottomNav("profile", "Profile")
    object Order : BottomNav("order", "Order")
}