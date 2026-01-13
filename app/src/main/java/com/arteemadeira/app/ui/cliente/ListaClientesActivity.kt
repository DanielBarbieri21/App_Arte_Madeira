package com.arteemadeira.app.ui.cliente

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.arteemadeira.app.R
import com.arteemadeira.app.data.mock.MockData
import com.arteemadeira.app.data.model.Cliente
import com.arteemadeira.app.databinding.ActivityListaClientesBinding
import com.arteemadeira.app.util.showToast

class ListaClientesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityListaClientesBinding
    private lateinit var adapter: ClienteAdapter
    private var clientes = mutableListOf<Cliente>()

    private val cadastroLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            carregarClientes()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListaClientesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupToolbar()
        setupRecyclerView()
        setupListeners()
        carregarClientes()
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = getString(R.string.title_clientes)
    }

    private fun setupRecyclerView() {
        adapter = ClienteAdapter(
            clientes = clientes,
            onEditClick = { cliente -> editarCliente(cliente) },
            onDeleteClick = { cliente -> confirmarExclusao(cliente) }
        )
        binding.rvClientes.layoutManager = LinearLayoutManager(this)
        binding.rvClientes.adapter = adapter
    }

    private fun setupListeners() {
        binding.fabNovo.setOnClickListener {
            val intent = Intent(this, CadastroClienteActivity::class.java)
            cadastroLauncher.launch(intent)
        }
    }

    private fun carregarClientes() {
        clientes.clear()
        clientes.addAll(MockData.clientes)
        adapter.notifyDataSetChanged()
    }

    private fun editarCliente(cliente: Cliente) {
        val intent = Intent(this, CadastroClienteActivity::class.java)
        intent.putExtra("cliente", cliente)
        cadastroLauncher.launch(intent)
    }

    private fun confirmarExclusao(cliente: Cliente) {
        showToast("Cliente ${cliente.nome} removido (Demo)")
        carregarClientes()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}
