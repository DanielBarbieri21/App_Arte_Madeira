package com.arteemadeira.app.data.model

import android.os.Parcelable
import com.google.firebase.firestore.DocumentId
import kotlinx.parcelize.Parcelize

@Parcelize
data class Pedido(
    @DocumentId
    val id: String = "",
    val clienteId: String = "",
    val clienteNome: String = "",
    val descricaoMovel: String = "",
    val valorEstimado: Double = 0.0,
    val prazoEntrega: Long = 0L,
    val dataPedido: Long = System.currentTimeMillis(),
    val status: String = StatusProducao.PENDENTE.name,
    val observacoes: String = "",
    val materiaisUtilizados: List<String> = emptyList()
) : Parcelable {
    
    // Construtor sem argumentos para Firebase
    constructor() : this("", "", "", "", 0.0, 0L, System.currentTimeMillis(), StatusProducao.PENDENTE.name, "", emptyList())
    
    fun getStatusProducao(): StatusProducao {
        return StatusProducao.fromString(status)
    }
    
    fun toMap(): Map<String, Any> {
        return mapOf(
            "clienteId" to clienteId,
            "clienteNome" to clienteNome,
            "descricaoMovel" to descricaoMovel,
            "valorEstimado" to valorEstimado,
            "prazoEntrega" to prazoEntrega,
            "dataPedido" to dataPedido,
            "status" to status,
            "observacoes" to observacoes,
            "materiaisUtilizados" to materiaisUtilizados
        )
    }
}
