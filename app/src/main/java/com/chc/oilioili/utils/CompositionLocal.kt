package com.chc.oilioili.utils

import androidx.compose.runtime.compositionLocalOf
import androidx.navigation.NavHostController

/**
 * 路由 NavHostController
 */
val LocalNavController = compositionLocalOf<NavHostController> { error("No NavController found!") }
