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
import ru.iteco.fmhandroid.pages.NewsPage;
import ru.iteco.fmhandroid.data.TestData;
import ru.iteco.fmhandroid.utils.WaitUtils;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class NewsTest {

    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    private AuthPage authPage;
    private MainPage mainPage;
    private NewsPage newsPage;
    private IdlingResource waitForMainScreen;

    @Before
    public void setUp() {
        authPage = new AuthPage();
        mainPage = new MainPage();
        newsPage = new NewsPage();

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
    public void TC07_clickOnNews() {
        newsPage.checkNewsListIsDisplayed();
        newsPage.clickOnNewsItem(0);
    }

    @Test
    public void TC08_sortNews() {
        newsPage.clickSortButton();
        newsPage.checkNewsListIsDisplayed();
    }

    @Test
    public void TC09_createNews() {
        mainPage.clickEditNewsButton();
        newsPage.clickAddNewsButton();
        newsPage.enterNewsTitle(TestData.NEWS_TITLE);
        newsPage.enterNewsDescription(TestData.NEWS_DESCRIPTION);
        newsPage.clickSaveButton();
        newsPage.checkNewsListIsDisplayed();
    }
}