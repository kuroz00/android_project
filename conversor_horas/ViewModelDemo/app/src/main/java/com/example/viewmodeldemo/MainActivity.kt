package com.example.viewmodeldemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.remember

import com.example.viewmodeldemo.ui.theme.ViewModelDemoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ViewModelDemoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ScreenSetup()
                }
            }
        }
    }
}

@Composable
fun ScreenSetup(viewModel: DemoViewModel = viewModel()) {
    MainScreen(
        minutesState = viewModel.updateMinutes(minutesState),
        hoursState = viewModel.hours,
        resultado =  viewModel.resultado
    )
}

@Composable
fun MainScreen(
    minutesState: Unit,
    hoursState: Unit,
    resultado: Int

) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {

        var hoursState by remember { mutableStateOf("") }
        var minutesState by remember { mutableStateOf("") }

        Text(
            "Calculo",
            modifier = Modifier.padding(20.dp),
            style = MaterialTheme.typography.headlineSmall
        )

        InputRow(
            hoursState = hoursState,
            minutesState = minutesState,
            onHoursChange = { text ->
                if (text.toIntOrNull() in 0..99) hoursState = text
            },
            onMinutesChange = { text ->
                if (text.toIntOrNull() in 0..60) minutesState = text
            }
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = { /* lógica para manejar el tiempo ingresado */ },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text("Submit Time")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputRow(
    hoursState: String,
    minutesState: String,
    onHoursChange: (String) -> Unit,
    onMinutesChange: (String) -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        OutlinedTextField(
            value = hoursState,
            onValueChange = onHoursChange,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            singleLine = true,
            label = { Text("Horas (0-99)") },
            modifier = Modifier.padding(10.dp),
            textStyle = TextStyle(fontWeight = FontWeight.Bold, fontSize = 30.sp)
        )

    }

    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        OutlinedTextField(
            value = minutesState,
            onValueChange = onMinutesChange,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            singleLine = true,
            label = { Text("Minutos (0-60)") },
            modifier = Modifier.padding(10.dp),
            textStyle = TextStyle(fontWeight = FontWeight.Bold, fontSize = 30.sp)
        )
    }
}





@Composable
fun CalculateAndShowResult(viewModel: DemoViewModel) {
    val result = viewModel.calculo()  // Realiza el cálculo en una función Composable
    Text("Resultado: $result", modifier = Modifier.padding(20.dp))
}
