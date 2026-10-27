package com.example.app_cursos.Modelo

class clsInscripcionModelo {
    suspend fun  consultarAlumno(matricula: String): DataAlumnoRespon{
        return RetrofitClient.apiService.obtenerAlumnos(matricula)
    }
    suspend fun  registrarInscripcion(matricula: String, idCurso: String, fecha  :String): DataInscripcion{
        val request= DataInscripcion(
            cursiId = idCurso.toIntOrNull()?:0,
            matricula=matricula,
            fechaRegistro = fecha
        )
        return RetrofitClient.apiService.registrarInscripcion(request)
    }
}