package com.chc.oilioili.navigation

import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.chc.oilioili.ui.screen.index.IndexPage
import com.chc.oilioili.ui.screen.movie.MoviePage
import com.chc.oilioili.utils.LocalNavController

@Composable
fun NavHostGraph(modifier: Modifier = Modifier) {
    val navController = LocalNavController.current
    val enterTransition = remember {
        slideInVertically(
            animationSpec = tween(
                durationMillis = 200,
                easing = FastOutSlowInEasing
            ),
            initialOffsetY = { it / 2 }
        ) + fadeIn(
            animationSpec = tween(
                durationMillis = 400
            )
        )
    }
    val exitTransition = remember {
        slideOutVertically(
            animationSpec = tween(
                durationMillis = 200,
                easing = FastOutLinearInEasing
            ),
            targetOffsetY = { it }
        ) + fadeOut(
            animationSpec = tween(
                durationMillis = 400
            )
        )
    }

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