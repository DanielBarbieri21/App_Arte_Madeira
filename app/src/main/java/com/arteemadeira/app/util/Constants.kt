package com.arteemadeira.app.util

object Constants {
    // Firebase Collections
    const val COLLECTION_USUARIOS = "usuarios"
    const val COLLECTION_CLIENTES = "clientes"
    const val COLLECTION_PEDIDOS = "pedidos"
    const val COLLECTION_PRODUTOS = "produtos"
    const val COLLECTION_MATERIAIS = "materiais"
    
    // SharedPreferences
    const val PREFS_NAME = "ArteEmadeiraPrefs"
    const val PREF_USER_ID = "userId"
    const val PREF_USER_EMAIL = "userEmail"
    const val PREF_USER_NOME = "userName"
    
    // Intent Extras
    const val EXTRA_CLIENTE = "extra_cliente"
    const val EXTRA_PEDIDO = "extra_pedido"
    const val EXTRA_PRODUTO = "extra_produto"
    const val EXTRA_MATERIAL = "extra_material"
    const val EXTRA_CLIENTE_ID = "extra_cliente_id"
    
    // Request Codes
    const val REQUEST_CODE_CLIENTE = 1001
    const val REQUEST_CODE_PEDIDO = 1002
    const val REQUEST_CODE_PRODUTO = 1003
    
    // Date Format
    const val DATE_FORMAT = "dd/MM/yyyy"
    const val DATE_TIME_FORMAT = "dd/MM/yyyy HH:mm"
}
