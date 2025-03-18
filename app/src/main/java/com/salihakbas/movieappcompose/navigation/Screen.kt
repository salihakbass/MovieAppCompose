package com.salihakbas.movieappcompose.navigation

import kotlinx.serialization.Serializable

sealed interface Screen {
    @Serializable
    data object Splash : Screen

    @Serializable
    data object Onboarding : Screen

    @Serializable
    data object SignIn : Screen

    @Serializable
    data object SignUp : Screen

    @Serializable
    data object Home : Screen

    @Serializable
    data object Detail : Screen

    @Serializable
    data object Trailer : Screen

    @Serializable
    data object Notification : Screen

    @Serializable
    data object Explore : Screen

    @Serializable
    data object Search : Screen

    @Serializable
    data object Favorite : Screen

    @Serializable
    data object Profile : Screen

    @Serializable
    data object Subscribe : Screen

    @Serializable
    data object Payment : Screen

    companion object {
        fun getRoute(screen: Screen): String = screen::class.qualifiedName.orEmpty()

        fun showBottomBar(currentRoute: String?): Boolean {
            return when (currentRoute) {
                getRoute(Home), getRoute(Profile), getRoute(Explore), getRoute(Favorite) -> true
                else -> false
            }
        }
    }
}