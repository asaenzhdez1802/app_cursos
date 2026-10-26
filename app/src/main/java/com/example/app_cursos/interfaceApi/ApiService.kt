package com.example.app_cursos.interfaceApi

import com.example.app_cursos.Modelo.dataCarreras
import com.example.app_cursos.Modelo.dataCursos
import retrofit2.http.GET
import retrofit2.http.Query
import com.example.app_cursos.Modelo.dataRegistros

interface ApiService {

    @GET("obtener_carreras.php")
    suspend fun obtenerCarreras(): List<dataCarreras>

    @GET("obtener_cursos.php")
    suspend fun obtenerCursos(
        @Query("carrera_id") carreraId: Int
    ): List<dataCursos>

    @GET("obtener_registros.php")
    suspend fun obtenerRegistros(
        @Query("carrera_id") carreraId: Int
    ): dataRegistros
}