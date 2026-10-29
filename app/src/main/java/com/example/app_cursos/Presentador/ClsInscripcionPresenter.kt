package com.example.app_cursos.Presentador

import com.example.app_cursos.Modelo.clsInscripcionModelo
import com.example.app_cursos.Vista.RegistroCurso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ClsInscripcionPresenter(private val view: RegistroCurso){
    private val modelo= clsInscripcionModelo()

    fun consultaAlumno(matricula: String){//consulta alumno si esta vacia se termina la funcion
        if (matricula.isEmpty())return

        CoroutineScope(Dispatchers.IO).launch {
            try {//aqui comienza la consulta la operacion se realiza en el hilo principal
                val respuesta=modelo.consultarAlumno(matricula)//envia la matricula al modelo y guarda la respuesta que este devuelve
                withContext(Dispatchers.Main){//el presentador comprueba el resultado
                  if (respuesta.exito){
                      view.mostrarCorreo(respuesta.correoInstitucional ?:"")//muestra el correo si fue exitosa
                  }else{//borra el correo y muestra el mensaje
                      view.limpiarCorreo()
                      view.mostrarResultado(false,respuesta.mensaje)
                  }
                }
            }catch (e: Exception){//si ocurre una exceocion durante la consulta muestra el mensaje
                withContext(Dispatchers.Main){
                    view.mostrarResultado(false,"Error de conexion: ${e.message}")
                }
            }
        }
    }
    fun registrarInscripcion(cursoId: Int,matricula: String,fecha: String){//recibe los datos necesarios para  registra
        if (matricula.isEmpty() || fecha.isEmpty()){//comprueba que la matricula y la fecha no esten vacias
            view.mostrarResultado(false,"completa todos los campos que se muestran")
            return
        }
        CoroutineScope(Dispatchers.IO).launch {//solicita al modelo que regisre la inscripcion
            try {
                val respuesta = modelo.registrarInscripcion(idCurso = cursoId, matricula = matricula, fecha = fecha)
                withContext(Dispatchers.Main){//cuando recibe la respuesta
                    view.mostrarResultado(respuesta.exito,respuesta.mensaje)
                }
            }catch (e: Exception){
                withContext(Dispatchers.Main){
                    view.mostrarResultado(false, "Erro al registrars: ${e.message}")
                }
            }
        }//recibe el curso ,la matricula y la fecha,comprueba que que los datos necesarios no esten vacios salicita al modelo  registra y muestra el resultado
    }
}