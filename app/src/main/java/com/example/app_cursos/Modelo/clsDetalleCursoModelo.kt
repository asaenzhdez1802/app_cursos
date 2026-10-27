
package com.example.app_cursos.Modelo

class clsDetalleCursoModelo {

    suspend fun obtenerCurso(cursoId: Int): dataDetalleCurso {
        return RetrofitClient.apiService.obtenerDetalle(cursoId)
    }
}
