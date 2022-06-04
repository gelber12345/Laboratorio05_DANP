package com.example.laboratorio05.navigation

sealed class AppScreens(
    val route: String
) {
    object FirstScreen : AppScreens("first_screen")
    object EditCentroScreen : AppScreens("editCentroScreen")
    object EstadisticaScreen : AppScreens("estadistica_screen")
    object EditEstadisticaScreen : AppScreens("editEstadisticaScreen")
}