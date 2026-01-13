package com.arteemadeira.app.ui.produto

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.arteemadeira.app.R
import com.arteemadeira.app.data.mock.MockData
import com.arteemadeira.app.data.model.Produto
import com.arteemadeira.app.databinding.ActivityListaClientesBinding
import com.arteemadeira.app.util.showToast

class ListaProdutosActivity : AppCompatActivity() {

    private lateinit var binding: ActivityListaClientesBinding
    private lateinit var adapter: ProdutoAdapter
    private var produtos = mutableListOf<Produto>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListaClientesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupToolbar()
        setupRecyclerView()
        setupListeners()
        carregarProdutos()
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = getString(R.string.title_produtos)
    }

    private fun setupRecyclerView() {
        adapter = ProdutoAdapter(produtos)
        binding.rvClientes.layoutManager = LinearLayoutManager(this)
        binding.rvClientes.adapter = adapter
    }

    private fun setupListeners() {
        binding.fabNovo.setOnClickListener {
            showToast("Adicionar produto - Em desenvolvimento")
        }
    }

    private fun carregarProdutos() {
        produtos.clear()
        produtos.addAll(MockData.produtos)
        adapter.notifyDataSetChanged()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}
