package com.arteemadeira.app.data.model

import android.os.Parcelable
import com.google.firebase.firestore.DocumentId
import kotlinx.parcelize.Parcelize

@Parcelize
data class Cliente(
    @DocumentId
    val id: String = "",
    val nome: String = "",
    val telefone: String = "",
    val email: String = "",
    val endereco: String = "",
    val dataCadastro: Long = System.currentTimeMillis(),
    val ativo: Boolean = true
) : Parcelable {
    
    // Construtor sem argumentos para Firebase
    constructor() : this("", "", "", "", "", System.currentTimeMillis(), true)
    
    fun toMap(): Map<String, Any> {
        return mapOf(
            "nome" to nome,
            "telefone" to telefone,
            "email" to email,
            "endereco" to endereco,
            "dataCadastro" to dataCadastro,
            "ativo" to ativo
        )
    }
}
