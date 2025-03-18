package com.salihakbas.movieappcompose.ui.subscribe

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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
import com.salihakbas.movieappcompose.ui.components.SubscribeTabRow
import com.salihakbas.movieappcompose.ui.subscribe.SubscribeContract.UiAction
import com.salihakbas.movieappcompose.ui.subscribe.SubscribeContract.UiEffect
import com.salihakbas.movieappcompose.ui.subscribe.SubscribeContract.UiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

@Composable
fun SubscribeScreen(
    uiState: UiState,
    uiEffect: Flow<UiEffect>,
    onAction: (UiAction) -> Unit,
    navigateToPayment: () -> Unit
) {
    uiEffect.collectWithLifecycle {
        when (it) {
            is UiEffect.NavigateToPayment -> navigateToPayment()
        }
    }
    when {
        uiState.isLoading -> LoadingBar()
        uiState.list.isNotEmpty() -> EmptyScreen()
        else -> SubscribeContent(
            navigateToPayment = navigateToPayment
        )
    }
}

@Composable
fun SubscribeContent(
    navigateToPayment: () -> Unit
) {
    val tabList = listOf("Monthly", "Yearly")
    var selectedTabIndex by remember { mutableIntStateOf(0) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(R.color.main_blue_bg)),
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_arrow_left),
                contentDescription = null,
                tint = colorResource(R.color.main_orange),
                modifier = Modifier.size(36.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = "Subscribe",
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }
        SubscribeTabRow(
            tabTitles = tabList,
            selectedTabIndex = selectedTabIndex,
            onTabSelected = { index ->
                selectedTabIndex = index
            }
        )
        Spacer(modifier = Modifier.weight(1f))
        SubscribeCard(navigateToPayment)
        Spacer(modifier = Modifier.weight(1f))
    }
}

@Composable
fun SubscribeCard(
    navigateToPayment: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                vertical = 64.dp,
                horizontal = 16.dp
            )
            .background(
                color = colorResource(R.color.subscribe_card_bg),
                shape = RoundedCornerShape(16.dp)
            )
            .border(1.dp, colorResource(R.color.main_orange), RoundedCornerShape(16.dp))
            .height(450.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Premium Account",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
            )
            Text(
                text = "Get access to all movies and TV shows",
                color = Color.White
            )
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            fontSize = 50.sp
                        )
                    ) {
                        append("$ 20")
                    }
                    withStyle(
                        style = SpanStyle(
                            color = Color.White,
                            fontSize = 20.sp
                        )
                    ) {
                        append(" / Month")
                    }
                }
            )
            SubscribeAdvantageText(
                text = "5 Device available"
            )
            SubscribeAdvantageText(
                text = "Choose unlimited movies"
            )
            SubscribeAdvantageText(
                text = "There are no ads in any movie"
            )
            SubscribeAdvantageText(
                text = "All movies are HD, FHD, 4K quality"
            )
            SubscribeAdvantageText(
                text = "Get all the extra features"
            )

            Button(
                onClick = { navigateToPayment() },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(R.color.main_orange)
                )
            ) {
                Text(
                    text = "Choose Plan",
                    color = Color.Black
                )
            }
        }
    }
}

@Composable
fun SubscribeAdvantageText(
    text: String
) {
    Row(
        modifier = Modifier
            .padding(vertical = 8.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(R.drawable.ic_done),
            contentDescription = null,
            modifier = Modifier.size(24.dp),
            tint = Color.White
        )
        Text(
            text = text,
            color = Color.White,
            fontSize = 14.sp
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SubscribeScreenPreview(
    @PreviewParameter(SubscribeScreenPreviewProvider::class) uiState: UiState,
) {
    SubscribeScreen(
        uiState = uiState,
        uiEffect = emptyFlow(),
        onAction = {},
        navigateToPayment = {}
    )
}