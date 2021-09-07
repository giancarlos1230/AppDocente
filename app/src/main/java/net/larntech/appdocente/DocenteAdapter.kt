package net.larntech.appdocente

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.item_docente.view.*

class DocenteAdapter (private val mContext: Context, private val listaDocente: List<Docente>) : ArrayAdapter<Docente> (mContext, 0, listaDocente) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layaout = LayoutInflater.from(mContext).inflate(R.layout.item_docente, parent, false)

        val docente = listaDocente[position]
        layaout.nombre_docente.text = docente.nombre
        layaout.salario.text = "${docente.salario}"

        return layaout
    }
}
