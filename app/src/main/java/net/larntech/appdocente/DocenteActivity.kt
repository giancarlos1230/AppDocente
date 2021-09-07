package net.larntech.appdocente

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.room.Database
import kotlinx.android.synthetic.main.activity_docente.*
import kotlinx.android.synthetic.main.item_docente.*
import kotlinx.android.synthetic.main.item_docente.nombre_docente
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class DocenteActivity : AppCompatActivity() {

    private lateinit var database: AppDB
    private lateinit var docente: Docente
    private lateinit var docenteLiveData: LiveData<Docente>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_docente)

        database = AppDB.getDatabase(this)

        val idDocente = intent.getIntExtra("id", 0)

        docenteLiveData = database.DocenteDao().get(idDocente)

        docenteLiveData.observe(this, Observer {
            docente = it

            nombre_docente.text = docente.nombre
            apellido_docente.text=docente.apellido
            sexo_doente.text=docente.sexo
            fechaNacimiento_docente.text=docente.fechaNacimiento
            ciudad_docente.text=docente.ciudad
            direccion_docente.text = docente.direccion

            padecimiento_docente.text=docente.padecimiento
            celular_docente.text= "${docente.celular}"
            fechaIngreso_docente.text=docente.fechaIngreso
            salario_docente.text = "${docente.salario}"

        })

    }
        override fun onCreateOptionsMenu(menu: Menu?): Boolean {
            menuInflater.inflate(R.menu.docente_menu, menu)

            return super.onCreateOptionsMenu(menu)
        }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {

            R.id.edit_item -> {
            val intent = Intent (this, NuevoDocenteActivity::class.java)
                intent.putExtra("docente", docente)
                startActivity(intent)

            }

            R.id.delete_item -> {
                docenteLiveData.removeObservers(this)

                CoroutineScope(Dispatchers.IO).launch {

                    database.DocenteDao().delete(docente)
                    this@DocenteActivity.finish()
                }
            }

        }

        return super.onOptionsItemSelected(item)
    }
}