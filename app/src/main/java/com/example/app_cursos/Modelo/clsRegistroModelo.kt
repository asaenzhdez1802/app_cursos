package com.example.app_cursos.Modelo

class clsRegistrosModelo {

    suspend fun obtenerRegistrosPorCarrera(carreraId: Int): dataRegistros {
        return RetrofitClient.apiService.obtenerRegistros(carreraId)
    }
}