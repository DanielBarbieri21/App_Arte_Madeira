package com.arteemadeira.app.data.repository

import com.arteemadeira.app.data.model.Pedido
import com.arteemadeira.app.data.model.StatusProducao
import com.arteemadeira.app.util.Constants
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.coroutines.tasks.await

class PedidoRepository {
    
    private val db = FirebaseFirestore.getInstance()
    private val collection = db.collection(Constants.COLLECTION_PEDIDOS)
    
    suspend fun salvarPedido(pedido: Pedido): Result<String> {
        return try {
            if (pedido.id.isEmpty()) {
                val docRef = collection.add(pedido.toMap()).await()
                Result.success(docRef.id)
            } else {
                collection.document(pedido.id).set(pedido.toMap()).await()
                Result.success(pedido.id)
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    suspend fun buscarPedidos(): Result<List<Pedido>> {
        return try {
            val snapshot = collection
                .orderBy("dataPedido", Query.Direction.DESCENDING)
                .get()
                .await()
            
            val pedidos = snapshot.documents.mapNotNull { doc ->
                doc.toObject(Pedido::class.java)?.copy(id = doc.id)
            }
            Result.success(pedidos)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    suspend fun buscarPedidosPorCliente(clienteId: String): Result<List<Pedido>> {
        return try {
            val snapshot = collection
                .whereEqualTo("clienteId", clienteId)
                .orderBy("dataPedido", Query.Direction.DESCENDING)
                .get()
                .await()
            
            val pedidos = snapshot.documents.mapNotNull { doc ->
                doc.toObject(Pedido::class.java)?.copy(id = doc.id)
            }
            Result.success(pedidos)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    suspend fun buscarPedidosPorStatus(status: StatusProducao): Result<List<Pedido>> {
        return try {
            val snapshot = collection
                .whereEqualTo("status", status.name)
                .orderBy("dataPedido", Query.Direction.DESCENDING)
                .get()
                .await()
            
            val pedidos = snapshot.documents.mapNotNull { doc ->
                doc.toObject(Pedido::class.java)?.copy(id = doc.id)
            }
            Result.success(pedidos)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    suspend fun atualizarStatusPedido(pedidoId: String, novoStatus: StatusProducao): Result<Unit> {
        return try {
            collection.document(pedidoId).update("status", novoStatus.name).await()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    suspend fun excluirPedido(id: String): Result<Unit> {
        return try {
            collection.document(id).delete().await()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
