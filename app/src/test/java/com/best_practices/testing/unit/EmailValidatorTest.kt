package com.best_practices.testing.unit

import com.best_practices.testing.EmailValidator
import org.junit.Assert
import org.junit.Test


internal class EmailValidatorTest {

    @Test
    fun emailValidator_CorrectEmailSimple_ReturnsTrue() {
        Assert.assertTrue(EmailValidator.validatedEmail("sampleemail"))
    }
}