
package com.example.app_cursos.Vista

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.app_cursos.R
import com.example.app_cursos.Modelo.dataDetalleCurso
import com.example.app_cursos.Presentador.clsDetalleCursoPresentador

class DetalleCursoActivity : AppCompatActivity() {

    private lateinit var presentador: clsDetalleCursoPresentador

    private lateinit var imgCurso: ImageView
    private lateinit var txtNombre: TextView
    private lateinit var txtCodigo: TextView
    private lateinit var txtDescripcion: TextView
    private lateinit var txtEspacio: TextView
    private lateinit var txtHoras: TextView
    private lateinit var txtDias: TextView
    private lateinit var txtHorario: TextView
    private lateinit var btnRegresar: Button

    private val urlServidor = "http://10.110.157.49/cursos_api/"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle_curso)

        imgCurso = findViewById(R.id.imgCurso)
        txtNombre = findViewById(R.id.txtNombre)
        txtCodigo = findViewById(R.id.txtCodigo)
        txtDescripcion = findViewById(R.id.txtDescripcion)
        txtEspacio = findViewById(R.id.txtEspacio)
        txtHoras = findViewById(R.id.txtHoras)
        txtDias = findViewById(R.id.txtDias)
        txtHorario = findViewById(R.id.txtHorario)
        btnRegresar = findViewById(R.id.btnRegresar)

        presentador = clsDetalleCursoPresentador(this)

        val cursoId = intent.getIntExtra("CURSO_ID", 0)

        presentador.consultarCurso(cursoId)

        btnRegresar.setOnClickListener {
            finish()
        }
    }

    fun mostrarCurso(curso: dataDetalleCurso) {

        txtNombre.text = curso.nombre
        txtCodigo.text = "Código: ${curso.codigo}"
        txtDescripcion.text = "Descripción: ${curso.descripcion ?: ""}"
        txtEspacio.text = "Edificio: ${curso.edificio}, Salón: ${curso.salon}"
        txtHoras.text = "Total de horas: ${curso.total_horas}"
        txtDias.text = "Días de atención: ${curso.dias_atencion}"
        txtHorario.text = "Horario: ${curso.hora_inicio} - ${curso.hora_fin}"

        val urlImagen = urlServidor + (curso.url_imagen ?: "")

        Glide.with(this)
            .load(urlImagen)
            .into(imgCurso)
    }

    fun mostrarError(mensaje: String) {
        Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show()
    }
}
