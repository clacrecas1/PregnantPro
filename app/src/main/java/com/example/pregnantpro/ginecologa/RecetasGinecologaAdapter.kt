package com.example.pregnantpro.ginecologa

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.pregnantpro.R
import com.example.pregnantpro.modeloReceta.Receta

class RecetasGinecologaAdapter (private val context: Context, private val recetas: List<Receta>) : BaseAdapter() {
    override fun getCount(): Int {
        return recetas.size
    }

    override fun getItem(position: Int): Any {
        return recetas[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = LayoutInflater.from(context).inflate(R.layout.item_receta_ginecologo, parent, false)

        val tituloReceta = view.findViewById<TextView>(R.id.tvTituloRecetaGinecologo)
        val descripcionReceta = view.findViewById<TextView>(R.id.tvDescripcionRecetaGinecologo)

        val receta = recetas[position]

        tituloReceta.text = receta.titulo
        descripcionReceta.text = receta.descripcion

        return view
    }
}