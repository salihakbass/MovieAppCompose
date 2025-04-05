package com.salihakbas.movieappcompose.ui.editprofile

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.salihakbas.movieappcompose.R
import com.salihakbas.movieappcompose.ui.components.CircleBackgroundIcon
import com.salihakbas.movieappcompose.ui.components.EmptyScreen
import com.salihakbas.movieappcompose.ui.components.LoadingBar
import com.salihakbas.movieappcompose.ui.editprofile.EditProfileContract.UiAction
import com.salihakbas.movieappcompose.ui.editprofile.EditProfileContract.UiEffect
import com.salihakbas.movieappcompose.ui.editprofile.EditProfileContract.UiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

@Composable
fun EditProfileScreen(
    uiState: UiState,
    uiEffect: Flow<UiEffect>,
    onAction: (UiAction) -> Unit,
    navigateBack: () -> Unit
) {
    when {
        uiState.isLoading -> LoadingBar()
        uiState.list.isNotEmpty() -> EmptyScreen()
        else -> EditProfileContent(
            uiState,
            navigateBack
        )
    }
}

@Composable
fun EditProfileContent(
    uiState: UiState,
    navigateBack: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(R.color.main_blue_bg))
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            CircleBackgroundIcon(
                onClick = { navigateBack()}
            )
            Text(
                text = "Edit Profile",
                fontSize = 20.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
        }
        Spacer(modifier = Modifier.height(48.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .border(1.dp, color = Color.LightGray, shape = RoundedCornerShape(8.dp))
                .background(color = colorResource(R.color.light_blue)),
            contentAlignment = Alignment.Center
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = null,
                    tint = Color.White
                )
                Text(
                    text = "Browse File Photo Profile",
                    fontSize = 14.sp,
                    color = Color.Gray,
                )
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = uiState.name,
            onValueChange = {},
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, color = Color.LightGray, shape = RoundedCornerShape(8.dp))
                .background(color = colorResource(R.color.light_blue)),
            textStyle = TextStyle(
                color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = uiState.email,
            onValueChange = {},
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, color = Color.LightGray, shape = RoundedCornerShape(8.dp))
                .background(color = colorResource(R.color.light_blue)),
            textStyle = TextStyle(
                color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = uiState.phoneNumber,
            onValueChange = {},
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, color = Color.LightGray, shape = RoundedCornerShape(8.dp))
                .background(color = colorResource(R.color.light_blue)),
            textStyle = TextStyle(
                color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = "",
            onValueChange = {},
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, color = Color.LightGray, shape = RoundedCornerShape(8.dp))
                .background(color = colorResource(R.color.light_blue)),
            placeholder = {
                Text(
                    text = "Gender",
                    color = Color.Gray,
                    fontSize = 16.sp,
                )
            },
            textStyle = TextStyle(
                color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = "",
            onValueChange = {},
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, color = Color.LightGray, shape = RoundedCornerShape(8.dp))
                .background(color = colorResource(R.color.light_blue)),
            placeholder = {
                Text(
                    text = "Date of Birth",
                    color = Color.Gray,
                    fontSize = 16.sp,
                )
            },
            textStyle = TextStyle(
                color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = "",
            onValueChange = {},
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, color = Color.LightGray, shape = RoundedCornerShape(8.dp))
                .background(color = colorResource(R.color.light_blue)),
            placeholder = {
                Text(
                    text = "Address",
                    color = Color.Gray,
                    fontSize = 16.sp,
                )
            },
            textStyle = TextStyle(
                color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        )
        Spacer(modifier = Modifier.height(24.dp))
        Button(
            onClick = { },
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
                text = "Save",
                color = Color.Black,
                fontSize = 18.sp
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun EditProfileScreenPreview(
    @PreviewParameter(EditProfileScreenPreviewProvider::class) uiState: UiState,
) {
    EditProfileScreen(
        uiState = uiState,
        uiEffect = emptyFlow(),
        onAction = {},
        navigateBack = {}
    )
}