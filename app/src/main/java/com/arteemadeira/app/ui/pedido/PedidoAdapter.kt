package com.arteemadeira.app.ui.pedido

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.arteemadeira.app.data.model.Pedido
import com.arteemadeira.app.databinding.ItemPedidoBinding
import com.arteemadeira.app.util.CurrencyUtils
import com.arteemadeira.app.util.DateUtils

class PedidoAdapter(
    private val pedidos: List<Pedido>,
    private val onEditClick: (Pedido) -> Unit,
    private val onDeleteClick: (Pedido) -> Unit
) : RecyclerView.Adapter<PedidoAdapter.PedidoViewHolder>() {

    inner class PedidoViewHolder(private val binding: ItemPedidoBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(pedido: Pedido) {
            binding.tvCliente.text = pedido.clienteNome
            binding.tvDescricao.text = pedido.descricaoMovel
            binding.tvValor.text = CurrencyUtils.format(pedido.valorEstimado)
            binding.tvData.text = DateUtils.formatDate(pedido.dataPedido)
            binding.tvStatus.text = pedido.getStatusProducao().descricao
            binding.tvStatus.setTextColor(pedido.getStatusProducao().cor)

            binding.btnEditar.setOnClickListener { onEditClick(pedido) }
            binding.btnExcluir.setOnClickListener { onDeleteClick(pedido) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PedidoViewHolder {
        val binding = ItemPedidoBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return PedidoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PedidoViewHolder, position: Int) {
        holder.bind(pedidos[position])
    }

    override fun getItemCount() = pedidos.size
}
