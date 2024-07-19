package com.devstudio.animatedbottomnavs.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.devstudio.animatedbottomnavs.HomeScreen
import com.devstudio.animatedbottomnavs.ui.SampleOne
import com.devstudio.animatedbottomnavs.ui.SampleTwo
import kotlinx.serialization.Serializable


@Serializable
object HomeScreen

@Serializable
object Sample1

@Serializable
object Sample2



@Composable
fun NavigationGraph(
    navController: NavHostController
) {

    NavHost(navController, startDestination = HomeScreen) {
        composable<HomeScreen> {
            HomeScreen(navController)
        }
        composable<Sample1> {
            SampleOne()
        }
        composable<Sample2> {
            SampleTwo()
        }

    }

}