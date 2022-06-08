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
import com.example.laboratorio05.data.estadistica.Estadistica
import com.example.laboratorio05.data.estadistica.EstadisticaViewModel
import com.example.laboratorio05.navigation.AppScreens

@Composable
fun EditEstadisticaScreen(
    navController: NavController,
    estadistica: Estadistica,
    viewModel: EstadisticaViewModel
) {

    var distrito by remember { mutableStateOf(estadistica.distrito) }
    var positivosVivos by remember { mutableStateOf(estadistica.positivosVivos.toString()) }
    var positivosDefucion by remember { mutableStateOf(estadistica.positivosDefucion.toString()) }
    var negativos by remember { mutableStateOf(estadistica.negativos.toString()) }
    var pendientes by remember { mutableStateOf(estadistica.pendientes.toString()) }

    val onDistritoTextChange = { text: String ->
        distrito = text
    }

    val onPositivosVivosTextChange = { text: String ->
        positivosVivos = text
    }
    val onPositivosDefucionTextChange = { text: String ->
        positivosDefucion = text
    }

    val onNegativosTextChange = { text: String ->
        negativos = text
    }
    val onPendientesTextChange = { text: String ->
        pendientes = text
    }

    val scrollState = rememberScrollState()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .verticalScroll(scrollState)

    ) {


        CustomTextFieldEstadistica(
            title = "Distrito",
            textState = distrito,
            onTextChange = onDistritoTextChange,
            keyboardType = KeyboardType.Text
        )

        CustomTextFieldEstadistica(
            title = "Cantidad de positivos vivos",
            textState = positivosVivos,
            onTextChange = onPositivosVivosTextChange,
            keyboardType = KeyboardType.Number
        )

        CustomTextFieldEstadistica(
            title = "Cantidad de positivos defucion",
            textState = positivosDefucion,
            onTextChange = onPositivosDefucionTextChange,
            keyboardType = KeyboardType.Number
        )
        CustomTextFieldEstadistica(
            title = "Cantidad de casos negativos",
            textState = negativos,
            onTextChange = onNegativosTextChange,
            keyboardType = KeyboardType.Number
        )
        CustomTextFieldEstadistica(
            title = "Cantidad de casos pendientes",
            textState = pendientes,
            onTextChange = onPendientesTextChange,
            keyboardType = KeyboardType.Number
        )

        if (estadistica.id != 0) {
            Button(onClick = {
                viewModel.deleteEstadisticaById(estadistica.id)
                navController.navigate(AppScreens.EstadisticaScreen.route)
            }) {
                Text("ELIMINAR")
            }
            Spacer(Modifier.height(20.0.dp))
            Button(onClick = {
                viewModel.updateEstadistica(
                    Estadistica(
                        estadistica.id!!.toInt(),
                        distrito,
                        positivosVivos.toInt(),
                        positivosDefucion.toInt(),
                        negativos.toInt(),
                        pendientes.toInt()
                    )
                )
                navController.navigate(AppScreens.EstadisticaScreen.route)
            }) {
                Text("GUARDAR")
            }
        } else {
            Button(onClick = {
                viewModel.insertEstadistica(
                    Estadistica(
                        0,
                        distrito,
                        positivosVivos.toInt(),
                        positivosDefucion.toInt(),
                        negativos.toInt(),
                        pendientes.toInt()
                    )
                )
                navController.navigate(AppScreens.EstadisticaScreen.route)
            }) {
                Text("AGREGAR")
            }
        }
    }
}


@Composable
fun CustomTextFieldEstadistica(
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
        textStyle = TextStyle(
            fontWeight = FontWeight.Bold,
            fontSize = 30.sp
        )
    )
}