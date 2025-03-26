package com.salihakbas.movieappcompose.common

import androidx.compose.foundation.border
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import java.text.SimpleDateFormat
import java.util.Locale

inline fun Modifier.conditional(
    condition: Boolean,
    modifier: Modifier.() -> Modifier,
): Modifier {
    return if (condition) {
        this then modifier(Modifier)
    } else {
        this
    }
}

@Composable
fun Modifier.boldBorder(
    radius: Int = 16,
    color: androidx.compose.ui.graphics.Color = androidx.compose.ui.graphics.Color.White,
    width: Dp = 2.dp
): Modifier {
    return this.border(
        width = width,
        color = color,
        shape = RoundedCornerShape(radius.dp)
    )
}

fun String.toFormattedDate(): String {
    return try {
        val parser = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        val date = parser.parse(this)
        formatter.format(date!!)
    } catch (e: Exception) {
        this
    }
}