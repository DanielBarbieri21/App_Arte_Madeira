package com.arteemadeira.app.ui.relatorios

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.arteemadeira.app.R
import com.arteemadeira.app.data.mock.MockData
import com.arteemadeira.app.data.model.StatusProducao
import com.arteemadeira.app.databinding.ActivityListaClientesBinding

class RelatoriosActivity : AppCompatActivity() {

    private lateinit var binding: ActivityListaClientesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListaClientesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupToolbar()
        setupRelatorios()
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = getString(R.string.title_relatorios)
    }

    private fun setupRelatorios() {
        val totalClientes = MockData.clientes.size
        val totalPedidos = MockData.pedidos.size
        val totalProdutos = MockData.produtos.size
        val totalMateriais = MockData.materiais.size
        
        val pedidosEmProducao = MockData.pedidos.count { it.status == StatusProducao.EM_PRODUCAO.name }
        val pedidosConcluidos = MockData.pedidos.count { it.status == StatusProducao.CONCLUIDO.name }
        
        val relatorio = """
            === RELATÓRIO GERAL ===
            
            CLIENTES: $totalClientes
            
            PEDIDOS: $totalPedidos
            - Em Produção: $pedidosEmProducao
            - Concluídos: $pedidosConcluidos
            
            PRODUTOS: $totalProdutos
            
            MATERIAIS: $totalMateriais
            
            Valor Total em Pedidos: R$ ${MockData.pedidos.sumOf { it.valorEstimado }}
        """.trimIndent()
        
        binding.tvListaVazia.text = relatorio
        binding.tvListaVazia.visibility = android.view.View.VISIBLE
        binding.rvClientes.visibility = android.view.View.GONE
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}
