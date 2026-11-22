package com.example.app_cursos.Presentador

import com.example.app_cursos.Modelo.clsInscripcionModelo
import com.example.app_cursos.Vista.RegistroCurso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ClsInscripcionPresenter(private val view: RegistroCurso){
    private val modelo= clsInscripcionModelo()

    fun consultaAlumno(matricula: String){
        if (matricula.isEmpty())return

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val respuesta=modelo.consultarAlumno(matricula)
                withContext(Dispatchers.Main){
                  if (respuesta.exito){
                      view.mostrarCorreo(respuesta.correoInstitucional ?:"")
                  }else{
                      view.limpiarCorreo()
                      view.mostrarResultado(false,respuesta.mensaje)
                  }
                }
            }catch (e: Exception){
                withContext(Dispatchers.Main){
                    view.mostrarResultado(false,"Error de conexion: ${e.message}")
                }
            }
        }
    }
    fun registrarInscripcion(cursoId: Int,matricula: String,fecha: String){
        if (matricula.isEmpty() || fecha.isEmpty()){
            view.mostrarResultado(false,"completa todos los campos que se muestran")
            return
        }
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val respuesta = modelo.registrarInscripcion(idCurso = cursoId, matricula = matricula, fecha = fecha)
                withContext(Dispatchers.Main){
                    view.mostrarResultado(respuesta.exito,respuesta.mensaje)
                }
            }catch (e: Exception){
                withContext(Dispatchers.Main){
                    view.mostrarResultado(false, "Erro al registrars: ${e.message}")
                }
            }
        }
    }
}