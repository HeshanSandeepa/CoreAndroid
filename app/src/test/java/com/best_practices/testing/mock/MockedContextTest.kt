package com.best_practices.testing.mock


import android.content.Context
import com.best_practices.testing.MockUnderTest
import com.heshan.androidcore.R
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock


private const val FAKE_STRING = "HELLO WORLD"

@RunWith(MockitoJUnitRunner::class)
class MockedContextTest {

    @Mock
    private lateinit var mockContext: Context

    @Test
    fun readStringFromContext_LocalizedString() {
        // Given a mocked Context injected into the object under test...
        mockContext = mock<Context> {
            on { getString(R.string.app_name) } doReturn ""
        }

        val myObjectUnderTest = MockUnderTest(mockContext)

        // ...when the string is returned from the object under test...
        val result = myObjectUnderTest.getMockName()

        // ...then the result should be the expected one.
        assertEquals(result, FAKE_STRING)
    }
}