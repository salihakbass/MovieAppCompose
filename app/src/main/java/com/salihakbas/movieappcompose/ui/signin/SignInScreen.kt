package com.salihakbas.movieappcompose.ui.signin

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.salihakbas.movieappcompose.R
import com.salihakbas.movieappcompose.common.collectWithLifecycle
import com.salihakbas.movieappcompose.ui.components.EmptyScreen
import com.salihakbas.movieappcompose.ui.components.LoadingBar
import com.salihakbas.movieappcompose.ui.signin.SignInContract.UiAction
import com.salihakbas.movieappcompose.ui.signin.SignInContract.UiEffect
import com.salihakbas.movieappcompose.ui.signin.SignInContract.UiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

@Composable
fun SignInScreen(
    uiState: UiState,
    uiEffect: Flow<UiEffect>,
    onAction: (UiAction) -> Unit,
    navigateToSignUp: () -> Unit,
    navigateToHome: () -> Unit
) {
    uiEffect.collectWithLifecycle { effect ->
        when (effect) {
            is UiEffect.NavigateToSignUp -> navigateToSignUp()
            is UiEffect.NavigateToHome -> navigateToHome()
        }
    }
    when {
        uiState.isLoading -> LoadingBar()
        uiState.list.isNotEmpty() -> EmptyScreen()
        else -> SignInContent(
            uiState = uiState,
            onAction = onAction,
            navigateToSignUp = navigateToSignUp
        )
    }
}

@Composable
fun SignInContent(
    uiState: UiState,
    onAction: (UiAction) -> Unit,
    navigateToSignUp: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(R.color.main_blue_bg))
            .padding(vertical = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(R.drawable.streaming),
            contentDescription = null,
            modifier = Modifier.fillMaxWidth(),
            contentScale = ContentScale.FillWidth
        )
        OutlinedTextField(
            value = uiState.email,
            onValueChange = { onAction(UiAction.EmailChanged(it)) },
            label = { Text(text = stringResource(R.string.email_address_text)) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            shape = RoundedCornerShape(12.dp),
            textStyle = TextStyle(color = Color.White)
        )
        Spacer(modifier = Modifier.height(12.dp))
        OutlinedTextField(
            value = uiState.password,
            onValueChange = { onAction(UiAction.PasswordChanged(it)) },
            label = { Text(text = stringResource(R.string.password_text)) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            shape = RoundedCornerShape(12.dp),
            textStyle = TextStyle(color = Color.White),
            trailingIcon = {
                Icon(
                    painter = painterResource(R.drawable.ic_visibility),
                    contentDescription = null
                )
            }
        )
        Spacer(modifier = Modifier.height(12.dp))
        Button(
            onClick = { onAction(UiAction.SignInClicked) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            colors = ButtonColors(
                containerColor = colorResource(R.color.main_orange),
                contentColor = Color.White,
                disabledContentColor = Color.White,
                disabledContainerColor = Color.Gray

            )
        ) {
            Text(
                text = stringResource(R.string.sign_in_text),
                color = Color.Black,
                fontSize = 18.sp
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            HorizontalDivider(modifier = Modifier.weight(1f))
            Text(
                text = stringResource(R.string.or_text),
                color = Color.White,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(horizontal = 12.dp)
            )
            HorizontalDivider(modifier = Modifier.weight(1f))
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            SignInIcon(
                painter = painterResource(R.drawable.ic_apple)
            )
            SignInIcon(
                painter = painterResource(R.drawable.ic_google)
            )
            SignInIcon(
                painter = painterResource(R.drawable.ic_facebook)
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        Row {
            Text(
                text = stringResource(R.string.you_dont_have_an_account_text),
                color = Color.Gray
            )
            Text(
                text = stringResource(R.string.sign_up_text),
                color = colorResource(R.color.main_orange),
                modifier = Modifier.padding(start = 4.dp)
                    .clickable {
                        navigateToSignUp()
                    }
            )
        }
    }
}

@Composable
fun SignInIcon(
    modifier: Modifier = Modifier,
    painter: Painter,
    contentDescription: String? = null,
    tint: Color = Color.Unspecified
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .size(48.dp)
            .clip(CircleShape)
            .border(
                width = 1.dp,
                color = Color.White,
                shape = CircleShape
            )
    ) {
        Icon(
            painter = painter,
            contentDescription = contentDescription,
            modifier = Modifier.size(32.dp),
            tint = tint
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SignInScreenPreview(
    @PreviewParameter(SignInScreenPreviewProvider::class) uiState: UiState,
) {
    SignInScreen(
        uiState = uiState,
        uiEffect = emptyFlow(),
        onAction = {},
        navigateToSignUp = {},
        navigateToHome = {}
    )
}