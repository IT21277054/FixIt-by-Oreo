package com.example.finalproject

import androidx.test.espresso.action.ViewActions.click
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
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
class CompleteAdapterInstrumentalTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.example.finalproject", appContext.packageName)
    }
    @get:Rule
    val activityScenarioRule = ActivityScenarioRule(MapsActivity::class.java)

    private lateinit var activityScenario : ActivityScenario<MapsActivity>

    @Before
    fun prepare(){
        activityScenario = activityScenarioRule.scenario
    }

    @Test
    fun backButton(){
        onView(withId(R.id.onGoingBackBtn)).perform(click())
    }

    @Test
    fun getWorker(){
        onView(withId(R.id.workerbtn)).perform(click())
    }

    @After
    fun clear(){
        activityScenario.close()
    }


}