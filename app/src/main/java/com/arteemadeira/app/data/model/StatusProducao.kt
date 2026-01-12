package com.arteemadeira.app.data.model

enum class StatusProducao(val descricao: String, val cor: Int) {
    PENDENTE("Pendente", 0xFFFF9800.toInt()),
    EM_PRODUCAO("Em Produção", 0xFF2196F3.toInt()),
    CONCLUIDO("Concluído", 0xFF4CAF50.toInt()),
    CANCELADO("Cancelado", 0xFFF44336.toInt());
    
    companion object {
        fun fromString(value: String): StatusProducao {
            return values().find { it.name == value } ?: PENDENTE
        }
    }
}
