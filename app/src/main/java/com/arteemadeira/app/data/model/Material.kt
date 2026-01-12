package com.arteemadeira.app.data.model

import android.os.Parcelable
import com.google.firebase.firestore.DocumentId
import kotlinx.parcelize.Parcelize

@Parcelize
data class Material(
    @DocumentId
    val id: String = "",
    val nome: String = "",
    val unidade: String = "kg", // kg, m³, unidade, etc.
    val quantidadeEstoque: Double = 0.0,
    val quantidadeMinima: Double = 0.0,
    val valorUnitario: Double = 0.0,
    val fornecedor: String = "",
    val dataUltimaCompra: Long = 0L,
    val ativo: Boolean = true
) : Parcelable {
    
    // Construtor sem argumentos para Firebase
    constructor() : this("", "", "kg", 0.0, 0.0, 0.0, "", 0L, true)
    
    fun precisaReposicao(): Boolean {
        return quantidadeEstoque <= quantidadeMinima
    }
    
    fun toMap(): Map<String, Any> {
        return mapOf(
            "nome" to nome,
            "unidade" to unidade,
            "quantidadeEstoque" to quantidadeEstoque,
            "quantidadeMinima" to quantidadeMinima,
            "valorUnitario" to valorUnitario,
            "fornecedor" to fornecedor,
            "dataUltimaCompra" to dataUltimaCompra,
            "ativo" to ativo
        )
    }
}
