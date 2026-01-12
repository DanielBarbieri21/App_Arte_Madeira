package com.arteemadeira.app.ui.estoque

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.arteemadeira.app.data.model.Material
import com.arteemadeira.app.databinding.ItemMaterialBinding
import com.arteemadeira.app.util.CurrencyUtils

class MaterialAdapter(
    private val materiais: List<Material>
) : RecyclerView.Adapter<MaterialAdapter.MaterialViewHolder>() {

    inner class MaterialViewHolder(private val binding: ItemMaterialBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(material: Material) {
            binding.tvNomeMaterial.text = material.nome
            binding.tvQuantidade.text = "${material.quantidadeEstoque} ${material.unidade}"
            binding.tvValor.text = CurrencyUtils.format(material.valorUnitario)

            if (material.precisaReposicao()) {
                binding.tvAlerta.visibility = android.view.View.VISIBLE
            } else {
                binding.tvAlerta.visibility = android.view.View.GONE
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MaterialViewHolder {
        val binding = ItemMaterialBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MaterialViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MaterialViewHolder, position: Int) {
        holder.bind(materiais[position])
    }

    override fun getItemCount() = materiais.size
}
