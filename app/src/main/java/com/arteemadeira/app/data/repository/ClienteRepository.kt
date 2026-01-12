package com.arteemadeira.app.data.repository

import com.arteemadeira.app.data.model.Cliente
import com.arteemadeira.app.util.Constants
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.coroutines.tasks.await

class ClienteRepository {
    
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
            val snapshot = collection
                .whereEqualTo("ativo", true)
                .orderBy("nome", Query.Direction.ASCENDING)
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
