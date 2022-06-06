package com.example.laboratorio05.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.laboratorio05.data.centro.Centro
import com.example.laboratorio05.data.centro.CentroViewModel
import com.example.laboratorio05.data.estadistica.Estadistica
import com.example.laboratorio05.data.estadistica.EstadisticaViewModel
import com.example.laboratorio05.screens.EditCentroScreen
import com.example.laboratorio05.screens.EditEstadisticaScreen
import com.example.laboratorio05.screens.EstadisticaScreen
import com.example.laboratorio05.screens.FirstScreen

@Composable
fun  AppNavigation (
    navController: NavHostController,
    viewModelCentro: CentroViewModel,
    viewModelEstadistica: EstadisticaViewModel
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
        composable(
            route = AppScreens.EstadisticaScreen.route
        ) {
            EstadisticaScreen(navController, viewModelEstadistica)

        }
        composable(
            route = AppScreens.EditEstadisticaScreen.route,
        ) {

            val result = navController.previousBackStackEntry?.savedStateHandle?.get<Estadistica>("estadistica")
            if (result!= null){
                EditEstadisticaScreen(navController, result, viewModelEstadistica)
            }else{
                EditEstadisticaScreen(navController, Estadistica(0, "",0,0,0,0), viewModelEstadistica)
            }
        }

    }
}