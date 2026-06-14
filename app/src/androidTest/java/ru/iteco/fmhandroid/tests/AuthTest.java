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
@Feature("Авторизация")
public class AuthTest {

    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    private AuthPage authPage;
    private MainPage mainPage;
    private IdlingResource waitForAppToLoad;

    @Before
    public void setUp() {
        authPage = new AuthPage();
        mainPage = new MainPage();

        waitForAppToLoad = WaitUtils.waitForAppToLoad();
        IdlingRegistry.getInstance().register(waitForAppToLoad);
    }

    @After
    public void tearDown() {
        if (waitForAppToLoad != null) {
            IdlingRegistry.getInstance().unregister(waitForAppToLoad);
        }
    }

    @Test
    @Story("Успешный вход")
    @Description("Позитивный тест: вход с валидными логином и паролем")
    public void TC01_successfulLogin() {
        authPage.login(TestData.VALID_LOGIN, TestData.VALID_PASSWORD);
        mainPage.checkMainScreen();
    }

    @Test
    @Story("Неверный пароль")
    @Description("Негативный тест: ожидаем сообщение об ошибке")
    public void TC02_invalidPassword() {
        authPage.login(TestData.VALID_LOGIN, TestData.INVALID_PASSWORD);
        onView(withText(TestData.ERROR_MESSAGE)).check(matches(isDisplayed()));
    }

    @Test
    @Story("Пустые поля")
    @Description("Негативный тест: вход без логина и пароля")
    public void TC03_emptyFields() {
        onView(withId(R.id.enter_button)).perform(click());
        onView(withId(R.id.enter_button)).check(matches(isDisplayed()));
    }

    @Test
    @Story("Выход из аккаунта")
    @Description("Позитивный тест: успешный выход")
    public void TC04_logout() {
        authPage.login(TestData.VALID_LOGIN, TestData.VALID_PASSWORD);
        onView(withId(R.id.main_menu_image_button)).perform(click());
        onView(withText("Log out")).perform(click());
        onView(withId(R.id.enter_button)).check(matches(isDisplayed()));
    }
}