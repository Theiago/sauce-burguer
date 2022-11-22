package br.com.mobile.sauceburguer

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class CarrinhoAdapter (val carrinho: List<Carrinho>, val onClick: (Carrinho) -> Unit) : RecyclerView.Adapter<CarrinhoAdapter.CarrinhoViewHolder>() {

    class CarrinhoViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val cardNome: TextView
//        var cardImage: ImageView
        var cardView: CardView

        init {
            cardNome = view.findViewById(R.id.cardNome)
//            cardImage = view.findViewById(R.id.cardImage)
            cardView = view.findViewById(R.id.cardCarrinho)
        }
    }

    override fun getItemCount() = this.carrinho.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarrinhoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_carrinho, parent, false)
        val holder = CarrinhoViewHolder(view)
        return holder
    }

    override fun onBindViewHolder(holder: CarrinhoViewHolder, position: Int) {
        val lanches = this.carrinho[position]
        holder.cardNome.text = lanches.nome

    }


    fun devolverId(holder: CarrinhoViewHolder, position: Int): String {
        val lanches = this.carrinho[position]
        return lanches.id.toString()
    }

}