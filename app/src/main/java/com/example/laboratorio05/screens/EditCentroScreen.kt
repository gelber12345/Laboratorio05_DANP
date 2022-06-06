package com.example.laboratorio05.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.laboratorio05.data.centro.Centro
import com.example.laboratorio05.data.centro.CentroViewModel
import com.example.laboratorio05.navigation.AppScreens

@Composable
fun EditCentroScreen(navController: NavController,centro:Centro ,viewModel: CentroViewModel) {

    var latitud by remember { mutableStateOf(centro.latitud.toString()) }
    var longitud by remember { mutableStateOf(centro.longitud.toString()) }
    var departamento by remember { mutableStateOf(centro.departamento) }
    var horario by remember { mutableStateOf(centro.horario) }
    var direccion by remember { mutableStateOf(centro.direccion) }

    val onLatitudTextChange = { text : String ->
        latitud = text
    }

    val onLongitudTextChange = { text : String ->
        longitud = text
    }
    val onDepartamentoTextChange = { text : String ->
        departamento = text
    }

    val onHorarioTextChange = { text : String ->
        horario = text
    }
    val onDireccionTextChange = { text : String ->
        direccion = text
    }


    val scrollState= rememberScrollState()
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .verticalScroll(scrollState)

    ) {


        CustomTextField(
            title = "Latitud",
            textState = latitud,
            onTextChange = onLatitudTextChange,
            keyboardType = KeyboardType.Number
        )

        CustomTextField(
            title = "Longitud ",
            textState = longitud,
            onTextChange = onLongitudTextChange,
            keyboardType = KeyboardType.Number
        )

        CustomTextField(
            title = "Departamento",
            textState = departamento,
            onTextChange = onDepartamentoTextChange,
            keyboardType = KeyboardType.Text
        )
        CustomTextField(
            title = "Horario",
            textState = horario,
            onTextChange = onHorarioTextChange,
            keyboardType = KeyboardType.Text
        )
        CustomTextField(
            title = "Direccion",
            textState = direccion,
            onTextChange = onDireccionTextChange,
            keyboardType = KeyboardType.Text
        )

        if ( centro.id != 0){
            Button(onClick = {
                viewModel.deleteCentroById(centro.id)
                navController.navigate(AppScreens.FirstScreen.route)
            }) {
                Text("ELIMINAR")
            }
            Spacer(Modifier.height(20.0.dp))
            Button(onClick = {
                viewModel.updateCentro(Centro(
                    centro.id!!.toInt(),
                    latitud.toDouble()!!,
                    longitud.toDouble()!!,
                    departamento,
                    horario,
                    direccion
                ))
                navController.navigate(AppScreens.FirstScreen.route)
            }) {
                Text("GUARDAR")
            }
        }else{
            Button(onClick = {
                viewModel.insertCentro(Centro(
                    0,
                    latitud.toDouble()!!,
                    longitud.toDouble()!!,
                    departamento,
                    horario,
                    direccion
                ))
                navController.navigate(AppScreens.FirstScreen.route)
            }) {
                Text("ADD")
            }
        }
    }
}


@Composable
fun CustomTextField(
    title: String,
    textState: String,
    onTextChange: (String) -> Unit,
    keyboardType: KeyboardType
) {
    OutlinedTextField(
        value = textState,
        onValueChange = { onTextChange(it) },
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType
        ),
        singleLine = true,
        label = { Text(title) },
        modifier = Modifier.padding(10.dp),
        textStyle = TextStyle(fontWeight = FontWeight.Bold,
            fontSize = 30.sp)
    )
}