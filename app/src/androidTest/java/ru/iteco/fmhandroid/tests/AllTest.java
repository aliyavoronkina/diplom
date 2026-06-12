package ru.iteco.fmhandroid.tests;

import androidx.test.espresso.IdlingResource;
import androidx.test.espresso.IdlingRegistry;
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
import ru.iteco.fmhandroid.pages.AboutPage;
import ru.iteco.fmhandroid.pages.NewsPage;
import ru.iteco.fmhandroid.utils.WaitUtils;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.assertion.ViewAssertions.matches;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class AllTest {

    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    private AuthPage authPage;
    private MainPage mainPage;
    private AboutPage aboutPage;
    // private NewsPage newsPage; // временно закомментировано, т.к. не используется

    private IdlingResource waitForEnterButton;
    private IdlingResource waitForMainScreen;

    @Before
    public void setUp() {
        authPage = new AuthPage();
        mainPage = new MainPage();
        aboutPage = new AboutPage();
        // newsPage = new NewsPage(); // временно закомментировано

        // Небольшая задержка для загрузки
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            // Игнорируем
        }

        // Проверяем, на каком экране мы находимся
        // Если мы уже на главном экране (уже авторизованы), выходим
        try {
            onView(withId(R.id.main_menu_image_button)).check(matches(isDisplayed()));
            // Если дошли сюда - значит мы на главном экране, выходим
            onView(withId(R.id.main_menu_image_button)).perform(click());
            onView(withText("Log out")).perform(click());
            Thread.sleep(1000);
        } catch (Exception e) {
            // Не на главном экране, продолжаем
        }

        // Регистрируем IdlingResource для ожидания кнопки входа
        waitForEnterButton = WaitUtils.waitForView(R.id.enter_button);
        IdlingRegistry.getInstance().register(waitForEnterButton);
    }

    @After
    public void tearDown() {
        if (waitForEnterButton != null) {
            IdlingRegistry.getInstance().unregister(waitForEnterButton);
        }
        if (waitForMainScreen != null) {
            IdlingRegistry.getInstance().unregister(waitForMainScreen);
        }
    }

    @Test
    public void TC01_successfulLogin() {
        authPage.login("login2", "password2");
        waitForMainScreen = WaitUtils.waitForView(R.id.main_menu_image_button);
        IdlingRegistry.getInstance().register(waitForMainScreen);
        mainPage.checkMainScreen();
    }

    @Test
    public void TC02_openAbout() {
        authPage.login("login2", "password2");
        waitForMainScreen = WaitUtils.waitForView(R.id.main_menu_image_button);
        IdlingRegistry.getInstance().register(waitForMainScreen);
        mainPage.openMenu();
        onView(withId(R.id.about_back_image_button)).check(matches(isDisplayed()));
        onView(withId(R.id.about_back_image_button)).perform(click());
    }

    @Test
    public void TC03_logout() {
        authPage.login("login2", "password2");
        waitForMainScreen = WaitUtils.waitForView(R.id.main_menu_image_button);
        IdlingRegistry.getInstance().register(waitForMainScreen);
        onView(withId(R.id.main_menu_image_button)).perform(click());
        onView(withText("Log out")).perform(click());
        onView(withId(R.id.enter_button)).check(matches(isDisplayed()));
    }

    @Test
    public void TC04_openOurMission() {
        authPage.login("login2", "password2");
        waitForMainScreen = WaitUtils.waitForView(R.id.main_menu_image_button);
        IdlingRegistry.getInstance().register(waitForMainScreen);
        onView(withId(R.id.our_mission_image_button)).perform(click());
    }
}