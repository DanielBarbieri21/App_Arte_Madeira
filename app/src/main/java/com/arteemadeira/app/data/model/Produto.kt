package com.arteemadeira.app.data.model

import android.os.Parcelable
import com.google.firebase.firestore.DocumentId
import kotlinx.parcelize.Parcelize

@Parcelize
data class Produto(
    @DocumentId
    val id: String = "",
    val nome: String = "",
    val descricao: String = "",
    val categoria: String = "", // Mesa, Cadeira, Armário, etc.
    val tempoProducaoMedio: Int = 0, // em dias
    val valorBase: Double = 0.0,
    val materiaisPrincipais: List<String> = emptyList(),
    val imagemUrl: String = "",
    val ativo: Boolean = true
) : Parcelable {
    
    // Construtor sem argumentos para Firebase
    constructor() : this("", "", "", "", 0, 0.0, emptyList(), "", true)
    
    fun toMap(): Map<String, Any> {
        return mapOf(
            "nome" to nome,
            "descricao" to descricao,
            "categoria" to categoria,
            "tempoProducaoMedio" to tempoProducaoMedio,
            "valorBase" to valorBase,
            "materiaisPrincipais" to materiaisPrincipais,
            "imagemUrl" to imagemUrl,
            "ativo" to ativo
        )
    }
}
