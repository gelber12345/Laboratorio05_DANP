package com.example.laboratorio05.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector

sealed class AppScreens(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    object FirstScreen : AppScreens("first_screen","Centros", Icons.Filled.Home)
    object EditCentroScreen : AppScreens("editCentroScreen", "Edit Centro", Icons.Filled.Email)
    object EstadisticaScreen : AppScreens("estadistica_screen", "Estadísticas", Icons.Filled.Info)
    object EditEstadisticaScreen : AppScreens("editEstadisticaScreen","Edit Estadisticas", Icons.Filled.Edit)
}