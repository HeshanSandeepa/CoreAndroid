package com.best_practices.testing

import android.content.Intent
import android.view.View
import android.widget.TextView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.heshan.androidcore.CoreTopicViewHolder
import com.heshan.androidcore.MainActivity
import com.heshan.androidcore.R
import org.hamcrest.Matcher
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
internal class MainActivityTest {

    @Before
    fun setUp() {
        var firstActivity: IntentsTestRule<MainActivity> = IntentsTestRule(MainActivity::class.java)
        firstActivity.launchActivity(Intent())
    }

    @After
    fun tearDown() {
    }


    @Test
    fun clickForAddData() {
        onView(withId(R.id.main_recycle_view))
            .perform(RecyclerViewActions.actionOnItemAtPosition<CoreTopicViewHolder>(0, object : ViewAction {
            override fun getConstraints(): Matcher<View> {
                return isAssignableFrom(View::class.java)
            }

            override fun getDescription(): String {
                return "Testing On App Data & Files Click"
            }

            override fun perform(uiController: UiController?, view: View?) {
                val rvView = view?.findViewById<TextView>(R.id.title)
                rvView?.performClick()
            }

        }))
    }
}