package com.arteemadeira.app.ui.cliente

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.arteemadeira.app.R
import com.arteemadeira.app.data.model.Cliente
import com.arteemadeira.app.data.repository.ClienteRepository
import com.arteemadeira.app.databinding.ActivityListaClientesBinding
import com.arteemadeira.app.util.gone
import com.arteemadeira.app.util.showToast
import com.arteemadeira.app.util.visible
import kotlinx.coroutines.launch

class ListaClientesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityListaClientesBinding
    private lateinit var adapter: ClienteAdapter
    private lateinit var repository: ClienteRepository
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

        repository = ClienteRepository()

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
        lifecycleScope.launch {
            repository.buscarClientes()
                .onSuccess { listaClientes ->
                    clientes.clear()
                    clientes.addAll(listaClientes)
                    adapter.notifyDataSetChanged()

                    if (clientes.isEmpty()) {
                        binding.rvClientes.gone()
                        binding.tvListaVazia.visible()
                    } else {
                        binding.rvClientes.visible()
                        binding.tvListaVazia.gone()
                    }
                }
                .onFailure { exception ->
                    showToast("Erro ao carregar clientes: ${exception.message}")
                }
        }
    }

    private fun editarCliente(cliente: Cliente) {
        val intent = Intent(this, CadastroClienteActivity::class.java)
        intent.putExtra("cliente", cliente)
        cadastroLauncher.launch(intent)
    }

    private fun confirmarExclusao(cliente: Cliente) {
        AlertDialog.Builder(this)
            .setTitle("Confirmar Exclusão")
            .setMessage("Deseja realmente excluir ${cliente.nome}?")
            .setPositiveButton("Sim") { _, _ -> excluirCliente(cliente) }
            .setNegativeButton("Não", null)
            .show()
    }

    private fun excluirCliente(cliente: Cliente) {
        lifecycleScope.launch {
            repository.excluirCliente(cliente.id)
                .onSuccess {
                    showToast("Cliente excluído com sucesso")
                    carregarClientes()
                }
                .onFailure { exception ->
                    showToast("Erro ao excluir cliente: ${exception.message}")
                }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_lista, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_refresh -> {
                carregarClientes()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
