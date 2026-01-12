package com.arteemadeira.app

import android.app.Application
import com.google.firebase.FirebaseApp

class ArteApplication : Application() {
    
    override fun onCreate() {
        super.onCreate()
        
        // Inicializar Firebase
        FirebaseApp.initializeApp(this)
    }
}
