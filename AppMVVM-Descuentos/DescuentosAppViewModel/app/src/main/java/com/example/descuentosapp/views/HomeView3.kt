package com.example.descuentosapp.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.descuentosapp.components.Alert
import com.example.descuentosapp.components.MainButton
import com.example.descuentosapp.components.MainTextField
import com.example.descuentosapp.components.SpaceH
import com.example.descuentosapp.components.TwoCards
import com.example.descuentosapp.viewModels.CalcularViewModel1
import com.example.descuentosapp.viewModels.CalcularViewModel2
import com.example.descuentosapp.viewModels.CalcularViewModel3


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeView3(viewModel3: CalcularViewModel3) {
    Scaffold(topBar = {
        CenterAlignedTopAppBar(
            title = { Text(text = "App descuentos", color = Color.White) },
            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                containerColor = MaterialTheme.colorScheme.primary
            )
        )
    }) {
        ContentHomeView3(it, viewModel3)
    }
}

@Composable
fun ContentHomeView3(paddingValues: PaddingValues, viewModel3: CalcularViewModel3) {
    Column(
        modifier = Modifier
            .padding(paddingValues)
            .padding(10.dp)
            .fillMaxSize(),
        //verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val state = viewModel3.state

        TwoCards(
            title1 = "Total",
            number1 = state.totalDescuento,
            title2 = "Descuento",
            number2 = state.precioDescuento
        )

        MainTextField(value = state.precio, onValueChange = { viewModel3.onValue(it,"precio") }, label = "Precio")
        SpaceH()
        MainTextField(value = state.descuento, onValueChange = { viewModel3.onValue(it, "descuento") }, label = "Descuento %")
        SpaceH(10.dp)
        MainButton(text = "Generar descuento") {
            viewModel3.calcular()
        }
        SpaceH()
        MainButton(text = "Limpiar", color = Color.Red) {
            viewModel3.limpiar()
        }

        if (state.showAlert){
            Alert(title = "Alerta",
                message = "Escribe el precio y descuento",
                confirmText = "Aceptar",
                onConfirmClick = { viewModel3.cancelAlert() }) { }
        }

    }
}
