package com.arteemadeira.app.ui.produto

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.arteemadeira.app.data.model.Produto
import com.arteemadeira.app.databinding.ItemClienteBinding

class ProdutoAdapter(
    private val produtos: List<Produto>
) : RecyclerView.Adapter<ProdutoAdapter.ProdutoViewHolder>() {

    inner class ProdutoViewHolder(private val binding: ItemClienteBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(produto: Produto) {
            binding.tvNome.text = produto.nome
            binding.tvTelefone.text = produto.categoria
            binding.tvEmail.text = "R$ ${produto.valorBase}"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProdutoViewHolder {
        val binding = ItemClienteBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ProdutoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProdutoViewHolder, position: Int) {
        holder.bind(produtos[position])
    }

    override fun getItemCount() = produtos.size
}
