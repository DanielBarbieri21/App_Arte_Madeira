package com.arteemadeira.app.ui.cliente

import android.app.Activity
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.arteemadeira.app.R
import com.arteemadeira.app.data.model.Cliente
import com.arteemadeira.app.data.repository.ClienteRepository
import com.arteemadeira.app.databinding.ActivityCadastroClienteBinding
import com.arteemadeira.app.util.ValidationUtils
import com.arteemadeira.app.util.showToast
import kotlinx.coroutines.launch

class CadastroClienteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCadastroClienteBinding
    private lateinit var repository: ClienteRepository
    private var clienteEdicao: Cliente? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCadastroClienteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        repository = ClienteRepository()

        setupToolbar()
        carregarDadosEdicao()
        setupListeners()
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val titulo = if (clienteEdicao != null) {
            "Editar Cliente"
        } else {
            getString(R.string.title_cadastro_cliente)
        }
        supportActionBar?.title = titulo
    }

    private fun carregarDadosEdicao() {
        clienteEdicao = intent.getParcelableExtra("cliente")
        clienteEdicao?.let { cliente ->
            binding.etNome.setText(cliente.nome)
            binding.etTelefone.setText(cliente.telefone)
            binding.etEmail.setText(cliente.email)
            binding.etEndereco.setText(cliente.endereco)
        }
    }

    private fun setupListeners() {
        binding.btnSalvar.setOnClickListener {
            salvarCliente()
        }

        binding.btnCancelar.setOnClickListener {
            finish()
        }
    }

    private fun validarCampos(): Boolean {
        val nome = binding.etNome.text.toString().trim()
        val telefone = binding.etTelefone.text.toString().trim()
        val email = binding.etEmail.text.toString().trim()

        if (nome.isEmpty()) {
            binding.tilNome.error = "Nome obrigatório"
            return false
        }
        binding.tilNome.error = null

        if (telefone.isEmpty()) {
            binding.tilTelefone.error = "Telefone obrigatório"
            return false
        }
        if (!ValidationUtils.isValidPhone(telefone)) {
            binding.tilTelefone.error = "Telefone inválido"
            return false
        }
        binding.tilTelefone.error = null

        if (email.isNotEmpty() && !ValidationUtils.isValidEmail(email)) {
            binding.tilEmail.error = "E-mail inválido"
            return false
        }
        binding.tilEmail.error = null

        return true
    }

    private fun salvarCliente() {
        if (!validarCampos()) return

        val cliente = Cliente(
            id = clienteEdicao?.id ?: "",
            nome = binding.etNome.text.toString().trim(),
            telefone = binding.etTelefone.text.toString().trim(),
            email = binding.etEmail.text.toString().trim(),
            endereco = binding.etEndereco.text.toString().trim()
        )

        binding.btnSalvar.isEnabled = false

        lifecycleScope.launch {
            repository.salvarCliente(cliente)
                .onSuccess {
                    showToast("Cliente salvo com sucesso!")
                    setResult(Activity.RESULT_OK)
                    finish()
                }
                .onFailure { exception ->
                    binding.btnSalvar.isEnabled = true
                    showToast("Erro ao salvar: ${exception.message}")
                }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}
