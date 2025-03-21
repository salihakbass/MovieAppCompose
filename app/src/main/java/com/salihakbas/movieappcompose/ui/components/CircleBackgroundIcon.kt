package com.salihakbas.movieappcompose.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.salihakbas.movieappcompose.R

@Composable
fun CircleBackgroundIcon(
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .size(40.dp)
            .border(width = 2.dp, color = colorResource(R.color.main_orange), shape = CircleShape)
            .clickable {
                onClick()
            },
        contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_arrow_left),
            contentDescription = null,
            tint = colorResource(R.color.main_orange),
            modifier = Modifier.size(32.dp)
        )
    }
}