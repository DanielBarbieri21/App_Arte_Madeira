package com.arteemadeira.app.data.repository

import com.arteemadeira.app.data.model.Cliente
import com.arteemadeira.app.util.Constants
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.coroutines.tasks.await

class ClienteRepository {
    
    // Dados mock em memória para teste
    private val clientesMock = mutableListOf<Cliente>()
    
    private val db = FirebaseFirestore.getInstance()
    private val collection = db.collection(Constants.COLLECTION_CLIENTES)
    
    suspend fun salvarCliente(cliente: Cliente): Result<String> {
        return try {
            if (cliente.id.isEmpty()) {
                // Novo cliente
                val docRef = collection.add(cliente.toMap()).await()
                Result.success(docRef.id)
            } else {
                // Atualizar cliente existente
                collection.document(cliente.id).set(cliente.toMap()).await()
                Result.success(cliente.id)
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    suspend fun buscarClientes(): Result<List<Cliente>> {
        return try {
            // Modo mock - dados de exemplo
            if (clientesMock.isEmpty()) {
                clientesMock.addAll(listOf(
                    Cliente(id = "1", nome = "João Silva", telefone = "11987654321", 
                           email = "joao@email.com", endereco = "Rua A, 123"),
                    Cliente(id = "2", nome = "Maria Santos", telefone = "11976543210",
                           email = "maria@email.com", endereco = "Av. B, 456"),
                    Cliente(id = "3", nome = "Pedro Costa", telefone = "11965432109",
                           email = "pedro@email.com", endereco = "Praça C, 789")
                ))
            }
            Result.success(clientesMock.filter { it.ativo }.sortedBy { it.nome })
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    suspend fun buscarClientePorId(id: String): Result<Cliente> {
        return try {
            val doc = collection.document(id).get().await()
            val cliente = doc.toObject(Cliente::class.java)?.copy(id = doc.id)
            if (cliente != null) {
                Result.success(cliente)
            } else {
                Result.failure(Exception("Cliente não encontrado"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    suspend fun excluirCliente(id: String): Result<Unit> {
        return try {
            // Soft delete - apenas marca como inativo
            collection.document(id).update("ativo", false).await()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    suspend fun buscarClientesPorNome(nome: String): Result<List<Cliente>> {
        return try {
            val snapshot = collection
                .whereEqualTo("ativo", true)
                .orderBy("nome")
                .startAt(nome)
                .endAt(nome + "\uf8ff")
                .get()
                .await()
            
            val clientes = snapshot.documents.mapNotNull { doc ->
                doc.toObject(Cliente::class.java)?.copy(id = doc.id)
            }
            Result.success(clientes)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
