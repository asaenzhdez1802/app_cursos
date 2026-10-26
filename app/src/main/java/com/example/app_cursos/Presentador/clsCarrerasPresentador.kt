package com.example.app_cursos.Presentador

import com.example.app_cursos.Modelo.clsCarrerasModelo
import com.example.app_cursos.Modelo.clsCursosModelo
import com.example.app_cursos.Vista.MainActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class clsCarrerasPresentador(private val vista: MainActivity) {

    private val modeloCarreras = clsCarrerasModelo()
    private val modeloCursos = clsCursosModelo()

    fun obtenerCarreras() {

        CoroutineScope(Dispatchers.Main).launch {

            try {
                val carreras = modeloCarreras.obtenerCarreras()

                vista.mostrarCarreras(carreras)

            } catch (e: Exception) {
                vista.mostrarError(
                    "Error al obtener las carreras: " + e.message
                )
            }
        }
    }

    fun obtenerCursosPorCarrera(carreraId: Int) {

        CoroutineScope(Dispatchers.Main).launch {

            try {
                val cursos = modeloCursos.obtenerCursosPorCarrera(carreraId)

                vista.mostrarCursos(cursos)

            } catch (e: Exception) {
                vista.mostrarError(
                    "Error al obtener los cursos: " + e.message
                )
            }
        }
    }
}