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
    companion object{
        const val   CURSO_ID ="CURSO_ID"
        const val  CURSO_NOMBRE="CURSO_NOMBRE"
        const val  CURSO_IMAGEN="CURSO_IMAGEN"
        private const val BASE_URL = "http://10.110.157.49/cursos_api/"
    }

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
//la pantallas recive la informacion mediante un item,obtiene el identificador ,el nombre  y la imagen del curso seleccionada
        cursoId = intent.getIntExtra(CURSO_ID, 0)
        val nombre = intent.getStringExtra(CURSO_NOMBRE) ?: ""
        val imagen = intent.getStringExtra(CURSO_IMAGEN) ?: ""


        //muestra el nombre y la  imagen de curso
        tvNombreCurso.text=nombre
        Glide.with(this)//prepara la carga de la imagen
            .load(BASE_URL+imagen)//indica donde se encuentra la imagen
            .placeholder(android.R.drawable.ic_menu_gallery)//indica que imagen mostrar mientras se carga
            .error(android.R.drawable.stat_notify_error)//indica que imagen mostrar si ocurre un error
            .into(imgCurso)//indica donde se mostrar la imagen

        etMatricula.addTextChangedListener(object : android.text.TextWatcher {//detecta los cambios
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: android.text.Editable?) {
                val matricula = s.toString().trim()////obtiene la matricula
                limpiarCorreo()//limpa el correo al eliminar la patricula
                if (matricula.length == 8) {//verifca que la matricula tiene 8 caracteres si los tiene llama al presentador para realizar la consulta
                    presentador.consultaAlumno(matricula)
                }
            }
        })
        etFecha.setOnClickListener { mostrarCalendario() }
        btnConfirmar.setOnClickListener { confirmarInscripcio() }
        btnCancelar.setOnClickListener { finish() }
    }
    private fun mostrarCalendario(){
        val c= Calendar.getInstance()//obtiene la fecha actual
        DatePickerDialog(//cuando el usuaroio seleccione una fecha se obtiene tres datos
            this,
            {_,anio,mes,dia->//representa el anio,mes,dia seleccionado
                etFecha.setText(String.format("%04d-%02d-%02d",anio,mes+1,dia))//es el formato de la fecha para que consida con el formato de la base de datos
            },
            c.get(Calendar.YEAR),c.get(Calendar.MONTH),c.get(Calendar.DAY_OF_MONTH)//cuando el usuario seleccione ,le aparesca el calendario y le de formato a la fecha
        ).show()
    }
    private fun confirmarInscripcio(){//obtiene los datos que estan escritos

        val matricula=etMatricula.text.toString().trim()
        val fecha=etFecha.text.toString().trim()
        val correo=etCorreo.text.toString().trim()

        if (matricula.isEmpty() || fecha.isEmpty()) {//valida
            mostrarError("captura la matricula y fecha")
            return
        }
        if (correo.isEmpty()) {//valida fecha
            mostrarError("no validado no se enconto")
            return
        }
        btnConfirmar.isEnabled=false//desactiva el boton para evitar al pulsarlo mientras re realiza la valiacion
        presentador.registrarInscripcion(cursoId,matricula,fecha)//envia los datos al presentador
    }//obtiene los datos del formulario y revisa que no falten los campos necesario

    fun mostrarCorreo(correo: String){//recibe correo y lo muestra
        etCorreo.setText(correo)
    }
    fun limpiarCorreo(){//limpia el correo
        etCorreo.setText("")
    }
    fun mostrarResultado(exito: Boolean,mensaje: String){//indica si la operacion salio bien  y contiene el mensaje que se le mostrara al usuario
        btnConfirmar.isEnabled=true
        Toast.makeText(this,mensaje, Toast.LENGTH_LONG).show()
        if (exito)finish()//finaliza la operacion y cierra la pantalla
    }
    fun mostrarError(mensaje: String){//mastrar el error solamente
        Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show()
    }



}