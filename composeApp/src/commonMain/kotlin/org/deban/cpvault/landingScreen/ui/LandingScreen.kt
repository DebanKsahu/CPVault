package org.deban.cpvault.landingScreen.ui

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import co.touchlab.kermit.Logger
import cpvault.composeapp.generated.resources.Res
import cpvault.composeapp.generated.resources.ic_app_logo_trasperent_foreground
import org.deban.cpvault.LightBeige
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun LandingScreen(
    modifier: Modifier = Modifier,
    onClick: (String) -> Unit
) {
    val viewModel = koinViewModel<LandingScreenViewModel>()
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()
    val username = rememberSaveable { mutableStateOf("") }

    LandingScreenContent(
        modifier = modifier,
        uiState = uiState.value,
        username = username,
        onUsernameChange = {newUsername ->
            username.value = newUsername
            viewModel.updateLeetCodeUsername(username = username.value)
        },
        onClick = onClick
    )


}

@Composable
fun LandingScreenContent(
    modifier: Modifier = Modifier,
    uiState: LandingScreen.UiState,
    username: MutableState<String>,
    onUsernameChange: (String) -> Unit,
    onClick: (String) -> Unit
) {

    var targetButtonColor by remember { mutableStateOf(Color.Red) }

    val animatedButtonColor by animateColorAsState(
        targetValue = targetButtonColor,
        animationSpec = tween(600)
    )

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(LightBeige),
        contentAlignment = Alignment.Center,
    ) {

        Image(
            painter = painterResource(resource = Res.drawable.ic_app_logo_trasperent_foreground),
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.Center)
                .fillMaxSize(0.7f)
                .blur(10.dp)
                .graphicsLayer{ alpha=0.25f }
                .scale(1.5f)
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {

            CustomRoundedInputField(
                value = username.value,
                placeholder = "Leetcode UserName",
                onValueChange = onUsernameChange
            )
            if (uiState.isLoading) {
                Text(
                    text = "Loading",
                    style = TextStyle(
                        color = Color.Gray,
                        fontSize = 14.sp,
                        textAlign = TextAlign.Center
                    )
                )
            } else if (uiState.isUsernameAvailable!=null && uiState.isUsernameAvailable) {
                Text(
                    text = "Found",
                    style = TextStyle(
                        color = Color.Gray,
                        fontSize = 14.sp,
                        textAlign = TextAlign.Center
                    )
                )
            } else if (uiState.isUsernameAvailable!=null && !uiState.isUsernameAvailable) {
                Text(
                    text = "Not Found",
                    style = TextStyle(
                        color = Color.Gray,
                        fontSize = 14.sp,
                        textAlign = TextAlign.Center
                    )
                )
            } else if (!uiState.isLoading && uiState.error.isNotBlank()) {
                Text(
                    text = "Some Error Occured",
                    style = TextStyle(
                        color = Color.Gray,
                        fontSize = 14.sp,
                        textAlign = TextAlign.Center
                    )
                )
            }


            Button(
                modifier = Modifier
                    .width(120.dp)
                    .height(45.dp),
                colors = ButtonDefaults.buttonColors(containerColor = animatedButtonColor),
                onClick = { onClick(username.value) },
                content = {
                    Text(text = "Submit")
                },
            )
        }
    }
}

@Composable
fun CustomRoundedInputField(
    value: String,
    placeholder: String,
    onValueChange: (String) -> Unit
) {
    val shape = RoundedCornerShape(50)
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(6.dp, shape)
            .background(Color.White, shape)
            .height(50.dp),
        contentAlignment = Alignment.CenterStart,
    ) {
        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            singleLine = true,
            textStyle = TextStyle(fontSize = 16.sp, color = Color.Black),
            decorationBox = { innerTextField ->
                Box(
                    contentAlignment = Alignment.CenterStart,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                ) {
                    if (value.isEmpty()) {
                        Text(
                            text = placeholder,
                            color = Color.Gray,
                            fontSize = 15.sp
                        )
                    }
                    innerTextField()
                }
            },
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Preview
@Composable
fun PreviewLandingScreen() {
    val username = rememberSaveable { mutableStateOf("") }
    LandingScreenContent(
        modifier = Modifier,
        uiState = LandingScreen.UiState(),
        username = username,
        onUsernameChange = {},
        onClick = {}
    )
}