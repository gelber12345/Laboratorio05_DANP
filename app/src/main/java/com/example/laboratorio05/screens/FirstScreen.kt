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
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.laboratorio05.data.centro.Centro
import com.example.laboratorio05.data.centro.CentroViewModel
import com.example.laboratorio05.navigation.AppScreens

@Composable
fun FirstScreen(navController: NavController, viewModel: CentroViewModel) {

    val allCentros by viewModel.fetchAllCentro().observeAsState(listOf())
    val searchResults by viewModel.searchResults.observeAsState(listOf())

    FirstMainScreen(
        allCentros = allCentros,
        searchResults = searchResults,
        viewModel = viewModel,
        navController = navController
    )
}

@Composable
fun FirstMainScreen(
    allCentros: List<Centro>,
    searchResults: List<Centro>,
    viewModel: CentroViewModel,
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
            title = "BÚSQUEDA ",
            textState = textBusqueda,
            onTextChange = onTextChange,
            keyboardType = KeyboardType.Text
        )
        //////////////
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            Button(
                modifier = Modifier.background(Color.DarkGray),
                onClick = {
                    searching = false
                    navController.navigate(AppScreens.EditCentroScreen.route)
                    navController.previousBackStackEntry?.savedStateHandle?.remove<Centro>("centro")
                }) {
                Text("AGREGAR")
            }
            Button(
                onClick = {
                    searching = true
                    viewModel.findCentro("%$textBusqueda%")
                }) {
                Text("BUSCAR")
            }
            Button(onClick = {
                searching = false
                viewModel.deleteAllCentro()
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

            val list = if (searching) searchResults else allCentros
            item {
                TitleRow(head1 = "Id", head2 = "Departamento", head3 = "Horario")
            }

            items(list) { centro ->
                CentroRow(centro, navController, viewModel)
            }
        }
    }
}

@Composable
fun TitleRow(head1: String, head2: String, head3: String) {
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
fun CentroRow(centro: Centro, navController: NavController, viewModel: CentroViewModel) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
            .clickable {

                navController.currentBackStackEntry?.savedStateHandle?.set("centro", centro)
                navController.navigate(AppScreens.EditCentroScreen.route)
            }
    ) {
        Text(
            centro.id.toString(), modifier = Modifier
                .weight(0.1f)
        )
        Text(centro.departamento, modifier = Modifier.weight(0.2f))
        Text(centro.horario, modifier = Modifier.weight(0.2f))
    }
}