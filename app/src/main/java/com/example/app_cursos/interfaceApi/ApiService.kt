package com.example.app_cursos.interfaceApi

import com.example.app_cursos.Modelo.DataAlumnoRespon
import com.example.app_cursos.Modelo.DataInscripcion
import com.example.app_cursos.Modelo.dataCarreras
import com.example.app_cursos.Modelo.dataCursos
import com.example.app_cursos.Modelo.dataDetalleCurso
import com.example.app_cursos.Modelo.dataRegistros
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {

    @GET("obtener_carreras.php")
    suspend fun obtenerCarreras(): List<dataCarreras>

    @GET("obtener_cursos.php")
    suspend fun obtenerCursos(
        @Query("carrera_id") carreraId: Int
    ): List<dataCursos>

    @GET("detalle_curso.php")
    suspend fun obtenerDetalle(
        @Query("curso_id") cursoId: Int
    ): dataDetalleCurso

    @GET("obtener_registros.php")
    suspend fun obtenerRegistros(
        @Query("carrera_id") carreraId: Int
    ): dataRegistros

    @GET("obtener_alumno.php")
    suspend fun obtenerAlumnos(
        @Query("matricula") matricula: String
    ): DataAlumnoRespon

    @POST("registrar_inscripcion.php")
    suspend fun registrarInscripcion(
        @Body request: DataInscripcion
    ): DataInscripcion
}