package ru.iteco.fmhandroid.tests;

import androidx.test.espresso.IdlingRegistry;
import androidx.test.espresso.IdlingResource;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.pages.AuthPage;
import ru.iteco.fmhandroid.pages.MainPage;
import ru.iteco.fmhandroid.data.TestData;
import ru.iteco.fmhandroid.utils.WaitUtils;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class OurMissionTest {

    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    private AuthPage authPage;
    private MainPage mainPage;
    private IdlingResource waitForMainScreen;

    @Before
    public void setUp() {
        authPage = new AuthPage();
        mainPage = new MainPage();

        authPage.login(TestData.VALID_LOGIN, TestData.VALID_PASSWORD);

        waitForMainScreen = WaitUtils.waitForView(R.id.main_menu_image_button);
        IdlingRegistry.getInstance().register(waitForMainScreen);
    }

    @After
    public void tearDown() {
        if (waitForMainScreen != null) {
            IdlingRegistry.getInstance().unregister(waitForMainScreen);
        }
    }

    @Test
    public void TC06_openOurMission() {
        onView(withId(R.id.our_mission_image_button)).perform(click());
        onView(withId(R.id.our_mission_item_list_recycler_view)).check(matches(isDisplayed()));
    }
}