package org.deban.cpvault.login.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import cpvault.composeapp.generated.resources.Res
import cpvault.composeapp.generated.resources.ic_app_logo_playstore
import org.deban.cpvault.LightBeige
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun landingScreen(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(LightBeige),
        contentAlignment = Alignment.Center,
    ) {
        Image(
            painter = painterResource(resource = Res.drawable.ic_app_logo_playstore),
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.Center)
                .fillMaxSize(0.8f)
                .blur(10.dp)
                .graphicsLayer{ alpha=0.25f }
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp)
        ) {  }
    }
}

@Preview
@Composable
fun previewLandingScreen() {
    landingScreen()
}