package com.arteemadeira.app.data.repository

import com.arteemadeira.app.data.model.Material
import com.arteemadeira.app.util.Constants
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.coroutines.tasks.await

class MaterialRepository {
    
    private val db = FirebaseFirestore.getInstance()
    private val collection = db.collection(Constants.COLLECTION_MATERIAIS)
    
    suspend fun salvarMaterial(material: Material): Result<String> {
        return try {
            if (material.id.isEmpty()) {
                val docRef = collection.add(material.toMap()).await()
                Result.success(docRef.id)
            } else {
                collection.document(material.id).set(material.toMap()).await()
                Result.success(material.id)
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    suspend fun buscarMateriais(): Result<List<Material>> {
        return try {
            val snapshot = collection
                .whereEqualTo("ativo", true)
                .orderBy("nome", Query.Direction.ASCENDING)
                .get()
                .await()
            
            val materiais = snapshot.documents.mapNotNull { doc ->
                doc.toObject(Material::class.java)?.copy(id = doc.id)
            }
            Result.success(materiais)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    suspend fun buscarMateriaisParaReposicao(): Result<List<Material>> {
        return try {
            val materiais = buscarMateriais().getOrNull() ?: emptyList()
            val materiaisReposicao = materiais.filter { it.precisaReposicao() }
            Result.success(materiaisReposicao)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    suspend fun atualizarQuantidade(materialId: String, novaQuantidade: Double): Result<Unit> {
        return try {
            collection.document(materialId).update("quantidadeEstoque", novaQuantidade).await()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    suspend fun excluirMaterial(id: String): Result<Unit> {
        return try {
            collection.document(id).update("ativo", false).await()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
