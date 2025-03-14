package com.salihakbas.movieappcompose.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.salihakbas.movieappcompose.R

@Composable
fun DotIndicator(
    totalDots: Int,
    selectedIndex: Int
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxWidth()
    ) {
        repeat(totalDots) { index ->
            Box(
                modifier = Modifier
                    .padding(4.dp)
                    .size(if (selectedIndex == index) 10.dp else 8.dp)
                    .clip(CircleShape)
                    .background(if (selectedIndex == index) colorResource(R.color.main_orange) else Color.White)
                    .padding(4.dp)
            )
        }
    }
}