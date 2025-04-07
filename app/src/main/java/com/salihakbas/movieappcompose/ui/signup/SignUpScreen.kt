package com.salihakbas.movieappcompose.ui.signup

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.salihakbas.movieappcompose.R
import com.salihakbas.movieappcompose.common.collectWithLifecycle
import com.salihakbas.movieappcompose.ui.components.EmptyScreen
import com.salihakbas.movieappcompose.ui.components.LoadingBar
import com.salihakbas.movieappcompose.ui.signin.SignInIcon
import com.salihakbas.movieappcompose.ui.signup.SignUpContract.UiAction
import com.salihakbas.movieappcompose.ui.signup.SignUpContract.UiEffect
import com.salihakbas.movieappcompose.ui.signup.SignUpContract.UiState
import kotlinx.coroutines.flow.Flow

@Composable
fun SignUpScreen(
    uiState: UiState,
    uiEffect: Flow<UiEffect>,
    onAction: (UiAction) -> Unit,
    navigateToSignIn: () -> Unit
) {
    val context = LocalContext.current
    uiEffect.collectWithLifecycle { effect ->
        when (effect) {
            is UiEffect.NavigateToSignIn -> navigateToSignIn()
            is UiEffect.ShowSignUpToast -> {
                Toast.makeText(
                    context,
                    "Lütfen tüm alanları doldurun veya şifre gereksinimlerini sağlayın.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
    when {
        uiState.isLoading -> LoadingBar()
        uiState.list.isNotEmpty() -> EmptyScreen()
        else -> SignUpContent(
            uiState = uiState,
            onAction = onAction
        )
    }
}

@Composable
fun SignUpContent(
    uiState: UiState,
    onAction: (UiAction) -> Unit
) {
    var isVisible by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(R.color.main_blue_bg))
            .padding(top = 48.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        Text(
            text = "Sign Up",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
        OutlinedTextField(
            value = uiState.nameSurname,
            onValueChange = { onAction(UiAction.OnNameSurnameChanged(it)) },
            label = {
                Text(
                    text = "Full Name",
                    color = Color.Gray
                )
            },
            isError = uiState.nameSurnameError != null,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            shape = RoundedCornerShape(12.dp),
            textStyle = TextStyle(color = Color.White),
            supportingText = {
                uiState.nameSurnameError?.let {
                    Text(
                        text = it,
                        color = Color.Red,
                        fontSize = 12.sp
                    )
                }
            }
        )
        OutlinedTextField(
            value = uiState.email,
            onValueChange = { onAction(UiAction.OnEmailChanged(it)) },
            label = {
                Text(
                    text = "Email Address",
                    color = Color.Gray
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            shape = RoundedCornerShape(12.dp),
            textStyle = TextStyle(color = Color.White),
            isError = uiState.emailError != null,
            supportingText = {
                uiState.emailError?.let {
                    Text(text = it, color = Color.Red, fontSize = 12.sp)
                }
            }
        )
        OutlinedTextField(
            value = uiState.phoneNumber,
            onValueChange = { onAction(UiAction.OnPhoneNumberChanged(it)) },
            label = {
                Text(
                    text = "Phone Number",
                    color = Color.Gray
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            shape = RoundedCornerShape(12.dp),
            textStyle = TextStyle(color = Color.White),
            isError = uiState.phoneNumberError != null,
            supportingText = {
                uiState.phoneNumberError?.let {
                    Text(text = it, color = Color.Red, fontSize = 12.sp)
                }
            }
        )
        OutlinedTextField(
            value = uiState.password,
            onValueChange = { onAction(UiAction.OnPasswordChanged(it)) },
            label = {
                Text(
                    text = "Password",
                    color = Color.Gray
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            shape = RoundedCornerShape(12.dp),
            textStyle = TextStyle(color = Color.White),
            isError = uiState.passwordError != null,
            supportingText = {
                uiState.passwordError?.let {
                    Text(text = it, color = Color.Red, fontSize = 12.sp)
                }
            },
            visualTransformation = if (isVisible) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                IconButton(onClick = { isVisible = !isVisible }) {
                    Icon(
                        painter = painterResource(
                            if (isVisible) R.drawable.ic_visibility else R.drawable.ic_visibility_off
                        ),
                        contentDescription = null
                    )
                }
            }
        )
        Button(
            onClick = { onAction(UiAction.SignUpClicked) },
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
                text = "Sign Up",
                color = Color.Black,
                fontSize = 18.sp
            )
        }
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
        Row {
            Text(
                text = "You already have an account?",
                color = Color.Gray
            )
            Text(
                text = stringResource(R.string.sign_in_text),
                color = colorResource(R.color.main_orange),
                modifier = Modifier.padding(start = 4.dp)
            )
        }
    }
}

// @Preview(showBackground = true)
// @Composable
// fun SignUpScreenPreview(
//    @PreviewParameter(SignUpScreenPreviewProvider::class) uiState: UiState,
// ) {
//    SignUpScreen(
//        uiState = uiState,
//        uiEffect = emptyFlow(),
//        onAction = {},
//        navigateToSignIn = {},
//        errorState =
//    )
// }