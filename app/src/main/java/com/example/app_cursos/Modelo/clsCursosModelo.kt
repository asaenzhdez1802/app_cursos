package com.example.app_cursos.Modelo

class clsCursosModelo {

    suspend fun obtenerCursosPorCarrera(carreraId: Int): List<dataCursos> {
        return RetrofitClient.apiService.obtenerCursos(carreraId)
    }
}