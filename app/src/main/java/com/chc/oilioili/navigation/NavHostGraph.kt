package com.chc.oilioili.navigation

import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
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

private const val AnimationInDuration = 300
private const val AnimationOutDuration = 300
private val AnimationEasing = LinearOutSlowInEasing
private val enterTransition = slideInHorizontally(
    animationSpec = tween(AnimationInDuration, easing = AnimationEasing),
    initialOffsetX = { it }
)
private val exitTransition = slideOutHorizontally(
    animationSpec = tween(AnimationOutDuration, easing = AnimationEasing),
    targetOffsetX = { it }
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