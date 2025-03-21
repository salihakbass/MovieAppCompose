package com.salihakbas.movieappcompose.ui.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.salihakbas.movieappcompose.R
import com.salihakbas.movieappcompose.data.model.ProductionCompany
import com.salihakbas.movieappcompose.ui.components.CircleBackgroundIcon
import com.salihakbas.movieappcompose.ui.components.EmptyScreen
import com.salihakbas.movieappcompose.ui.components.LoadingBar
import com.salihakbas.movieappcompose.ui.detail.DetailContract.UiAction
import com.salihakbas.movieappcompose.ui.detail.DetailContract.UiEffect
import com.salihakbas.movieappcompose.ui.detail.DetailContract.UiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

@Composable
fun DetailScreen(
    uiState: UiState,
    uiEffect: Flow<UiEffect>,
    onAction: (UiAction) -> Unit,
    navigateBack: () -> Unit
) {
    when {
        uiState.isLoading -> LoadingBar()
        uiState.list.isNotEmpty() -> EmptyScreen()
        else -> DetailContent(
            uiState = uiState,
            navigateBack = navigateBack
        )
    }
}

@Composable
fun DetailContent(
    uiState: UiState,
    navigateBack: () -> Unit
) {
    val movie = uiState.movie
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(R.color.main_blue_bg))
            .verticalScroll(scrollState),
    ) {
        TopBar(navigateBack = navigateBack)
        Spacer(modifier = Modifier.height(8.dp))
        movie?.poster_path?.let {
            AsyncImage(
                model = "https://image.tmdb.org/t/p/w500$it",
                contentDescription = null,
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .fillMaxWidth()
                    .height(450.dp)
                    .clip(RoundedCornerShape(16.dp)),
                contentScale = ContentScale.FillBounds
            )
        }
        Column(
            modifier = Modifier
                .padding(16.dp)
                .background(color = colorResource(R.color.main_blue_bg))
        ) {
            Text(
                text = (uiState.movie?.title ?: "") + "(${
                    uiState.movie?.release_date?.substring(
                        0,
                        4
                    )
                })",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            Text(
                text = uiState.movie?.runtime?.let { minutes ->
                    val hours = minutes / 60
                    val remainingMinutes = minutes % 60
                    "${hours}h ${remainingMinutes}m"
                } ?: "Unknown",
                fontSize = 16.sp,
                color = Color.White
            )
            Text(
                text = uiState.movie?.genres?.joinToString { it.name } ?: "Unknown",
                fontSize = 16.sp,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(16.dp))
            HorizontalDivider()
            OutlinedButton(
                onClick = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(R.color.main_orange)
                )
            ) {
                Text(
                    text = "Play Movie",
                    color = Color.Black,
                    fontSize = 18.sp
                )
            }
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = uiState.movie?.overview ?: "",
                fontSize = 16.sp,
                color = Color.White
            )
            CompanySection(uiState.movie?.production_companies)
        }
    }
}

@Composable
fun TopBar(navigateBack: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        CircleBackgroundIcon(onClick = navigateBack)
        Icon(
            painter = painterResource(R.drawable.ic_bookmark),
            contentDescription = null,
            tint = colorResource(R.color.main_orange),
            modifier = Modifier.size(40.dp)
        )
    }
}

@Composable
fun CompanySection(companies: List<ProductionCompany>?) {
    Text(
        text = "Product Companies",
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold,
        color = Color.White,
        modifier = Modifier.padding(top = 16.dp, bottom = 8.dp)
    )

    companies?.takeIf { it.isNotEmpty() }?.forEach { company ->
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            company.logo_path.let {
                AsyncImage(
                    model = "https://image.tmdb.org/t/p/w500$it",
                    contentDescription = company.name,
                    modifier = Modifier
                        .size(80.dp)
                        .padding(end = 8.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(Color.White.copy(alpha = 0.1f)),
                    contentScale = ContentScale.Fit
                )
            }
            Text(
                text = company.name,
                fontSize = 16.sp,
                color = Color.White
            )
        }
    } ?: Text(
        text = "Bilinmiyor",
        fontSize = 16.sp,
        color = Color.White
    )
}

@Preview(showBackground = true)
@Composable
fun DetailScreenPreview(
    @PreviewParameter(DetailScreenPreviewProvider::class) uiState: UiState,
) {
    DetailScreen(
        uiState = uiState,
        uiEffect = emptyFlow(),
        onAction = {},
        navigateBack = {}
    )
}