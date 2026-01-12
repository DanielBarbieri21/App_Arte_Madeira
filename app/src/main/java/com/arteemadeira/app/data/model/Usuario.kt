package com.arteemadeira.app.data.model

data class Usuario(
    val id: String = "",
    val email: String = "",
    val nome: String = "",
    val cargo: String = "",
    val dataCadastro: Long = System.currentTimeMillis()
) {
    
    fun toMap(): Map<String, Any> {
        return mapOf(
            "email" to email,
            "nome" to nome,
            "cargo" to cargo,
            "dataCadastro" to dataCadastro
        )
    }
}
