package net.larntech.appdocente

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_docente.*
import kotlinx.android.synthetic.main.activity_nuevo_docente.*
import kotlinx.android.synthetic.main.item_docente.*
import kotlinx.android.synthetic.main.item_docente.nombre_docente
import kotlinx.coroutines.*

class NuevoDocenteActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nuevo_docente)

        var idDocente: Int? = null

        if (intent.hasExtra("docente")) {

            val docente = intent.extras?.getSerializable("docente") as Docente

            etNombre.setText(docente.nombre)
            etApellido.setText(docente.apellido)
            etSexo.setText(docente.sexo)
            etFechaNacimiento.setText(docente.fechaNacimiento)
            etCiudad.setText(docente.ciudad)
            etDireccion.setText(docente.direccion)
            etPadecimiento.setText(docente.padecimiento)
            etCelular.setText(docente.celular.toString())
            etFechaIngreso.setText(docente.fechaIngreso)
            etSalario.setText(docente.salario.toString())

            idDocente = docente.idDocente
        }

        val database = AppDB.getDatabase(this)

        btnGuardar.setOnClickListener {
            val nombre = etNombre.text.toString()
            val apellido = etApellido.text.toString()
            val sexo = etSexo.text.toString()
            val fechaNacimiento = etFechaNacimiento.text.toString()
            val ciudad = etCiudad.text.toString()
            val direccion = etDireccion.text.toString()
            val padecimiento = etPadecimiento.text.toString()
            val celular = etCelular.text.toString().toDouble()
            val fechaIngreso = etFechaIngreso.text.toString()
            val salario = etSalario.text.toString().toDouble()

            val docente = Docente(nombre, apellido, sexo, fechaNacimiento, ciudad, direccion, padecimiento, celular, fechaIngreso, salario)

            if (idDocente != null) {
                CoroutineScope(Dispatchers.IO).launch {
                    docente.idDocente =idDocente

                    database.DocenteDao().update(docente)
                    this@NuevoDocenteActivity.finish()
                }
            } else{

                CoroutineScope(Dispatchers.IO).launch {
                    database.DocenteDao().insertAll(docente)
                }

                this@NuevoDocenteActivity.finish()

            }

        }
    }
}