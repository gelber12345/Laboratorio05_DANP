package com.example.laboratorio05.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.laboratorio05.data.centro.Centro
import com.example.laboratorio05.data.centro.CentroViewModel
import com.example.laboratorio05.screens.EditCentroScreen
import com.example.laboratorio05.screens.FirstScreen

@Composable
fun  AppNavigation (
    navController: NavHostController,
    viewModelCentro: CentroViewModel,
    //viewModelEstadistica: EstadisticaViewModel
) {
    //val navController = rememberNavController()
    NavHost(navController = navController, startDestination = AppScreens.FirstScreen.route) {
        composable(
            route = AppScreens.FirstScreen.route
        ) {
            FirstScreen(navController, viewModelCentro)

        }
        composable(
            route = AppScreens.EditCentroScreen.route,
        ) {

            val result = navController.previousBackStackEntry?.savedStateHandle?.get<Centro>("centro")
            if (result!= null){
                EditCentroScreen(navController, result, viewModelCentro)
            }else{
                EditCentroScreen(navController, Centro(0, 0.0,0.0,"","",""), viewModelCentro)
            }
        }

    }
}