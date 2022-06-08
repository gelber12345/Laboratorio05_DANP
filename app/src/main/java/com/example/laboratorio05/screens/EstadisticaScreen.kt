package com.example.laboratorio05.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.laboratorio05.data.estadistica.Estadistica
import com.example.laboratorio05.data.estadistica.EstadisticaViewModel
import com.example.laboratorio05.navigation.AppScreens

@Composable
fun EstadisticaScreen(navController: NavController, viewModel: EstadisticaViewModel) {

    val allEstadisticas by viewModel.fetchAllEstadistica().observeAsState(listOf())
    val searchResults by viewModel.searchResults.observeAsState(listOf())

    EstadisticaMainScreen(
        allEstadisticas = allEstadisticas,
        searchResults = searchResults,
        viewModel = viewModel,
        navController = navController
    )
}

@Composable
fun EstadisticaMainScreen(
    allEstadisticas: List<Estadistica>,
    searchResults: List<Estadistica>,
    viewModel: EstadisticaViewModel,
    navController: NavController
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()

    ) {
        //////////////

        var textBusqueda by remember { mutableStateOf("") }
        var searching by remember { mutableStateOf(false) }

        val onTextChange = { text: String ->
            textBusqueda = text
        }
        CustomTextField(
            title = "BÚSQUEDA",
            textState = textBusqueda,
            onTextChange = onTextChange,
            keyboardType = KeyboardType.Text,
        )
        //////////////
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            Button(onClick = {
                searching = false
                navController.navigate(AppScreens.EditEstadisticaScreen.route)
                navController.previousBackStackEntry?.savedStateHandle?.remove<Estadistica>("estadistica")
            }) {
                Text("AGREGAR")
            }
            Button(onClick = {
                searching = true
                viewModel.findEstadistica("%$textBusqueda%")
            }) {
                Text("BUSCAR")
            }
            Button(onClick = {
                searching = false
                viewModel.deleteAllEstadistica()
            }) {
                Text("ELIMINAR DATOS")
            }
        }
        //////////////
        LazyColumn(
            Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {

            val list = if (searching) searchResults else allEstadisticas
            item {
                TitleRowEstadistica(
                    head1 = "Id",
                    head2 = "Distrito",
                    head3 = "# Casos Positivos (Vivos)"
                )
            }

            items(list) { estadistica ->
                EstadisticaRow(estadistica, navController, viewModel)
            }
        }
    }
}

@Composable
fun TitleRowEstadistica(head1: String, head2: String, head3: String) {
    Row(
        modifier = Modifier
            .background(MaterialTheme.colors.primary)
            .fillMaxWidth()
            .padding(5.dp)
    ) {
        Text(
            head1, color = Color.White,
            modifier = Modifier
                .weight(0.1f)
        )
        Text(
            head2, color = Color.White,
            modifier = Modifier
                .weight(0.2f)
        )
        Text(
            head3, color = Color.White,
            modifier = Modifier.weight(0.2f)
        )
    }
}

@Composable
fun EstadisticaRow(
    estadistica: Estadistica,
    navController: NavController,
    viewModel: EstadisticaViewModel
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
            .clickable {

                navController.currentBackStackEntry?.savedStateHandle?.set(
                    "estadistica",
                    estadistica
                )
                navController.navigate(AppScreens.EditEstadisticaScreen.route)
            }
    ) {
        Text(
            estadistica.id.toString(), modifier = Modifier
                .weight(0.1f)
        )
        Text(estadistica.distrito, modifier = Modifier.weight(0.2f))
        Text(estadistica.positivosVivos.toString(), modifier = Modifier.weight(0.2f))
    }
}