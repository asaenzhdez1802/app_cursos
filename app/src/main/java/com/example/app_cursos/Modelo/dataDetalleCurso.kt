
package com.example.app_cursos.Modelo

import kotlinx.serialization.Serializable

@Serializable
data class dataDetalleCurso(
    val id: Int,
    val carrera_id: Int,
    val codigo: String,
    val nombre: String,
    val descripcion: String? = null,
    val edificio: String,
    val salon: String,
    val total_horas: Int,
    val dias_atencion: String,
    val hora_inicio: String,
    val hora_fin: String,
    val cupo_total: Int,
    val url_imagen: String? = null
)
