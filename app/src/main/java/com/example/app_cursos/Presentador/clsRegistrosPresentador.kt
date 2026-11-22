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
        //se usa para no bloquear la interfaz
        CoroutineScope(Dispatchers.Main).launch {
            try {
                //Carga la vista con el spiner
                listaCarreras = modeloCarreras.obtenerCarreras()
                vista.mostrarCarreras(listaCarreras)

                cargarRegistros(0)

            } catch (e: Exception) {
                vista.mostrarError("Error: " + e.localizedMessage)
            }
        }
    }

    fun seleccionarCarrera(posicion: Int) {

        if (posicion >= 0 && posicion < listaCarreras.size) {
            val carrera = listaCarreras[posicion]

            cargarRegistros(carrera.id)
        }
    }

    private fun cargarRegistros(carreraId: Int) {

        CoroutineScope(Dispatchers.Main).launch {
            try {
                val registros = modeloRegistros.obtenerRegistrosPorCarrera(carreraId)
                vista.mostrarRegistros(registros)

            } catch (e: Exception) {
                vista.mostrarError("Error al cargar registros: " + e.localizedMessage)
            }
        }
    }
}