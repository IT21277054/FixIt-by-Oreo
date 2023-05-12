package com.example.workerside

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
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
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.example.workerside", appContext.packageName)
    }

    @get:Rule
    val activityScenarioRule = ActivityScenarioRule(workerMainMenu::class.java)

    private lateinit var activityScenario : ActivityScenario<workerMainMenu>

    @Before
    fun prepare(){
        activityScenario = activityScenarioRule.scenario
    }

    @Test
    fun searchJobImageView(){
        onView(withId(R.id.jobsCardWorker)).perform(click())
    }

    @Test
    fun onGoingImageView(){
        onView(withId(R.id.onGoingCardWorker)).perform(click())
    }

    @Test
    fun completeImageView(){
        onView(withId(R.id.completedCardWorker)).perform(click())
    }

    @Test
    fun profileImageView(){
        onView(withId(R.id.profileCardWorker)).perform(click())
    }

    @Test
    fun notesImageView(){
        onView(withId(R.id.notesCardWorker)).perform(click())
    }

    @After
    fun clear(){
        activityScenario.close()
    }

}