package com.best_practices.testing

class EmailValidator {

        companion object {
        fun validatedEmail(email: String): Boolean {
            return email.length > 5
        }
    }

}