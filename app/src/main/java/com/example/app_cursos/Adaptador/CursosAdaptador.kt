package com.example.app_cursos.Adaptador

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.app_cursos.Modelo.dataCursos
import com.example.app_cursos.R
import com.example.app_cursos.Vista.MainActivity

class CursosAdaptador( private var cursos: List<dataCursos>,private val activity: MainActivity
) : RecyclerView.Adapter<CursosAdaptador.CursoViewHolder>() {

    class CursoViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView) {

        val imgCurso: ImageView = itemView.findViewById(R.id.imgCurso)
        val txtCurso: TextView = itemView.findViewById(R.id.txtCurso)
        val txtSalon: TextView = itemView.findViewById(R.id.txtSalon)
        val btnVerDetalle: Button = itemView.findViewById(R.id.btnVerDetalle)
        val btnRegistrarse: Button = itemView.findViewById(R.id.btnRegistrarse)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CursoViewHolder {

        val vista = LayoutInflater.from(parent.context).inflate(R.layout.item_curso, parent, false)
        return CursoViewHolder(vista)
    }

    override fun onBindViewHolder( holder: CursoViewHolder,position: Int) {
        val curso = cursos[position]
        holder.txtCurso.text = curso.nombre
        holder.txtSalon.text = "Salón: " + curso.salon
        val baseUrl = "http://10.110.157.49/cursos_api/"
        val urlImagen = baseUrl + curso.urlImagen
        Glide.with(holder.itemView.context)
            .load(urlImagen)
            .placeholder(android.R.drawable.ic_menu_gallery)
            .error(android.R.drawable.stat_notify_error)
            .into(holder.imgCurso)

        holder.btnVerDetalle.setOnClickListener(
            object : View.OnClickListener {
                override fun onClick(v: View?) {
                    activity.seleccionarCurso(curso)
                }
            }
        )

        holder.btnRegistrarse.setOnClickListener(
            object : View.OnClickListener {
                override fun onClick(v: View?) {
                    activity.registrarCurso(curso)
                }
            }
        )
    }

    override fun getItemCount(): Int {
        return cursos.size
    }

    fun actualizarCursos(nuevosCursos: List<dataCursos>) {
        cursos = nuevosCursos
        notifyDataSetChanged()
    }
}