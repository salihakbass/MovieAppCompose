package com.salihakbas.movieappcompose.ui.onboarding

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.salihakbas.movieappcompose.R
import com.salihakbas.movieappcompose.ui.components.EmptyScreen
import com.salihakbas.movieappcompose.ui.components.LoadingBar
import com.salihakbas.movieappcompose.ui.onboarding.OnboardingContract.UiAction
import com.salihakbas.movieappcompose.ui.onboarding.OnboardingContract.UiEffect
import com.salihakbas.movieappcompose.ui.onboarding.OnboardingContract.UiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

@Composable
fun OnboardingScreen(
    uiState: UiState,
    uiEffect: Flow<UiEffect>,
    onAction: (UiAction) -> Unit,
    navigateToHome: () -> Unit
) {
    when {
        uiState.isLoading -> LoadingBar()
        uiState.list.isNotEmpty() -> EmptyScreen()
        else -> OnboardingContent(
            navigateToHome = navigateToHome
        )
    }
}

@Composable
fun OnboardingContent(
    navigateToHome : () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(R.color.main_blue_bg)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Spacer(modifier = Modifier.height(36.dp))
        Text(
            text = stringResource(R.string.enjoy_our_premium_features_text),
            color = Color.White,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
                .clip(RoundedCornerShape(24.dp))
                .background(color = colorResource(R.color.light_blue_bg)),
            horizontalAlignment = Alignment.CenterHorizontally

        ) {

            AnnotatedText(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                coloredText = stringResource(R.string.premium_features_title_1),
                normalText = stringResource(R.string.premium_features_text_1)
            )
            AnnotatedText(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                coloredText = stringResource(R.string.premium_features_title_2),
                normalText = stringResource(R.string.premium_features_text_2)
            )
            AnnotatedText(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                coloredText = stringResource(R.string.premium_features_title_3),
                normalText = stringResource(R.string.premium_features_text_3)
            )
            AnnotatedText(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                coloredText = stringResource(R.string.premium_features_title_4),
                normalText = stringResource(R.string.premium_features_text_4)
            )
            AnnotatedText(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                coloredText = stringResource(R.string.premium_features_title_5),
                normalText = stringResource(R.string.premium_features_text_5)
            )
        }

        Button(
            onClick = {},
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonColors(
                containerColor = colorResource(R.color.main_orange),
                contentColor = Color.White,
                disabledContentColor = Color.White,
                disabledContainerColor = Color.Gray
            )
        ) {
            Text(
                text = "🚀 Start Your Free 7-Day Trial Now",
                color = Color.Black,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }

        OutlinedButton(
            onClick = {},
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            shape = RoundedCornerShape(12.dp),
            border = BorderStroke(1.dp, colorResource(R.color.main_orange))

        ) {
            Text(
                text = "Book and Plan",
                color = colorResource(R.color.main_orange),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Text(
            text = "Skip Now",
            color = colorResource(R.color.main_orange),
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .clickable {
                    navigateToHome()
                }
        )
    }
}

@Composable
fun AnnotatedText(
    modifier: Modifier = Modifier,
    coloredText: String,
    normalText: String
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(R.drawable.ic_dot),
            contentDescription = null,
            tint = colorResource(R.color.main_orange),
            modifier = Modifier
                .size(16.dp)
                .padding(end = 8.dp)
                .align(Alignment.Top)
        )
        Text(
            text = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        color = colorResource(R.color.main_orange),
                        fontSize = 18.sp
                    )
                ) {
                    append(coloredText)
                }
                withStyle(
                    style = SpanStyle(
                        color = Color.White,
                        fontSize = 18.sp
                    )
                ) {
                    append(normalText)
                }
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun OnboardingScreenPreview(
    @PreviewParameter(OnboardingScreenPreviewProvider::class) uiState: UiState,
) {
    OnboardingScreen(
        uiState = uiState,
        uiEffect = emptyFlow(),
        onAction = {},
        navigateToHome = {}
    )
}