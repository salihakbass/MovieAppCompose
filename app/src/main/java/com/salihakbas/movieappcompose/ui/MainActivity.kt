package com.salihakbas.movieappcompose.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.salihakbas.movieappcompose.R
import com.salihakbas.movieappcompose.navigation.BottomNavigation
import com.salihakbas.movieappcompose.navigation.NavigationGraph
import com.salihakbas.movieappcompose.navigation.Screen
import com.salihakbas.movieappcompose.ui.theme.MyappTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyappTheme {
                val navController = rememberNavController()
                val startDestination = Screen.Home

                val systemUiController = rememberSystemUiController()
                val backgroundColor = colorResource(R.color.main_blue_bg)

                SideEffect {
                    systemUiController.setStatusBarColor(color = backgroundColor)
                }

                val currentBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = currentBackStackEntry?.destination?.route

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = {
                        if (Screen.showBottomBar(currentDestination)) {
                            BottomNavigation(navController)
                        }
                    }
                ) { innerPadding ->

                    NavigationGraph(
                        navController = navController,
                        startDestination = startDestination,
                        modifier = Modifier.padding(innerPadding)
                    )
                }


            }
        }
    }
}