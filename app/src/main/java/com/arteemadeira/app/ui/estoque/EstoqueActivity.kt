package com.arteemadeira.app.ui.estoque

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.arteemadeira.app.R
import com.arteemadeira.app.data.model.Material
import com.arteemadeira.app.data.repository.MaterialRepository
import com.arteemadeira.app.databinding.ActivityListaClientesBinding
import com.arteemadeira.app.util.gone
import com.arteemadeira.app.util.showToast
import com.arteemadeira.app.util.visible
import kotlinx.coroutines.launch

class EstoqueActivity : AppCompatActivity() {

    private lateinit var binding: ActivityListaClientesBinding
    private lateinit var adapter: MaterialAdapter
    private lateinit var repository: MaterialRepository
    private var materiais = mutableListOf<Material>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListaClientesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        repository = MaterialRepository()

        setupToolbar()
        setupRecyclerView()
        setupListeners()
        carregarMateriais()
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = getString(R.string.title_estoque)
    }

    private fun setupRecyclerView() {
        adapter = MaterialAdapter(materiais)
        binding.rvClientes.layoutManager = LinearLayoutManager(this)
        binding.rvClientes.adapter = adapter
    }

    private fun setupListeners() {
        binding.fabNovo.setOnClickListener {
            showToast("Adicionar material - Em desenvolvimento")
        }

        binding.tilBusca.gone()
    }

    private fun carregarMateriais() {
        lifecycleScope.launch {
            repository.buscarMateriais()
                .onSuccess { listaMateriais ->
                    materiais.clear()
                    materiais.addAll(listaMateriais)
                    adapter.notifyDataSetChanged()

                    if (materiais.isEmpty()) {
                        binding.rvClientes.gone()
                        binding.tvListaVazia.visible()
                    } else {
                        binding.rvClientes.visible()
                        binding.tvListaVazia.gone()
                    }
                }
                .onFailure { exception ->
                    showToast("Erro ao carregar materiais: ${exception.message}")
                }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}
