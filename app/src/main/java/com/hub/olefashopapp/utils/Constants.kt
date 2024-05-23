package com.hub.olefashopapp.utils

import com.hub.olefashopapp.R
import com.hub.olefashopapp.model.NavItems
import com.hub.olefashopapp.navigation.BottomNav

object Constants{
    const val BASE_URL = "https://fakestoreapi.com/"

    val categories = listOf(
        "all",
        "men",
        "women",
        "kids",
        "electronics",
        "jewelery"
    )


    val NAV_ITEMS = listOf(
        NavItems(
            route= BottomNav.Home.route,
            title = BottomNav.Home.title,
            selectedIcon = R.drawable.baseline_home_filled_24,
            unselectedIcon = R.drawable.outline_home_24
        ),
        NavItems(
            route= BottomNav.Cart.route,
            title = BottomNav.Cart.title,
            selectedIcon = R.drawable.baseline_shopping_cart_24,
            unselectedIcon = R.drawable.outline_shopping_cart_24
        ),
//        NavItems(
//            route= BottomNav.Profile.route,
//            title = BottomNav.Profile.title,
//            selectedIcon = R.drawable.baseline_person_24,
//            unselectedIcon = R.drawable.outline_person_outline_24
//        ),
        NavItems(
            route= BottomNav.Order.route,
            title = BottomNav.Order.title,
            selectedIcon = R.drawable.baseline_library_books_24,
            unselectedIcon = R.drawable.outline_library_books_24
        ),
        NavItems(
            route= BottomNav.Settings.route,
            title = BottomNav.Settings.title,
            selectedIcon = R.drawable.baseline_settings_24,
            unselectedIcon = R.drawable.outline_settings_24
        ),
    )
}