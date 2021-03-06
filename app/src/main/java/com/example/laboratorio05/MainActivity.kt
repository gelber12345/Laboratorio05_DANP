package com.example.laboratorio05

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.rememberNavController
import com.example.laboratorio05.data.centro.CentroViewModel
import com.example.laboratorio05.data.estadistica.EstadisticaViewModel
import com.example.laboratorio05.navigation.AppNavigation
import com.example.laboratorio05.navigation.AppScreens
import com.example.laboratorio05.screens.Componens.ButtomNavigationBar
import com.example.laboratorio05.ui.theme.Laboratorio05Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val centroViewModel = ViewModelProvider(this).get(CentroViewModel::class.java)
        val estadisticaViewModel = ViewModelProvider(this).get(EstadisticaViewModel::class.java)

        setContent {
            Laboratorio05Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    //val navController = rememberNavController()
                    //AppNavigation(navController,centroViewModel)

                    val navController = rememberNavController() // Redirección de vistas

                    //BOTTOM NAVIGATION
                    val navigationItems = listOf(
                        AppScreens.FirstScreen,
                        AppScreens.EstadisticaScreen
                    )
                    Scaffold(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.Blue),
                        bottomBar = { ButtomNavigationBar(navController = navController, items = navigationItems) }
                    ) { innerPadding ->
                        // Apply the padding globally to the whole BottomNavScreensController
                        Surface(modifier = Modifier.padding(innerPadding)) {
                            AppNavigation(navController,centroViewModel,estadisticaViewModel)
                        }
                    }

                }
            }
        }
    }
}

