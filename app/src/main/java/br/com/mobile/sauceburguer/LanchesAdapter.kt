package br.com.mobile.sauceburguer

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class LanchesAdapter (val lanches: List<Lanches>, val onClick: (Lanches) -> Unit) : RecyclerView.Adapter<LanchesAdapter.LanchesViewHolder>() {

    class LanchesViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val cardNome: TextView
        var cardImage: ImageView
        var cardView: CardView

        init {
            cardNome = view.findViewById(R.id.cardNome)
            cardImage = view.findViewById(R.id.cardImage)
            cardView = view.findViewById(R.id.cardDisciplina)
        }
    }

    override fun getItemCount() = this.lanches.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LanchesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_lanches, parent, false)
        val holder = LanchesViewHolder(view)
        return holder
    }

    override fun onBindViewHolder(holder: LanchesViewHolder, position: Int) {

        val lanches = this.lanches[position]

        holder.cardNome.text = lanches.nome

        Picasso.with(holder.itemView.context).load(lanches.foto).into(holder.cardImage, object: com.squareup.picasso.Callback {
            override fun onError() {

            }

            override fun onSuccess() {

            }

        })

        holder.itemView.setOnClickListener { onClick(lanches) }
    }

}