
package com.example.app_cursos.Presentador

import com.example.app_cursos.Modelo.clsDetalleCursoModelo
import com.example.app_cursos.Vista.DetalleCursoActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class clsDetalleCursoPresentador(val vista: DetalleCursoActivity) {

    private val modelo = clsDetalleCursoModelo()

    fun consultarCurso(cursoId: Int) {

        if (cursoId <= 0) {
            vista.mostrarError("Debe seleccionar un curso")
            return
        }

        CoroutineScope(Dispatchers.Main).launch {
            try {
                val curso = modelo.obtenerCurso(cursoId)
                vista.mostrarCurso(curso)

            } catch (e: Exception) {
                vista.mostrarError("Error al consultar el curso")
            }
        }
    }
}
