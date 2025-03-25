package com.salihakbas.movieappcompose.navigation

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.salihakbas.movieappcompose.navigation.Screen.Explore
import com.salihakbas.movieappcompose.navigation.Screen.Favorite
import com.salihakbas.movieappcompose.navigation.Screen.Home
import com.salihakbas.movieappcompose.navigation.Screen.Notification
import com.salihakbas.movieappcompose.navigation.Screen.Onboarding
import com.salihakbas.movieappcompose.navigation.Screen.Payment
import com.salihakbas.movieappcompose.navigation.Screen.Profile
import com.salihakbas.movieappcompose.navigation.Screen.Search
import com.salihakbas.movieappcompose.navigation.Screen.SignIn
import com.salihakbas.movieappcompose.navigation.Screen.SignUp
import com.salihakbas.movieappcompose.navigation.Screen.Splash
import com.salihakbas.movieappcompose.navigation.Screen.Subscribe
import com.salihakbas.movieappcompose.ui.components.LocalActivity
import com.salihakbas.movieappcompose.ui.detail.DetailScreen
import com.salihakbas.movieappcompose.ui.detail.DetailViewModel
import com.salihakbas.movieappcompose.ui.explore.ExploreScreen
import com.salihakbas.movieappcompose.ui.explore.ExploreViewModel
import com.salihakbas.movieappcompose.ui.favorite.FavoriteScreen
import com.salihakbas.movieappcompose.ui.favorite.FavoriteViewModel
import com.salihakbas.movieappcompose.ui.home.HomeScreen
import com.salihakbas.movieappcompose.ui.home.HomeViewModel
import com.salihakbas.movieappcompose.ui.notification.NotificationScreen
import com.salihakbas.movieappcompose.ui.notification.NotificationViewModel
import com.salihakbas.movieappcompose.ui.onboarding.OnboardingScreen
import com.salihakbas.movieappcompose.ui.onboarding.OnboardingViewModel
import com.salihakbas.movieappcompose.ui.payment.PaymentScreen
import com.salihakbas.movieappcompose.ui.payment.PaymentViewModel
import com.salihakbas.movieappcompose.ui.profile.ProfileScreen
import com.salihakbas.movieappcompose.ui.profile.ProfileViewModel
import com.salihakbas.movieappcompose.ui.search.SearchScreen
import com.salihakbas.movieappcompose.ui.search.SearchViewModel
import com.salihakbas.movieappcompose.ui.signin.SignInScreen
import com.salihakbas.movieappcompose.ui.signin.SignInViewModel
import com.salihakbas.movieappcompose.ui.signup.SignUpScreen
import com.salihakbas.movieappcompose.ui.signup.SignUpViewModel
import com.salihakbas.movieappcompose.ui.splash.SplashScreen
import com.salihakbas.movieappcompose.ui.splash.SplashViewModel
import com.salihakbas.movieappcompose.ui.subscribe.SubscribeScreen
import com.salihakbas.movieappcompose.ui.subscribe.SubscribeViewModel
import com.salihakbas.movieappcompose.ui.trailer.TrailerScreen
import com.salihakbas.movieappcompose.ui.trailer.TrailerViewModel
import okio.ByteString.Companion.encode
import java.net.URLDecoder
import java.nio.charset.StandardCharsets

@Composable
fun NavigationGraph(
    navController: NavHostController,
    startDestination: Screen,
    modifier: Modifier = Modifier,
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination,
    ) {
        composable<Splash> {
            val viewModel: SplashViewModel = hiltViewModel()
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()
            val uiEffect = viewModel.uiEffect
            SplashScreen(
                uiState = uiState,
                uiEffect = uiEffect,
                onAction = viewModel::onAction,
                navController = navController
            )
        }
        composable<Onboarding> {
            val viewModel: OnboardingViewModel = hiltViewModel()
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()
            val uiEffect = viewModel.uiEffect
            OnboardingScreen(
                uiState = uiState,
                uiEffect = uiEffect,
                onAction = viewModel::onAction
            )
        }
        composable<SignIn> {
            val viewModel: SignInViewModel = hiltViewModel()
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()
            val uiEffect = viewModel.uiEffect
            SignInScreen(
                uiState = uiState,
                uiEffect = uiEffect,
                onAction = viewModel::onAction,
                navigateToSignUp = { navController.navigate(SignUp) },
                navigateToHome = { navController.navigate(Home) }
            )
        }
        composable<SignUp> {
            val viewModel: SignUpViewModel = hiltViewModel()
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()
            val uiEffect = viewModel.uiEffect
            SignUpScreen(
                uiState = uiState,
                uiEffect = uiEffect,
                onAction = viewModel::onAction,
                navigateToSignIn = { navController.navigate(SignIn) }
            )
        }
        composable<Home> {
            val viewModel: HomeViewModel = hiltViewModel()
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()
            val uiEffect = viewModel.uiEffect
            HomeScreen(
                uiState = uiState,
                uiEffect = uiEffect,
                onAction = viewModel::onAction,
                navigateToMovieDetail = { movieId ->
                    navController.navigate("detail/movie/$movieId")
                },
                navigateToSeriesDetail = { seriesId ->
                    navController.navigate("detail/series/$seriesId")
                }
            )
        }
        composable(
            route = "detail/{type}/{id}",
            arguments = listOf(
                navArgument("type") { type = NavType.StringType },
                navArgument("id") { type = NavType.IntType }
            )
        ) {
            val viewModel: DetailViewModel = hiltViewModel()
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()
            val uiEffect = viewModel.uiEffect

            DetailScreen(
                uiState = uiState,
                uiEffect = uiEffect,
                onAction = viewModel::onAction,
                navigateBack = { navController.popBackStack() },
                navigateToTrailer = { movieId ->
                    navController.navigate("trailer/$movieId")
                }
            )
        }
        composable(
            route = "trailer/{movieId}",
            arguments = listOf(
                navArgument("movieId") { type = NavType.IntType },
            )
        ) {
            val context = LocalContext.current
            val activity = (context as? Activity) ?: throw IllegalStateException("Activity not found")
            val viewModel: TrailerViewModel = hiltViewModel()
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()
            val uiEffect = viewModel.uiEffect

            CompositionLocalProvider(LocalActivity provides activity) {
                TrailerScreen(
                    uiState = uiState,
                    uiEffect = uiEffect,
                    onAction = viewModel::onAction,
                    onBackClick = { navController.popBackStack() }
                )
            }

        }
        composable<Notification> {
            val viewModel: NotificationViewModel = hiltViewModel()
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()
            val uiEffect = viewModel.uiEffect
            NotificationScreen(
                uiState = uiState,
                uiEffect = uiEffect,
                onAction = viewModel::onAction
            )
        }
        composable<Explore> {
            val viewModel: ExploreViewModel = hiltViewModel()
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()
            val uiEffect = viewModel.uiEffect
            ExploreScreen(
                uiState = uiState,
                uiEffect = uiEffect,
                onAction = viewModel::onAction
            )
        }
        composable<Search> {
            val viewModel: SearchViewModel = hiltViewModel()
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()
            val uiEffect = viewModel.uiEffect
            SearchScreen(
                uiState = uiState,
                uiEffect = uiEffect,
                onAction = viewModel::onAction
            )
        }
        composable<Favorite> {
            val viewModel: FavoriteViewModel = hiltViewModel()
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()
            val uiEffect = viewModel.uiEffect
            FavoriteScreen(
                uiState = uiState,
                uiEffect = uiEffect,
                onAction = viewModel::onAction
            )
        }
        composable<Profile> {
            val viewModel: ProfileViewModel = hiltViewModel()
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()
            val uiEffect = viewModel.uiEffect
            ProfileScreen(
                uiState = uiState,
                uiEffect = uiEffect,
                onAction = viewModel::onAction,
                navigateToSubscribe = { navController.navigate(Subscribe) },
                navigateToEditProfile = { navController.navigate(SignUp) }
            )
        }
        composable<Subscribe> {
            val viewModel: SubscribeViewModel = hiltViewModel()
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()
            val uiEffect = viewModel.uiEffect
            SubscribeScreen(
                uiState = uiState,
                uiEffect = uiEffect,
                onAction = viewModel::onAction,
                navigateToPayment = { navController.navigate(Payment) },
                navigateToBack = { navController.popBackStack() }
            )
        }
        composable<Payment> {
            val viewModel: PaymentViewModel = hiltViewModel()
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()
            val uiEffect = viewModel.uiEffect
            PaymentScreen(
                uiState = uiState,
                uiEffect = uiEffect,
                onAction = viewModel::onAction,
                navigateBack = { navController.popBackStack() }
            )
        }
    }
}