package com.salihakbas.movieappcompose.navigation

import androidx.compose.foundation.background
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.salihakbas.movieappcompose.R

@Composable
fun BottomNavigation(
    navController: NavController
) {
    val bottomNavItems = listOf(
        BottomNavItem(
            title = R.string.home_text,
            icon = R.drawable.ic_home,
            route = Screen.Home
        ),
        BottomNavItem(
            title = R.string.explore_text,
            icon = R.drawable.ic_explore,
            route = Screen.Explore
        ),
        BottomNavItem(
            title = R.string.favorite_text,
            icon = R.drawable.ic_favorite,
            route = Screen.Favorite
        ),
        BottomNavItem(
            title = R.string.account_text,
            icon = R.drawable.ic_person,
            route = Screen.Profile
        )
    )

    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = currentBackStackEntry?.destination?.route

    NavigationBar(
        modifier = Modifier.background(colorResource(id = R.color.main_blue_bg)),
        containerColor = colorResource(id = R.color.main_blue_bg)
    ) {
        bottomNavItems.forEach { item ->
            val selected = currentDestination == Screen.getRoute(item.route)
            NavigationBarItem(
                selected = selected,
                onClick = {
                    navController.navigate(Screen.getRoute(item.route)) {
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = {
                    Icon(
                        painter = painterResource(id = item.icon),
                        contentDescription = stringResource(id = item.title),
                        tint = if (selected) colorResource(id = R.color.main_orange) else Color.White
                    )
                },
                label = {
                    Text(
                        text = stringResource(id = item.title),
                        color = if (selected) colorResource(id = R.color.main_orange) else Color.White,
                        fontWeight = if (selected) FontWeight.Bold else FontWeight.Normal
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = colorResource(id = R.color.main_orange),
                    selectedTextColor = colorResource(id = R.color.main_orange),
                    unselectedIconColor = Color.White,
                    unselectedTextColor = Color.White
                )
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BottomBarPreview() {
    BottomNavigation(navController = rememberNavController())
}