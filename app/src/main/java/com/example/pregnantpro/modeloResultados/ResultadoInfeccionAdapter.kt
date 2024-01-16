package com.example.pregnantpro.modeloResultados

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pregnantpro.R

class ResultadoInfeccionAdapter(private val context: Context, private val resultadosInfeccion: List<ResultadoInfeccion>) :
    RecyclerView.Adapter<ResultadoInfeccionAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTitulo: TextView = itemView.findViewById(R.id.tvTituloResultadoInfeccion)
        val ivIcono: ImageView = itemView.findViewById(R.id.ivIconoResultadoInfeccion)
        val tvDescripcion: TextView = itemView.findViewById(R.id.tvDescripcionResultadoInfeccion)
        val tvFecha: TextView = itemView.findViewById(R.id.tvFechaResultadoInfeccion)
        val tvTipo: TextView = itemView.findViewById(R.id.tvTipoResultadoInfeccion)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_resultado_infeccion, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val resultadoInfeccion = resultadosInfeccion[position]
        holder.tvTitulo.text = resultadoInfeccion.titulo
        holder.ivIcono.setImageResource(android.R.drawable.ic_dialog_alert)
        holder.tvDescripcion.text = resultadoInfeccion.descripcion
        holder.tvFecha.text = resultadoInfeccion.fecha
        holder.tvTipo.text = resultadoInfeccion.tipo
    }

    override fun getItemCount(): Int {
        return resultadosInfeccion.size
    }
}