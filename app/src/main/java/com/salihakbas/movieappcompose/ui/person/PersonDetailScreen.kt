package com.salihakbas.movieappcompose.ui.person

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.salihakbas.movieappcompose.R
import com.salihakbas.movieappcompose.common.toFormattedDate
import com.salihakbas.movieappcompose.data.model.PersonDetailResponse
import com.salihakbas.movieappcompose.ui.components.CircleBackgroundIcon
import com.salihakbas.movieappcompose.ui.components.LoadingBar
import kotlinx.coroutines.flow.Flow

@Composable
fun PersonDetailScreen(
    uiState: PersonContract.UiState,
    onAction: (PersonContract.UiAction) -> Unit,
    uiEffect: Flow<PersonContract.UiEffect>,
    navigateBack: () -> Unit
) {
    when {
        uiState.isLoading -> LoadingBar()
        else -> PersonDetailContent(
            person = uiState.personDetail,
            navigateBack = navigateBack
        )
    }
}

@Composable
fun PersonDetailContent(person: PersonDetailResponse?, navigateBack: () -> Unit) {
    if (person == null) return

    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .background(color = colorResource(R.color.main_blue_bg))
            .padding(16.dp)
    ) {
        CircleBackgroundIcon(
            onClick = navigateBack
        )
        Spacer(modifier = Modifier.height(8.dp))
        person.profilePath?.let { path ->
            val imageUrl = "https://image.tmdb.org/t/p/w500$path"
            AsyncImage(
                model = imageUrl,
                contentDescription = person.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(400.dp)
                    .clip(RoundedCornerShape(12.dp)),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(16.dp))
        }

        Text(
            text = person.name,
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
        Text(
            text = person.knownForDepartment,
            fontSize = 16.sp,
            color = Color.Gray
        )
        Spacer(modifier = Modifier.height(8.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            person.birthday?.let {
                Text("🎂 ${it.toFormattedDate()}", fontSize = 14.sp, color = Color.White)
            }
            if (!person.deathday.isNullOrEmpty()) {
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    "✝ ${person.deathday.toFormattedDate()}",
                    fontSize = 14.sp,
                    color = Color.White
                )
            }
        }

        person.placeOfBirth?.let {
            Text(
                text = "📍 $it",
                fontSize = 14.sp,
                modifier = Modifier.padding(top = 4.dp),
                color = Color.White
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Biography",
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.White
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = if (person.biography.isNotBlank()) person.biography else "No biography available.",
            fontSize = 14.sp,
            lineHeight = 20.sp,
            color = Color.White
        )

        Spacer(modifier = Modifier.height(16.dp))

        if (person.alsoKnownAs.isNotEmpty()) {
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                "Also Known As",
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(4.dp))
            person.alsoKnownAs.forEach {
                Text("• $it", fontSize = 14.sp, color = Color.White)
            }
        }
    }
}
