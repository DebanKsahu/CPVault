package org.deban.cpvault

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import org.deban.cpvault.landingScreen.ui.LandingScreen
import org.deban.cpvault.navigation.NavigationRoot
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme {
        NavigationRoot()
    }
}