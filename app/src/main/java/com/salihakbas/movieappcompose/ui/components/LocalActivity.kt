package com.salihakbas.movieappcompose.ui.components

import android.app.Activity
import androidx.compose.runtime.staticCompositionLocalOf

val LocalActivity = staticCompositionLocalOf<Activity> {
    error("No Activity found!")
}