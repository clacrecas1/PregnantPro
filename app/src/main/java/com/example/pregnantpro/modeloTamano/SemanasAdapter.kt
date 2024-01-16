package com.example.pregnantpro.modeloTamano

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pregnantpro.R

class SemanasAdapter(private val semanasList: List<SemanasModel>) :
    RecyclerView.Adapter<SemanasAdapter.SemanaViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SemanaViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_semana, parent, false)
        return SemanaViewHolder(view)
    }

    override fun onBindViewHolder(holder: SemanaViewHolder, position: Int) {
        holder.bind(semanasList[position])
    }

    override fun getItemCount(): Int {
        return semanasList.size
    }

    class SemanaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imageViewSemana: ImageView = itemView.findViewById(R.id.imageViewSemana)
        private val textViewTituloSemana: TextView = itemView.findViewById(R.id.textViewTituloSemana)


        fun bind(semana: SemanasModel) {
            val imagenResourceId =
                itemView.resources.getIdentifier("fruta${semana.numeroSemana}", "mipmap", itemView.context.packageName)
            imageViewSemana.setImageResource(imagenResourceId)

            // Obtener datos de los arrays


            // Obtener valores correspondientes a la semana actual


            // Mostrar la información de peso y longitud
            textViewTituloSemana.text = "Semana ${semana.numeroSemana}"

        }
    }
}