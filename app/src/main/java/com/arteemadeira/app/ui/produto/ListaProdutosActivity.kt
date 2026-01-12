package com.arteemadeira.app.ui.produto

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.arteemadeira.app.R
import com.arteemadeira.app.databinding.ActivityListaClientesBinding
import com.arteemadeira.app.util.showToast

class ListaProdutosActivity : AppCompatActivity() {

    private lateinit var binding: ActivityListaClientesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListaClientesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupToolbar()
        showToast("Tela de Produtos - Em desenvolvimento")
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = getString(R.string.title_produtos)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}
