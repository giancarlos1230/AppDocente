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
            etSalario.setText(docente.salario.toString())
            etDireccion.setText(docente.direccion)
            idDocente = docente.idDocente
        }

        val database = AppDB.getDatabase(this)

        btnGuardar.setOnClickListener {
            val nombre = etNombre.text.toString()
            val salario = etSalario.text.toString().toDouble()
            val direccion = etDireccion.text.toString()

            val docente = Docente(nombre, salario, direccion)

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