package com.example.app_cursos.Modelo

class clsInscripcionModelo {
    suspend fun  consultarAlumno(matricula: String): DataAlumnoRespon{//recibe la matricula y solicita al servicio que consulte  al alumno
        return RetrofitClient.apiService.obtenerAlumnos(matricula)
    }
    suspend fun registrarInscripcion(idCurso: Int, matricula: String, fecha: String): DataInscripcion {//registra la inscripcion  recibe los datos de la inscripcion

        val request = DataInscripcion(//crea un objeto donde reune toda la informacion en un solo objeto
            cursiId = idCurso,
            matricula = matricula,
            fechaRegistro = fecha
        )
        return RetrofitClient.apiService.registrarInscripcion(request)//envia el objeto al servidor y develve la respuesta
    }
}//el modelo se encarga de comunicarse can la api ,tiene la funcion de consultal y otra de enviar los datos de la inscribcion