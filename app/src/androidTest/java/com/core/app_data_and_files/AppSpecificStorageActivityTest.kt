package com.core.app_data_and_files

import android.content.Intent
import android.view.View
import android.widget.TextView
import androidx.test.espresso.Espresso
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.heshan.androidcore.CoreTopicViewHolder
import com.heshan.androidcore.R
import org.hamcrest.Matcher
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
internal class AppSpecificStorageActivityTest {

    @Before
    fun setUp() {
        var firstActivity: IntentsTestRule<AppSpecificStorageActivity> = IntentsTestRule(AppSpecificStorageActivity::class.java)
        firstActivity.launchActivity(Intent())
    }

    @Test
    fun checkIfInternalFolderCreatedRowTapped() {
        Espresso.onView(ViewMatchers.withId(R.id.main_recycle_view))
            .perform(RecyclerViewActions.actionOnItemAtPosition<CoreTopicViewHolder>(2, object :
                ViewAction {
                override fun getConstraints(): Matcher<View> {
                    return ViewMatchers.isAssignableFrom(View::class.java)
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


