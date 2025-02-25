package com.chc.oilioili.ui.screen.index

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.chc.oilioili.navigation.MOVIE_PAGE
import com.chc.oilioili.utils.LocalNavController
import kotlin.random.Random

@Composable
fun IndexPage(modifier: Modifier = Modifier) {
    val navHostController = LocalNavController.current

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .systemBarsPadding()
    ) {
        Button(
            onClick = {
                navHostController.navigate("${MOVIE_PAGE}/${Random.nextInt()}")
            }
        ) {
            Text("CLICK")
        }
    }
}