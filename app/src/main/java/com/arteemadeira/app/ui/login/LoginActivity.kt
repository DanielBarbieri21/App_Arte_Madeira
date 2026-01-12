package com.arteemadeira.app.ui.login

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.arteemadeira.app.databinding.ActivityLoginBinding
import com.arteemadeira.app.ui.main.MainActivity
import com.arteemadeira.app.util.gone
import com.arteemadeira.app.util.showToast
import com.arteemadeira.app.util.visible
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        // Verificar se usuário já está logado
        if (auth.currentUser != null) {
            navegarParaMain()
            return
        }

        setupListeners()
    }

    private fun setupListeners() {
        binding.btnEntrar.setOnClickListener {
            val email = binding.etEmail.text.toString().trim()
            val senha = binding.etSenha.text.toString().trim()

            if (validarCampos(email, senha)) {
                realizarLogin(email, senha)
            }
        }
    }

    private fun validarCampos(email: String, senha: String): Boolean {
        if (email.isEmpty() || senha.isEmpty()) {
            showToast("Preencha todos os campos")
            return false
        }

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.tilEmail.error = "E-mail inválido"
            return false
        }

        binding.tilEmail.error = null
        return true
    }

    private fun realizarLogin(email: String, senha: String) {
        binding.progressBar.visible()
        binding.btnEntrar.isEnabled = false

        // Login mock - aceita qualquer email/senha
        binding.progressBar.postDelayed({
            binding.progressBar.gone()
            showToast("Login realizado com sucesso!")
            navegarParaMain()
        }, 500)
    }

    private fun navegarParaMain() {
        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        finish()
    }
}
