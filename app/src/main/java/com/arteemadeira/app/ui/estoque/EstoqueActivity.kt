package com.arteemadeira.app.ui.estoque

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.arteemadeira.app.R
import com.arteemadeira.app.data.mock.MockData
import com.arteemadeira.app.data.model.Material
import com.arteemadeira.app.databinding.ActivityListaClientesBinding
import com.arteemadeira.app.util.showToast

class EstoqueActivity : AppCompatActivity() {

    private lateinit var binding: ActivityListaClientesBinding
    private lateinit var adapter: MaterialAdapter
    private var materiais = mutableListOf<Material>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListaClientesBinding.inflate(layoutInflater)
        setContentView(binding.root)

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
    }

    private fun carregarMateriais() {
        materiais.clear()
        materiais.addAll(MockData.materiais)
        adapter.notifyDataSetChanged()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}
