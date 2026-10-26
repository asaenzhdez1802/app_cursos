package com.example.app_cursos.Presentador

import com.example.app_cursos.Modelo.clsCarrerasModelo
import com.example.app_cursos.Modelo.clsRegistrosModelo
import com.example.app_cursos.Modelo.dataCarreras
import com.example.app_cursos.Vista.RegistrosActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class clsRegistrosPresentador(val vista: RegistrosActivity) {

    private val modeloCarreras = clsCarrerasModelo()
    private val modeloRegistros = clsRegistrosModelo()

    private var listaCarreras: List<dataCarreras> = emptyList()

    fun iniciar() {

        // Se usa un Scope simple para no bloquear la interfaz
        CoroutineScope(Dispatchers.Main).launch {
            try {
                // 1. Cargar la lista de carreras en el Spinner
                listaCarreras = modeloCarreras.obtenerCarreras()
                vista.mostrarCarreras(listaCarreras)

                // 2. Cargar todos los registros por defecto
                cargarRegistros(0)

            } catch (e: Exception) {
                vista.mostrarError("Error: " + e.localizedMessage)
            }
        }
    }

    fun seleccionarCarrera(posicion: Int) {

        if (posicion >= 0 && posicion < listaCarreras.size) {
            val carrera = listaCarreras[posicion]

            // Si el identificador es 0, la API devuelve todas las carreras
            cargarRegistros(carrera.id)
        }
    }

    private fun cargarRegistros(carreraId: Int) {

        CoroutineScope(Dispatchers.Main).launch {
            try {
                val registros =
                    modeloRegistros.obtenerRegistrosPorCarrera(carreraId)

                vista.mostrarRegistros(registros)

            } catch (e: Exception) {
                vista.mostrarError(
                    "Error al cargar registros: " + e.localizedMessage
                )
            }
        }
    }
}