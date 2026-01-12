package com.arteemadeira.app.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.arteemadeira.app.databinding.ActivityMainBinding
import com.arteemadeira.app.ui.cliente.ListaClientesActivity
import com.arteemadeira.app.ui.estoque.EstoqueActivity
import com.arteemadeira.app.ui.pedido.ListaPedidosActivity
import com.arteemadeira.app.ui.produto.ListaProdutosActivity
import com.arteemadeira.app.ui.relatorios.RelatoriosActivity
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        setupToolbar()
        setupClickListeners()
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar)
    }

    private fun setupClickListeners() {
        binding.cardClientes.setOnClickListener {
            startActivity(Intent(this, ListaClientesActivity::class.java))
        }

        binding.cardPedidos.setOnClickListener {
            startActivity(Intent(this, ListaPedidosActivity::class.java))
        }

        binding.cardProdutos.setOnClickListener {
            startActivity(Intent(this, ListaProdutosActivity::class.java))
        }

        binding.cardEstoque.setOnClickListener {
            startActivity(Intent(this, EstoqueActivity::class.java))
        }

        binding.cardRelatorios.setOnClickListener {
            startActivity(Intent(this, RelatoriosActivity::class.java))
        }
    }
}
