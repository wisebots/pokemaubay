package com.aubay.pokemon.home.ui

import androidx.arch.core.executor.testing.CountingTaskExecutorRule
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import com.aubay.pokemon.home.R
import com.aubay.pokemon.home.base.BaseAppTest
import com.aubay.pokemon.home.di.configureAppComponent
import com.aubay.pokemon.home.di.interactorModuleTest
import com.aubay.pokemon.home.utils.hasItemCount
import com.aubay.pokemon.home.utils.waitForAdapterChangeWithPagination
import com.aubay.pokemon.home.view.activities.HomeActivity
import org.hamcrest.CoreMatchers
import org.jetbrains.anko.find
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.loadKoinModules
import java.net.HttpURLConnection

@RunWith(AndroidJUnit4::class)
@LargeTest
class HomeActivityTest: BaseAppTest() {

    @Rule
    @JvmField
    val activityRule = ActivityTestRule(HomeActivity::class.java, true, false)

    @get:Rule
    var executorRule = CountingTaskExecutorRule()

    // OVERRIDE ---
    override fun isMockServerEnabled() = true

    @Before
    override fun setUp() {
        super.setUp()
        configureCustomDependencies()
        activityRule.launchActivity(null)
    }

    // TESTS ---
    @Test
    fun whenUserSearchUsersAndSucceed() {
        mockHttpResponse("list_pokemon.json", HttpURLConnection.HTTP_OK)

        onView(withId(R.id.rvPokeList)).check(ViewAssertions.matches((hasItemCount(1))))
        waitForAdapterChangeWithPagination(getRecyclerView(), executorRule, 20)

        onView(withId(R.id.mcvPokeInfo)).perform(ViewActions.click())
        onView(
            CoreMatchers.allOf(
                withId(R.id.tvName),
                ViewMatchers.withText("pikachu")
            )
        ).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    private fun configureCustomDependencies() {
        loadKoinModules(configureAppComponent(getMockUrl()).toMutableList().apply { add(interactorModuleTest) })
    }

    private fun getString(id: Int) = activityRule.activity.getString(id)

    private fun getRecyclerView() = activityRule.activity.find<RecyclerView>(R.id.rvPokeList)

}