package com.example.finalproject

import androidx.test.espresso.action.ViewActions.click
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule

import org.junit.After

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class DashboardInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.example.finalproject", appContext.packageName)
    }
    @get:Rule
    val activityScenarioRule = ActivityScenarioRule(DashBoard::class.java)

    private lateinit var activityScenario : ActivityScenario<DashBoard>

    @Before
    fun prepare(){
        activityScenario = activityScenarioRule.scenario
    }

    @Test
    fun findworkerButton(){
        onView(withId(R.id.findworker)).perform(click())
    }

    @Test
    fun pendingButton(){
        onView(withId(R.id.pending)).perform(click())
    }

    @Test
    fun completeButton(){
        onView(withId(R.id.complete)).perform(click())
    }

    @Test
    fun inProgressButton(){
        onView(withId(R.id.inprogress)).perform(click())
    }

    @Test
    fun ratingcompleteButton(){
        onView(withId(R.id.rating)).perform(click())
    }

    @After
    fun clear(){
        activityScenario.close()
    }


}