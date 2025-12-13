package org.deban.cpvault.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import androidx.savedstate.serialization.SavedStateConfiguration
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import org.deban.cpvault.homeScreen.ui.HomeScreen
import kotlin.collections.listOf
import org.deban.cpvault.landingScreen.ui.LandingScreen

@Composable
fun NavigationRoot(
    modifier: Modifier = Modifier
) {
    val backStack = rememberNavBackStack(
        configuration = SavedStateConfiguration {
            serializersModule = SerializersModule {
                polymorphic(NavKey::class) {
                    subclass(Route.LandingScreen::class, Route.LandingScreen.serializer())
                    subclass(Route.HomeScreen::class, Route.HomeScreen.serializer())
                }
            }
        },
        Route.LandingScreen
    )

    NavDisplay(
        modifier = modifier,
        backStack = backStack,
        entryDecorators = listOf(
            rememberSaveableStateHolderNavEntryDecorator(),
            rememberViewModelStoreNavEntryDecorator()
        ),
        entryProvider = {navKey ->
            when(navKey) {
                is Route.LandingScreen -> {
                    NavEntry(navKey) {
                        LandingScreen(
                            onClick = {username ->
                                backStack.add(Route.HomeScreen(username = username))
                            }
                        )
                    }
                }
                is Route.HomeScreen -> {
                    NavEntry(navKey) {
                        HomeScreen(
                            username = navKey.username
                        )
                    }
                }
                else -> error("Unknown NavKey: $navKey")
            }

        }

    )
}