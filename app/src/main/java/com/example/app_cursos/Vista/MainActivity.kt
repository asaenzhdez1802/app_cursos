package com.example.app_cursos.Vista


import android.os.Bundle
import android.content.Intent
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.app_cursos.Adaptador.CursosAdaptador
import com.example.app_cursos.Modelo.dataCarreras
import com.example.app_cursos.Modelo.dataCursos
import com.example.app_cursos.Presentador.clsCarrerasPresentador
import com.example.app_cursos.R


class MainActivity : AppCompatActivity() {


    private lateinit var spCarreras: Spinner
    private lateinit var rvCursos: RecyclerView
    private lateinit var btnVerRegistros: Button
    private lateinit var presentador: clsCarrerasPresentador
    private lateinit var adaptadorCursos: CursosAdaptador

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main))
        { v, insets ->
            val systemBars = insets.getInsets(
                WindowInsetsCompat.Type.systemBars()
            )
            v.setPadding(systemBars.left,systemBars.top,systemBars.right,systemBars.bottom)
            insets
        }

        spCarreras = findViewById(R.id.spCarreras)
        rvCursos = findViewById(R.id.rvCursos)
        btnVerRegistros = findViewById(R.id.btnVerRegistros)
        presentador = clsCarrerasPresentador(this)
        rvCursos.layoutManager = GridLayoutManager(this, 2)
        adaptadorCursos = CursosAdaptador(emptyList(), this)
        rvCursos.adapter = adaptadorCursos

        spCarreras.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

                override fun onItemSelected(parent: AdapterView<*>?,view: View?,position: Int,id: Long) {

                    val carrera = parent?.getItemAtPosition(position) as dataCarreras
                    presentador.obtenerCursosPorCarrera(carrera.id)
                }

                override fun onNothingSelected( parent: AdapterView<*>?) {
                }
            }

        btnVerRegistros.setOnClickListener(
            object : View.OnClickListener {
                override fun onClick(v: View?) {
                    verRegistros()
                }
            }
        )

        presentador.obtenerCarreras()
    }

    fun mostrarCarreras(carreras: List<dataCarreras>) {

        val adaptadorCarreras = ArrayAdapter(this,android.R.layout.simple_spinner_item,carreras)

        adaptadorCarreras.setDropDownViewResource(
            android.R.layout.simple_spinner_dropdown_item
        )

        spCarreras.adapter = adaptadorCarreras
    }

    fun mostrarCursos(cursos: List<dataCursos>) {
        adaptadorCursos.actualizarCursos(cursos)
    }

    fun mostrarError(mensaje: String) {
        Toast.makeText(this, mensaje,Toast.LENGTH_LONG).show()
    }

    fun seleccionarCurso(curso: dataCursos) {
        val intent = Intent(this, DetalleCursoActivity::class.java)
        intent.putExtra("CURSO_ID", curso.id)
        startActivity(intent)
    }

    fun registrarCurso(curso: dataCursos) {
        val intent = Intent(this, RegistroCurso::class.java).apply {
            putExtra(RegistroCurso.CURSO_ID, curso.id)
            putExtra(RegistroCurso.CURSO_NOMBRE, curso.nombre)
            putExtra(RegistroCurso.CURSO_IMAGEN, curso.urlImagen)
        }
        startActivity(intent)
    }

    fun verRegistros() {
        val intent = Intent(this, RegistrosActivity::class.java)
        startActivity(intent)

    }
}