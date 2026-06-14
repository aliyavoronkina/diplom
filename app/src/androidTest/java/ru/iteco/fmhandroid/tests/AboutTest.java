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

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.pages.AuthPage;
import ru.iteco.fmhandroid.pages.MainPage;
import ru.iteco.fmhandroid.pages.AboutPage;
import ru.iteco.fmhandroid.data.TestData;
import ru.iteco.fmhandroid.utils.WaitUtils;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@LargeTest
@RunWith(AndroidJUnit4.class)
@Epic("Мобильный хоспис")
@Feature("О приложении")
public class AboutTest {

    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    private AuthPage authPage;
    private MainPage mainPage;
    private AboutPage aboutPage;
    private IdlingResource waitForAppToLoad;

    @Before
    public void setUp() {
        authPage = new AuthPage();
        mainPage = new MainPage();
        aboutPage = new AboutPage();

        waitForAppToLoad = WaitUtils.waitForAppToLoad();
        IdlingRegistry.getInstance().register(waitForAppToLoad);

        authPage.login(TestData.VALID_LOGIN, TestData.VALID_PASSWORD);
    }

    @After
    public void tearDown() {
        if (waitForAppToLoad != null) {
            IdlingRegistry.getInstance().unregister(waitForAppToLoad);
        }
    }

    @Test
    @Story("Открытие экрана About")
    @Description("Позитивный тест: открытие раздела About")
    public void TC05_openAbout() {
        mainPage.openMenu();
        onView(withText("About")).perform(click());
        aboutPage.checkAboutScreen();
    }
}