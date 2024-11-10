package com.example.viewmodeldemo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class DemoViewModel : ViewModel() {

    // Estados para almacenar horas y minutos
    private val _hours = MutableLiveData(0)
    val hours: LiveData<Int> get() = _hours

    private val _minutes = MutableLiveData(0)
    val minutes: LiveData<Int> get() = _minutes

    // Función para actualizar las horas con validación
    fun updateHours(value: String) {
        val intValue = value.toIntOrNull()
        if (intValue != null && intValue in 0..99) {
            _hours.value = intValue
        }
    }

    // Función para actualizar los minutos con validación
    fun updateMinutes(value: String) {
        val intValue = value.toIntOrNull()
        if (intValue != null && intValue in 0..60) {
            _minutes.value = intValue
        }
    }

    private val _resultado = MutableLiveData<Int>()
    val resultado: LiveData<Int> get() = _resultado

    fun calculo() {
        val currentHours = _hours.value ?: 0
        val currentMinutes = _minutes.value ?: 0

        // Realiza el cálculo y guarda el resultado en LiveData
        _resultado.value = (currentMinutes * 100 / 60) + currentHours
    }


}

