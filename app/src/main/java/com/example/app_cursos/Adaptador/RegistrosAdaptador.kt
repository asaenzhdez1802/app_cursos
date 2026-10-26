package com.example.app_cursos.Adaptador

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.app_cursos.Modelo.dataRegistroCurso
import com.example.app_cursos.R

class RegistrosAdaptador(
    private var registros: List<dataRegistroCurso>
) : RecyclerView.Adapter<RegistrosAdaptador.RegistroViewHolder>() {

    class RegistroViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        val imgCursoRegistro: ImageView =
            itemView.findViewById(R.id.imgCursoRegistro)

        val txtCursoRegistro: TextView =
            itemView.findViewById(R.id.txtCursoRegistro)

        val progresoInscripciones: ProgressBar =
            itemView.findViewById(R.id.progresoInscripciones)

        val txtInscritosRegistro: TextView =
            itemView.findViewById(R.id.txtInscritosRegistro)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RegistroViewHolder {

        val vista = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_registro, parent, false)

        return RegistroViewHolder(vista)
    }

    override fun onBindViewHolder(
        holder: RegistroViewHolder,
        position: Int
    ) {

        val registro = registros[position]

        holder.txtCursoRegistro.text = registro.nombre

        holder.txtInscritosRegistro.text =
            registro.inscritos.toString() + " inscritos / " +
                    registro.cupoTotal.toString() + " lugares"

        holder.progresoInscripciones.max = registro.cupoTotal
        holder.progresoInscripciones.progress = registro.inscritos

        val baseUrl = "http://172.31.98.90/cursos_api/"
        val urlImagen = baseUrl + registro.urlImagen

        Glide.with(holder.itemView.context)
            .load(urlImagen)
            .placeholder(android.R.drawable.ic_menu_gallery)
            .error(android.R.drawable.stat_notify_error)
            .into(holder.imgCursoRegistro)
    }

    override fun getItemCount(): Int {
        return registros.size
    }

    fun actualizarLista(nuevosRegistros: List<dataRegistroCurso>) {
        registros = nuevosRegistros
        notifyDataSetChanged()
    }
}