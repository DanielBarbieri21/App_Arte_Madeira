package com.arteemadeira.app.ui.pedido

import android.app.Activity
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.arteemadeira.app.R
import com.arteemadeira.app.data.model.Pedido
import com.arteemadeira.app.data.repository.PedidoRepository
import com.arteemadeira.app.databinding.ActivityCadastroPedidoBinding
import com.arteemadeira.app.util.showToast
import kotlinx.coroutines.launch

class CadastroPedidoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCadastroPedidoBinding
    private lateinit var repository: PedidoRepository
    private var pedidoEdicao: Pedido? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCadastroPedidoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        repository = PedidoRepository()

        setupToolbar()
        carregarDadosEdicao()
        setupListeners()
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = getString(R.string.title_cadastro_pedido)
    }

    private fun carregarDadosEdicao() {
        pedidoEdicao = intent.getParcelableExtra("pedido")
        pedidoEdicao?.let { pedido ->
            binding.etDescricaoMovel.setText(pedido.descricaoMovel)
            binding.etValorEstimado.setText(pedido.valorEstimado.toString())
            binding.etObservacoes.setText(pedido.observacoes)
        }
    }

    private fun setupListeners() {
        binding.btnSalvar.setOnClickListener {
            salvarPedido()
        }

        binding.btnCancelar.setOnClickListener {
            finish()
        }
    }

    private fun salvarPedido() {
        // Implementação simplificada
        val descricao = binding.etDescricaoMovel.text.toString()
        val valorStr = binding.etValorEstimado.text.toString()

        if (descricao.isEmpty() || valorStr.isEmpty()) {
            showToast("Preencha todos os campos obrigatórios")
            return
        }

        showToast("Funcionalidade completa disponível na versão final")
        finish()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}
