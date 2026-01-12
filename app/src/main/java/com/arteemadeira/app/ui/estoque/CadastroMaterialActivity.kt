package com.arteemadeira.app.ui.estoque

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.arteemadeira.app.R
import com.arteemadeira.app.databinding.ActivityCadastroClienteBinding

class CadastroMaterialActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCadastroClienteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCadastroClienteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupToolbar()
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = getString(R.string.title_cadastro_material)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}
