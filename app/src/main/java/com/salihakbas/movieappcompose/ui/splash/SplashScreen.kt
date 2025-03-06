package com.salihakbas.movieappcompose.ui.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.salihakbas.movieappcompose.R
import com.salihakbas.movieappcompose.navigation.Screen
import com.salihakbas.movieappcompose.ui.components.EmptyScreen
import com.salihakbas.movieappcompose.ui.components.LoadingBar
import com.salihakbas.movieappcompose.ui.splash.SplashContract.UiAction
import com.salihakbas.movieappcompose.ui.splash.SplashContract.UiEffect
import com.salihakbas.movieappcompose.ui.splash.SplashContract.UiState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

@Composable
fun SplashScreen(
    uiState: UiState,
    uiEffect: Flow<UiEffect>,
    onAction: (UiAction) -> Unit,
    navController: NavController
) {
    when {
        uiState.isLoading -> LoadingBar()
        uiState.list.isNotEmpty() -> EmptyScreen()
        else -> SplashContent(
            navController = navController
        )
    }
}

@Composable
fun SplashContent(
    navController: NavController,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(R.color.main_blue_bg)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var progress by remember { mutableFloatStateOf(0f) }

        LaunchedEffect(Unit) {
            while (progress < 1f) {
                delay(100)
                progress += 0.05f
            }
            navController.navigate(Screen.getRoute(Screen.Home))
        }
        Text(
            text = stringResource(R.string.movie_text_splash),
            fontWeight = FontWeight.Bold,
            fontSize = 70.sp,
            color = colorResource(R.color.main_orange)
        )

        Text(
            text = stringResource(R.string.streaming_text_splash),
            fontWeight = FontWeight.Normal,
            fontSize = 50.sp,
            color = colorResource(R.color.white)
        )

        Icon(
            painter = painterResource(R.drawable.baseline_movie_24),
            contentDescription = null,
            tint = colorResource(R.color.main_orange),
            modifier = Modifier.size(200.dp)
        )

        Text(
            text = stringResource(R.string.loading_text_splash),
            fontSize = 20.sp,
            color = Color.White
        )
        Spacer(modifier = Modifier.height(10.dp))
        LinearProgressIndicator(
            progress = { progress },
            color = colorResource(R.color.main_orange),
            trackColor = colorResource(R.color.white),
            modifier = Modifier.height(10.dp)
        )
    }


}

@Preview(showBackground = true)
@Composable
fun SplashScreenPreview(
    @PreviewParameter(SplashScreenPreviewProvider::class) uiState: UiState,
) {
    SplashScreen(
        uiState = uiState,
        uiEffect = emptyFlow(),
        onAction = {},
        navController = rememberNavController()
    )
}