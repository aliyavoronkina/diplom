package ru.iteco.fmhandroid.ui;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import ru.iteco.fmhandroid.R;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class AllTests {

    // Константы для тестов (убираем хардкод)
    private static final String VALID_LOGIN = "login2";
    private static final String VALID_PASSWORD = "password2";
    private static final String INVALID_PASSWORD = "wrong";

    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    @Before
    public void setUp() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // ========== ВСПОМОГАТЕЛЬНЫЕ МЕТОДЫ ==========

    private void login(String login, String password) {
        onView(withId(R.id.login_text_input_layout)).perform(replaceText(login), closeSoftKeyboard());
        onView(withId(R.id.password_text_input_layout)).perform(replaceText(password), closeSoftKeyboard());
        onView(withId(R.id.enter_button)).perform(click());
    }

    private void logout() {
        onView(withId(R.id.main_menu_image_button)).perform(click());
        onView(withText("Log out")).perform(click());
    }

    private void openMenu() {
        onView(withId(R.id.main_menu_image_button)).perform(click());
    }

    // ========== ТЕСТ 1: Успешная авторизация ==========
    @Test
    public void TC01_successfulLogin() {
        login(VALID_LOGIN, VALID_PASSWORD);
        onView(withId(R.id.main_menu_image_button)).check(matches(isDisplayed()));
    }

    // ========== ТЕСТ 2: Неверный пароль ==========
    @Test
    public void TC02_invalidPassword() {
        login(VALID_LOGIN, INVALID_PASSWORD);
        onView(withText("Wrong login or password")).check(matches(isDisplayed()));
    }

    // ========== ТЕСТ 3: Пустые поля ==========
    @Test
    public void TC03_emptyFields() {
        onView(withId(R.id.enter_button)).perform(click());
        onView(withId(R.id.enter_button)).check(matches(isDisplayed()));
    }

    // ========== ТЕСТ 4: Открытие About ==========
    @Test
    public void TC04_openAbout() {
        login(VALID_LOGIN, VALID_PASSWORD);
        openMenu();
        onView(withText("About")).perform(click());
        onView(withId(R.id.about_back_image_button)).check(matches(isDisplayed()));
        pressBack();
    }

    // ========== ТЕСТ 5: Выход из аккаунта ==========
    @Test
    public void TC05_logout() {
        login(VALID_LOGIN, VALID_PASSWORD);
        logout();
        onView(withId(R.id.enter_button)).check(matches(isDisplayed()));
    }

    // ========== ТЕСТ 6: Открытие Our Mission ==========
    @Test
    public void TC06_openOurMission() {
        login(VALID_LOGIN, VALID_PASSWORD);
        onView(withId(R.id.our_mission_image_button)).perform(click());
        onView(withId(R.id.our_mission_item_list_recycler_view)).check(matches(isDisplayed()));
        pressBack();
    }

    // ========== ТЕСТ 7: Клик по новости ==========
    @Test
    public void TC07_clickOnNews() {
        login(VALID_LOGIN, VALID_PASSWORD);
        onView(withId(R.id.news_list_recycler_view)).check(matches(isDisplayed()));
        onView(withId(R.id.news_list_recycler_view))
                .perform(actionOnItemAtPosition(0, click()));
        onView(withId(R.id.main_menu_image_button)).check(matches(isDisplayed()));
    }

    // ========== ТЕСТ 8: Сортировка ==========
    @Test
    public void TC08_sortNews() {
        login(VALID_LOGIN, VALID_PASSWORD);
        onView(withId(R.id.sort_news_material_button)).perform(click());
        onView(withId(R.id.sort_news_material_button)).check(matches(isDisplayed()));
    }

    // ========== ТЕСТ 9: Развернуть/свернуть ==========
    @Test
    public void TC09_expandCollapseNews() {
        login(VALID_LOGIN, VALID_PASSWORD);
        onView(withId(R.id.expand_material_button)).perform(click());
        onView(withId(R.id.expand_material_button)).check(matches(isDisplayed()));
        onView(withId(R.id.expand_material_button)).perform(click());
        onView(withId(R.id.expand_material_button)).check(matches(isDisplayed()));
    }

    // ========== ТЕСТ 10: Фильтр ==========
    @Test
    public void TC10_openNewsFilter() {
        login(VALID_LOGIN, VALID_PASSWORD);
        onView(withId(R.id.filter_news_material_button)).perform(click());
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        pressBack();
    }
}