package com.example.pregnantpro.modeloRecomendaciones





import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pregnantpro.R

class RecomendacionesNutricionAdapter(private val recomendacionesList: List<String>) :
    RecyclerView.Adapter<RecomendacionesNutricionAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_recomendacion_nutricion, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int {
        return recomendacionesList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val recomendacionTitulo: TextView =
            itemView.findViewById(R.id.recomendacionTitulo)
        private val ivIconoRecomendaciones: ImageView =
            itemView.findViewById(R.id.ivIconoRecomendaciones)
        private val recomendacionDescripcion: TextView =
            itemView.findViewById(R.id.recomendacionDescripcion)

        fun bind(position: Int) {
            if (position < recomendacionesList.size) {
                val recomendacion = recomendacionesList[position]

                // Supongamos que las recomendaciones son sencillas y simplemente se dividen en título y descripción
                val partesRecomendacion = recomendacion.split(": ")

                // Actualizar vistas con datos de la recomendación
                recomendacionTitulo.text = partesRecomendacion[0]
                recomendacionDescripcion.text = if (partesRecomendacion.size > 1) partesRecomendacion[1] else ""

                // Puedes personalizar el icono en función de la recomendación o dejarlo fijo
                ivIconoRecomendaciones.setImageResource(R.drawable.ic_recomendaciones)
            }
        }
    }
}
