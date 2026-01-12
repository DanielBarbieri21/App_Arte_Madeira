package com.arteemadeira.app.ui.pedido

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.arteemadeira.app.R
import com.arteemadeira.app.data.model.Pedido
import com.arteemadeira.app.data.repository.PedidoRepository
import com.arteemadeira.app.databinding.ActivityListaClientesBinding
import com.arteemadeira.app.util.gone
import com.arteemadeira.app.util.showToast
import com.arteemadeira.app.util.visible
import kotlinx.coroutines.launch

class ListaPedidosActivity : AppCompatActivity() {

    private lateinit var binding: ActivityListaClientesBinding
    private lateinit var adapter: PedidoAdapter
    private lateinit var repository: PedidoRepository
    private var pedidos = mutableListOf<Pedido>()

    private val cadastroLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            carregarPedidos()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListaClientesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        repository = PedidoRepository()

        setupToolbar()
        setupRecyclerView()
        setupListeners()
        carregarPedidos()
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = getString(R.string.title_pedidos)
    }

    private fun setupRecyclerView() {
        adapter = PedidoAdapter(
            pedidos = pedidos,
            onEditClick = { pedido -> editarPedido(pedido) },
            onDeleteClick = { pedido -> confirmarExclusao(pedido) }
        )
        binding.rvClientes.layoutManager = LinearLayoutManager(this)
        binding.rvClientes.adapter = adapter
    }

    private fun setupListeners() {
        binding.fabNovo.setOnClickListener {
            val intent = Intent(this, CadastroPedidoActivity::class.java)
            cadastroLauncher.launch(intent)
        }

        binding.tilBusca.gone()
    }

    private fun carregarPedidos() {
        lifecycleScope.launch {
            repository.buscarPedidos()
                .onSuccess { listaPedidos ->
                    pedidos.clear()
                    pedidos.addAll(listaPedidos)
                    adapter.notifyDataSetChanged()

                    if (pedidos.isEmpty()) {
                        binding.rvClientes.gone()
                        binding.tvListaVazia.visible()
                    } else {
                        binding.rvClientes.visible()
                        binding.tvListaVazia.gone()
                    }
                }
                .onFailure { exception ->
                    showToast("Erro ao carregar pedidos: ${exception.message}")
                }
        }
    }

    private fun editarPedido(pedido: Pedido) {
        val intent = Intent(this, CadastroPedidoActivity::class.java)
        intent.putExtra("pedido", pedido)
        cadastroLauncher.launch(intent)
    }

    private fun confirmarExclusao(pedido: Pedido) {
        AlertDialog.Builder(this)
            .setTitle("Confirmar Exclusão")
            .setMessage("Deseja realmente excluir este pedido?")
            .setPositiveButton("Sim") { _, _ -> excluirPedido(pedido) }
            .setNegativeButton("Não", null)
            .show()
    }

    private fun excluirPedido(pedido: Pedido) {
        lifecycleScope.launch {
            repository.excluirPedido(pedido.id)
                .onSuccess {
                    showToast("Pedido excluído com sucesso")
                    carregarPedidos()
                }
                .onFailure { exception ->
                    showToast("Erro ao excluir pedido: ${exception.message}")
                }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}
