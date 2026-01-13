package com.arteemadeira.app.ui.pedido

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.arteemadeira.app.R
import com.arteemadeira.app.data.mock.MockData
import com.arteemadeira.app.data.model.Pedido
import com.arteemadeira.app.databinding.ActivityListaClientesBinding
import com.arteemadeira.app.util.showToast

class ListaPedidosActivity : AppCompatActivity() {

    private lateinit var binding: ActivityListaClientesBinding
    private lateinit var adapter: PedidoAdapter
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
    }

    private fun carregarPedidos() {
        pedidos.clear()
        pedidos.addAll(MockData.pedidos)
        binding.rvClientes.adapter = adapter
        adapter.notifyDataSetChanged()
    }

    private fun editarPedido(pedido: Pedido) {
        val intent = Intent(this, CadastroPedidoActivity::class.java)
        intent.putExtra("pedido", pedido)
        cadastroLauncher.launch(intent)
    }

    private fun confirmarExclusao(pedido: Pedido) {
        showToast("Pedido ${pedido.id} removido (Demo)")
        carregarPedidos()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}
