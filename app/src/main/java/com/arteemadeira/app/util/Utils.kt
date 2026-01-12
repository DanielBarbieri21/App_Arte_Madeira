package com.arteemadeira.app.util

import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*

object DateUtils {
    
    private val dateFormat = SimpleDateFormat(Constants.DATE_FORMAT, Locale("pt", "BR"))
    private val dateTimeFormat = SimpleDateFormat(Constants.DATE_TIME_FORMAT, Locale("pt", "BR"))
    
    fun formatDate(timestamp: Long): String {
        return if (timestamp > 0) {
            dateFormat.format(Date(timestamp))
        } else {
            "-"
        }
    }
    
    fun formatDateTime(timestamp: Long): String {
        return if (timestamp > 0) {
            dateTimeFormat.format(Date(timestamp))
        } else {
            "-"
        }
    }
    
    fun parseDate(dateString: String): Long {
        return try {
            dateFormat.parse(dateString)?.time ?: 0L
        } catch (e: Exception) {
            0L
        }
    }
}

object CurrencyUtils {
    
    private val currencyFormat = NumberFormat.getCurrencyInstance(Locale("pt", "BR"))
    
    fun format(value: Double): String {
        return currencyFormat.format(value)
    }
    
    fun parse(valueString: String): Double {
        return try {
            val cleanString = valueString.replace("R$", "")
                .replace(".", "")
                .replace(",", ".")
                .trim()
            cleanString.toDoubleOrNull() ?: 0.0
        } catch (e: Exception) {
            0.0
        }
    }
}

object ValidationUtils {
    
    fun isValidEmail(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
    
    fun isValidPhone(phone: String): Boolean {
        val cleanPhone = phone.replace(Regex("[^0-9]"), "")
        return cleanPhone.length in 10..11
    }
    
    fun formatPhone(phone: String): String {
        val cleanPhone = phone.replace(Regex("[^0-9]"), "")
        return when (cleanPhone.length) {
            10 -> "(${cleanPhone.substring(0, 2)}) ${cleanPhone.substring(2, 6)}-${cleanPhone.substring(6)}"
            11 -> "(${cleanPhone.substring(0, 2)}) ${cleanPhone.substring(2, 7)}-${cleanPhone.substring(7)}"
            else -> phone
        }
    }
}
