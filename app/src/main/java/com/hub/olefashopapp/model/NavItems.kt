package com.hub.olefashopapp.model

import androidx.compose.ui.graphics.vector.ImageVector

data class NavItems(
    val title: String,
    val route: String,
    val selectedIcon: Int,
    val unselectedIcon: Int
)