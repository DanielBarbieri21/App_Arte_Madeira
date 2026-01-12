package com.arteemadeira.app.data.repository

import com.arteemadeira.app.data.model.Produto
import com.arteemadeira.app.util.Constants
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.coroutines.tasks.await

class ProdutoRepository {
    
    private val db = FirebaseFirestore.getInstance()
    private val collection = db.collection(Constants.COLLECTION_PRODUTOS)
    
    suspend fun salvarProduto(produto: Produto): Result<String> {
        return try {
            if (produto.id.isEmpty()) {
                val docRef = collection.add(produto.toMap()).await()
                Result.success(docRef.id)
            } else {
                collection.document(produto.id).set(produto.toMap()).await()
                Result.success(produto.id)
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    suspend fun buscarProdutos(): Result<List<Produto>> {
        return try {
            val snapshot = collection
                .whereEqualTo("ativo", true)
                .orderBy("nome", Query.Direction.ASCENDING)
                .get()
                .await()
            
            val produtos = snapshot.documents.mapNotNull { doc ->
                doc.toObject(Produto::class.java)?.copy(id = doc.id)
            }
            Result.success(produtos)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    suspend fun buscarProdutosPorCategoria(categoria: String): Result<List<Produto>> {
        return try {
            val snapshot = collection
                .whereEqualTo("ativo", true)
                .whereEqualTo("categoria", categoria)
                .orderBy("nome", Query.Direction.ASCENDING)
                .get()
                .await()
            
            val produtos = snapshot.documents.mapNotNull { doc ->
                doc.toObject(Produto::class.java)?.copy(id = doc.id)
            }
            Result.success(produtos)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    suspend fun excluirProduto(id: String): Result<Unit> {
        return try {
            collection.document(id).update("ativo", false).await()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
