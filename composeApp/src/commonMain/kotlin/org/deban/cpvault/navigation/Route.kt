package org.deban.cpvault.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable


@Serializable
sealed interface Route: NavKey {

    @Serializable
    data object LandingScreen: Route, NavKey

    @Serializable
    data class HomeScreen(val username: String): Route, NavKey

}