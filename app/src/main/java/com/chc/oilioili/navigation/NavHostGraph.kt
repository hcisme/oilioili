package com.chc.oilioili.navigation

import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.chc.oilioili.ui.screen.index.IndexPage
import com.chc.oilioili.ui.screen.movie.MoviePage
import com.chc.oilioili.utils.LocalNavController

val enterTransition = slideInVertically(
    animationSpec = tween(
        durationMillis = 300,
        easing = FastOutLinearInEasing
    ),
    initialOffsetY = { it }
) + fadeIn(
    animationSpec = tween(
        durationMillis = 600
    )
)

val exitTransition = slideOutVertically(
    animationSpec = tween(
        durationMillis = 300,
        easing = FastOutLinearInEasing
    ),
    targetOffsetY = { it }
) + fadeOut(
    animationSpec = tween(
        durationMillis = 600
    )
)

@Composable
fun NavHostGraph(modifier: Modifier = Modifier) {
    val navController = LocalNavController.current

    NavHost(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        navController = navController,
        startDestination = INDEX_PAGE,
    ) {
        composable(INDEX_PAGE) {
            IndexPage()
        }

        composable(
            "$MOVIE_PAGE/{id}",
            arguments = listOf(
                navArgument("id") {
                    type = NavType.StringType
                }
            ),
            enterTransition = { enterTransition },
            popEnterTransition = null,
            popExitTransition = { exitTransition }
        ) {
            val id = it.arguments?.getString("id")!!

            MoviePage(id = id)
        }
    }
}