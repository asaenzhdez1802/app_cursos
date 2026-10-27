package com.example.app_cursos.Vista

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.app_cursos.Adaptador.RegistrosAdaptador
import com.example.app_cursos.Modelo.dataCarreras
import com.example.app_cursos.Modelo.dataRegistros
import com.example.app_cursos.Presentador.clsRegistrosPresentador
import com.example.app_cursos.R

class RegistrosActivity : AppCompatActivity() {

    private lateinit var presenter: clsRegistrosPresentador
    private lateinit var spCarrerasRegistros: Spinner
    private lateinit var txtTotalInscripciones: TextView
    private lateinit var rvRegistros: RecyclerView
    private lateinit var btnVolverCatalogo: Button
    private lateinit var adaptadorRegistros: RegistrosAdaptador

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_registros)

        ViewCompat.setOnApplyWindowInsetsListener(
            findViewById(R.id.main)
        ) { v, insets ->

            val systemBars = insets.getInsets(
                WindowInsetsCompat.Type.systemBars()
            )

            v.setPadding(
                systemBars.left,
                systemBars.top,
                systemBars.right,
                systemBars.bottom
            )

            insets
        }

        // 1. Enlazar los componentes del diseño
        spCarrerasRegistros = findViewById(R.id.spCarrerasRegistros)
        txtTotalInscripciones = findViewById(R.id.txtTotalInscripciones)
        rvRegistros = findViewById(R.id.rvRegistros)
        btnVolverCatalogo = findViewById(R.id.btnVolverCatalogo)

        // 2. Configurar la lista de registros
        rvRegistros.layoutManager = LinearLayoutManager(this)

        adaptadorRegistros = RegistrosAdaptador(emptyList())
        rvRegistros.adapter = adaptadorRegistros

        // 3. Inicializar el presentador
        presenter = clsRegistrosPresentador(this)

        configurarListeners()
        presenter.iniciar()
    }

    private fun configurarListeners() {

        spCarrerasRegistros.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {

                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    presenter.seleccionarCarrera(position)
                }

                override fun onNothingSelected(
                    parent: AdapterView<*>?
                ) {
                }
            }

        btnVolverCatalogo.setOnClickListener(
            this::volverCatalogo
        )
    }

    fun mostrarCarreras(carreras: List<dataCarreras>) {

        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            carreras
        )

        adapter.setDropDownViewResource(
            android.R.layout.simple_spinner_dropdown_item
        )

        spCarrerasRegistros.adapter = adapter
    }

    fun mostrarRegistros(registros: dataRegistros) {

        txtTotalInscripciones.text =
            "Total de inscripciones: " +
                    registros.totalInscripciones.toString()

        adaptadorRegistros.actualizarLista(registros.cursos)
    }

    fun mostrarError(mensaje: String) {

        Toast.makeText(
            this,
            mensaje,
            Toast.LENGTH_LONG
        ).show()
    }

    fun volverCatalogo(v: View) {
        finish()
    }
}