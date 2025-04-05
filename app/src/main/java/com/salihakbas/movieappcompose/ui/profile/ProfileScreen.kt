package com.salihakbas.movieappcompose.ui.profile

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.salihakbas.movieappcompose.R
import com.salihakbas.movieappcompose.common.collectWithLifecycle
import com.salihakbas.movieappcompose.ui.components.EmptyScreen
import com.salihakbas.movieappcompose.ui.components.LoadingBar
import com.salihakbas.movieappcompose.ui.profile.ProfileContract.UiAction
import com.salihakbas.movieappcompose.ui.profile.ProfileContract.UiEffect
import com.salihakbas.movieappcompose.ui.profile.ProfileContract.UiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

@Composable
fun ProfileScreen(
    uiState: UiState,
    uiEffect: Flow<UiEffect>,
    onAction: (UiAction) -> Unit,
    navigateToSubscribe: () -> Unit,
    navigateToEditProfile: () -> Unit,
    navigateToSignIn: () -> Unit
) {
    uiEffect.collectWithLifecycle {
        when (it) {
            UiEffect.NavigateToSubscribe -> {
                navigateToSubscribe()
            }

            UiEffect.NavigateToEditProfile -> {
                navigateToEditProfile()
            }

            UiEffect.NavigateToSignIn -> {
                navigateToSignIn()
            }
        }
    }
    when {
        uiState.isLoading -> LoadingBar()
        uiState.list.isNotEmpty() -> EmptyScreen()
        else -> ProfileContent(
            navigateToSubscribe,
            navigateToEditProfile,
            uiState,
            onAction
        )
    }
}

@Composable
fun ProfileContent(
    navigateToSubscribe: () -> Unit,
    navigateToEditProfile: () -> Unit,
    uiState: UiState,
    onAction: (UiAction) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(R.color.main_blue_bg)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .padding(16.dp)
                .background(
                    color = colorResource(R.color.light_blue),
                    shape = RoundedCornerShape(16.dp)
                )
                .fillMaxWidth()
                .height(300.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(
                    text = uiState.name + uiState.surname,
                    fontSize = 24.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    modifier = Modifier
                        .clip(shape = RoundedCornerShape(8.dp))
                        .background(color = colorResource(R.color.main_orange))
                        .padding(4.dp),
                    text = "Premium Account",
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
                ProfileStats()
                OutlinedButton(
                    onClick = { navigateToEditProfile() },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    border = BorderStroke(color = colorResource(R.color.main_orange), width = 1.dp)
                ) {
                    Text(
                        text = "Edit Profile",
                        color = colorResource(R.color.main_orange),
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
        ProfileButtons(
            painter = painterResource(R.drawable.ic_subscribe),
            text = "Subscribe",
            navigate = navigateToSubscribe
        )
        Spacer(modifier = Modifier.height(8.dp))
        HorizontalDivider(
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        ProfileButtons(
            painter = painterResource(R.drawable.ic_settings),
            text = "App Settings",
            navigate = {}
        )
        Spacer(modifier = Modifier.height(8.dp))
        HorizontalDivider(
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        ProfileButtons(
            painter = painterResource(R.drawable.ic_info),
            text = "About",
            navigate = {}
        )
        Spacer(modifier = Modifier.height(8.dp))
        HorizontalDivider(
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        ProfileButtons(
            painter = painterResource(R.drawable.ic_signout),
            text = "Sign Out",
            navigate = { onAction(UiAction.SignOutClicked) }
        )
        Spacer(modifier = Modifier.height(8.dp))
        HorizontalDivider(
            modifier = Modifier.padding(horizontal = 16.dp)
        )
    }
}

@Composable
fun ProfileButtons(
    painter: Painter,
    text: String,
    navigate: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable {
                navigate()
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painter,
            contentDescription = null,
            tint = Color.White
        )
        Spacer(modifier = Modifier.width(32.dp))
        Text(
            text = text,
            color = Color.White,
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold
        )
        Spacer(modifier = Modifier.weight(1f))
        Icon(
            painter = painterResource(R.drawable.ic_arrow_right),
            contentDescription = null,
            tint = Color.White
        )
    }
}

@Composable
fun ProfileStats() {
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = "Total hours watched",
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
            Text(
                buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            fontSize = 24.sp,
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )
                    ) {
                        append("20")
                    }
                    withStyle(
                        style = SpanStyle(
                            fontSize = 16.sp,
                            color = Color.White
                        )
                    ) {
                        append(" Hours")
                    }
                }
            )
        }
        VerticalDivider(
            modifier = Modifier
                .height(60.dp)
        )
        Column(
            modifier = Modifier.padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = "Total film watched",
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
            Text(
                buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            fontSize = 24.sp,
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )
                    ) {
                        append("50")
                    }
                    withStyle(
                        style = SpanStyle(
                            fontSize = 16.sp,
                            color = Color.White
                        )
                    ) {
                        append(" Film")
                    }
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview(
    @PreviewParameter(ProfileScreenPreviewProvider::class) uiState: UiState,
) {
    ProfileScreen(
        uiState = uiState,
        uiEffect = emptyFlow(),
        onAction = {},
        navigateToSubscribe = {},
        navigateToEditProfile = {},
        navigateToSignIn = {}
    )
}