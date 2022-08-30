package com.best_practices.testing

import android.content.Context
import com.heshan.androidcore.R

class MockUnderTest(context: Context) {

    val context = context

    fun getMockName(): String {
        return context.getString(R.string.app_name)
    }
}