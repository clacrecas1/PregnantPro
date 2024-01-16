package com.example.pregnantpro.modeloResultados

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pregnantpro.R

class ResultadoUltrasonidoAdapter(private val resultados: List<ResultadoUltrasonido>) :
    RecyclerView.Adapter<ResultadoUltrasonidoAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTitulo: TextView = itemView.findViewById(R.id.tvTituloResultado)
        val ivIcono: ImageView = itemView.findViewById(R.id.ivIconoResultado)
        val tvDescripcion: TextView = itemView.findViewById(R.id.tvDescripcionResultado)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_resultado_analisis, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val resultado = resultados[position]
        holder.tvTitulo.text = resultado.titulo
        holder.ivIcono.setImageResource(resultado.imagenResId)
        holder.tvDescripcion.text = resultado.descripcion
    }

    override fun getItemCount(): Int {
        return resultados.size
    }
}