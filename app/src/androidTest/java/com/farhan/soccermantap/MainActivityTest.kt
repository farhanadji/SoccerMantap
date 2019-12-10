package com.farhan.soccermantap

import android.view.KeyEvent
import android.widget.EditText
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {
    @Rule
    @JvmField var activityRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun searchEventTest(){
        Espresso.onView(ViewMatchers.withId(R.id.nav_search))
            .check(matches(ViewMatchers.isDisplayed()))
        Thread.sleep(1000)

        Espresso.onView(ViewMatchers.withId(R.id.nav_search))
            .perform(click())
        Thread.sleep(1000)

        Espresso.onView(ViewMatchers.withId(R.id.searchMatch))
            .check(matches(ViewMatchers.isDisplayed()))

        Espresso.onView(ViewMatchers.withId(R.id.searchMatch))
            .perform(click())
        Thread.sleep(2000)

        Espresso.onView(ViewMatchers.isAssignableFrom(EditText::class.java)).perform(
            typeText("Arsenal"),
            pressKey(KeyEvent.KEYCODE_ENTER)
        )
        Thread.sleep(8000)

        Espresso.onView(ViewMatchers.withId(R.id.searchRV))
            .check(matches(ViewMatchers.isDisplayed()))
        Thread.sleep(8000)


        Espresso.onView(ViewMatchers.withId(R.id.searchRV))
            .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        Thread.sleep(5000)

        Espresso.onView(ViewMatchers.withId(R.id.awayTeamBadge))
            .check(matches(ViewMatchers.isDisplayed()))
        Thread.sleep(1000)



    }

}