package com.example.restaurantapp.helper

import java.util.regex.Pattern

class ValidationUtils {

    companion object {

        fun isValidEmail(email:String) : Boolean{
            if ( email.isEmpty() ) return false
            val emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$"
            val pattern = Pattern.compile(emailRegex)
            return pattern.matcher(email).matches()
        }

        fun isValidPass(pass:String) :Boolean{
            val passwordRegex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[A-Za-z\\d]{8,}$"
            val pattern = Pattern.compile(passwordRegex)
            return pattern.matcher(pass).matches()
        }

    }

}