package com.adisastrawan.jetpacksubmission

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.adisastrawan.jetpacksubmission.ui.navigation.Screen
import com.adisastrawan.jetpacksubmission.ui.screen.about.About
import com.adisastrawan.jetpacksubmission.ui.screen.detail.Detail
import com.adisastrawan.jetpacksubmission.ui.screen.favorite.Favorite
import com.adisastrawan.jetpacksubmission.ui.screen.home.Home

@Composable
fun SubmissionApp(
    navController: NavHostController = rememberNavController()
) {
    NavHost(navController= navController, startDestination = Screen.Home.route){
        composable(Screen.Home.route){
            Home(navigateToDetail = {
                navController.navigate(Screen.Detail.createRoute(it))
            }, navigateToFavorite = {
                navController.navigate(Screen.Favorite.route)
            }, navigateToAbout = {
                navController.navigate(Screen.About.route)
            }
            )
        }
        composable(route = Screen.Detail.route, arguments = listOf(navArgument("name"){
            type = NavType.StringType
        })){
            val name = it.arguments?.getString("name")?:"ahri"
            Detail(name = name, navigateBack = { navController.popBackStack() })
        }
        composable(Screen.Favorite.route){
            Favorite(navigateToDetail = {
                navController.navigate(Screen.Detail.createRoute(it))
            }, navigateBack = {
                navController.popBackStack()
            })
        }
        composable(Screen.About.route){
            About(
                navigateBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}

