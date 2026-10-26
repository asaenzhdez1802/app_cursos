package com.example.app_cursos.Modelo
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class dataCarreras(
    @SerialName("id")
    val id: Int,

    @SerialName("nombre")
    val nombre: String
) {
    override fun toString(): String {
        return nombre
    }
}