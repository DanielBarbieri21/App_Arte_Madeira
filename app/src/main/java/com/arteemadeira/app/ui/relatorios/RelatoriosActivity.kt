package com.arteemadeira.app.ui.relatorios

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.arteemadeira.app.R
import com.arteemadeira.app.databinding.ActivityListaClientesBinding
import com.arteemadeira.app.util.showToast

class RelatoriosActivity : AppCompatActivity() {

    private lateinit var binding: ActivityListaClientesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListaClientesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupToolbar()
        showToast("Relatórios - Em desenvolvimento")
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = getString(R.string.title_relatorios)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}
