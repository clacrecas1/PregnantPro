package com.example.pregnantpro.modeloRecomendaciones

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pregnantpro.R

class RecomendacionesDescansoAdapter(private val recomendacionesList: List<String>) :
    RecyclerView.Adapter<RecomendacionesDescansoAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_recomendacion_descanso, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (position < recomendacionesList.size) {
            val recomendacion = recomendacionesList[position]
            holder.bind(recomendacion)
        }
    }

    override fun getItemCount(): Int {
        return recomendacionesList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val recomendacionTitulo: TextView =
            itemView.findViewById(R.id.recomendacionTituloDescanso)
        private val ivIconoRecomendaciones: ImageView =
            itemView.findViewById(R.id.ivIconoRecomendacionesDescanso)
        private val recomendacionDescripcion: TextView =
            itemView.findViewById(R.id.recomendacionDescripcionDescanso)

        fun bind(recomendacion: String) {
            // Actualizar vistas con datos de la recomendación
            recomendacionTitulo.text = recomendacion
            recomendacionDescripcion.text = ""

            // Puedes personalizar el icono en función del título o dejarlo fijo
            ivIconoRecomendaciones.setImageResource(R.drawable.ic_descanso)
        }
    }
}