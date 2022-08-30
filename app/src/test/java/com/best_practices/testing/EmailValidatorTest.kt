package com.best_practices.testing

import org.junit.Assert
import org.junit.Test


internal class EmailValidatorTest {

    @Test
    fun validatedEmail() {
        Assert.assertTrue(EmailValidator.validatedEmail("sampleemail"))
    }
}