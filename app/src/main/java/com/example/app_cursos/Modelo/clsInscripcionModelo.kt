package com.example.app_cursos.Modelo

class clsInscripcionModelo {
    suspend fun  consultarAlumno(matricula: String): DataAlumnoRespon{
        return RetrofitClient.apiService.obtenerAlumnos(matricula)
    }
    suspend fun registrarInscripcion(idCurso: Int, matricula: String, fecha: String): DataInscripcion {

        val request = DataInscripcion(
            cursiId = idCurso,
            matricula = matricula,
            fechaRegistro = fecha
        )
        return RetrofitClient.apiService.registrarInscripcion(request)
    }
}