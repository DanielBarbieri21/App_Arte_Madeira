package com.arteemadeira.app.ui.cliente

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.arteemadeira.app.data.model.Cliente
import com.arteemadeira.app.databinding.ItemClienteBinding

class ClienteAdapter(
    private val clientes: List<Cliente>,
    private val onEditClick: (Cliente) -> Unit,
    private val onDeleteClick: (Cliente) -> Unit
) : RecyclerView.Adapter<ClienteAdapter.ClienteViewHolder>() {

    inner class ClienteViewHolder(private val binding: ItemClienteBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(cliente: Cliente) {
            binding.tvNome.text = cliente.nome
            binding.tvTelefone.text = cliente.telefone
            binding.tvEmail.text = cliente.email

            binding.btnEditar.setOnClickListener { onEditClick(cliente) }
            binding.btnExcluir.setOnClickListener { onDeleteClick(cliente) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClienteViewHolder {
        val binding = ItemClienteBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ClienteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ClienteViewHolder, position: Int) {
        holder.bind(clientes[position])
    }

    override fun getItemCount() = clientes.size
}
