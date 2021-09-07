package net.larntech.appdocente

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var listaDocente= emptyList<Docente>()

        val db = AppDB.getDatabase(this)

        db.DocenteDao().getAll().observe(this, Observer {
            listaDocente = it

            val adapter = DocenteAdapter(this, listaDocente)

            lista.adapter = adapter
        })


        lista.setOnItemClickListener { parent, view, position, id ->
            val intent = Intent(this, DocenteActivity::class.java)
            intent.putExtra("id", listaDocente[position].idDocente)
            startActivity(intent)
        }

        floatingActionButton.setOnClickListener{
            val intent= Intent(this, NuevoDocenteActivity::class.java)
            startActivity(intent)
        }
    }
}