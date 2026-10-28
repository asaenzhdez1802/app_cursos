package com.example.app_cursos.Vista

import android.app.DatePickerDialog
import android.icu.util.Calendar
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.example.app_cursos.Presentador.ClsInscripcionPresenter
import com.example.app_cursos.R

class RegistroCurso : AppCompatActivity() {
    private lateinit var imgCurso: ImageView
    private lateinit var  tvNombreCurso: TextView
    private lateinit var etMatricula: EditText
    private lateinit var  etFecha: EditText
    private lateinit var etCorreo: EditText
    private lateinit var  btnConfirmar: Button
    private lateinit var  btnCancelar: Button
    private lateinit var  presentador: ClsInscripcionPresenter

    private var cursoId: Int=0
    private val BASE_URL = "http://10.110.157.49/cursos_api/"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_registro_curso)
        imgCurso=findViewById(R.id.imgCursoInscripcion)
        tvNombreCurso=findViewById(R.id.tvNombreCursoInscripcion)
        etMatricula=findViewById(R.id.etMatricula)
        etFecha=findViewById(R.id.etFechaRegistro)
        etCorreo=findViewById(R.id.etCorreoInstitucional)
        btnConfirmar=findViewById(R.id.btnConfirmarInscripcion)
        btnCancelar=findViewById(R.id.btnCancelarInscripcion)

        presentador= ClsInscripcionPresenter(this)

        cursoId = intent.getIntExtra("CURSO_ID", 0)
        val nombre = intent.getStringExtra("CURSO_NOMBRE") ?: ""
        val imagen = intent.getStringExtra("CURSO_IMAGEN") ?: ""

        tvNombreCurso.text=nombre
        Glide.with(this)
            .load(BASE_URL+imagen)
            .placeholder(android.R.drawable.ic_menu_gallery)
            .error(android.R.drawable.stat_notify_error)
            .into(imgCurso)

        etMatricula.addTextChangedListener(object : android.text.TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: android.text.Editable?) {
                val matricula = s.toString().trim()
                limpiarCorreo()
                if (matricula.length == 8) {
                    presentador.consultaAlumno(matricula)
                }
            }
        })
        etFecha.setOnClickListener { mostrarCalendario() }
        btnConfirmar.setOnClickListener { confirmarInscripcio() }
        btnCancelar.setOnClickListener { finish() }
    }
    private fun mostrarCalendario(){
        val c= Calendar.getInstance()
        DatePickerDialog(
            this,
            {_,anio,mes,dia->
                etFecha.setText(String.format("%04d-%02d-%02d",anio,mes+1,dia))
            },
            c.get(Calendar.YEAR),c.get(Calendar.MONTH),c.get(Calendar.DAY_OF_MONTH)
        ).show()
    }
    private fun confirmarInscripcio(){

        val matricula=etMatricula.text.toString().trim()
        val fecha=etFecha.text.toString().trim()
        val correo=etCorreo.text.toString().trim()

        if (matricula.isEmpty() || fecha.isEmpty()) {
            mostrarError("captura la matricula y fecha")
            return
        }
        if (correo.isEmpty()) {
            mostrarError("no validado no se enconto")
            return
        }
        btnConfirmar.isEnabled=false
        presentador.registrarInscripcion(cursoId,matricula,fecha)
    }

    fun mostrarCorreo(correo: String){
        etCorreo.setText(correo)
    }
    fun limpiarCorreo(){
        etCorreo.setText("")
    }
    fun mostrarResultado(exito: Boolean,mensaje: String){
        btnConfirmar.isEnabled=true
        Toast.makeText(this,mensaje, Toast.LENGTH_LONG).show()
        if (exito)finish()
    }
    fun mostrarError(mensaje: String){
        Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show()
    }



}