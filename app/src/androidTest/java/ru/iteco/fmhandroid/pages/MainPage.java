package ru.iteco.fmhandroid.pages;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import ru.iteco.fmhandroid.R;

public class MainPage {

    public void checkMainScreen() {
        onView(withId(R.id.main_menu_image_button)).check(matches(isDisplayed()));
    }

    public void openMenu() {
        onView(withId(R.id.main_menu_image_button)).perform(click());
    }

    public void clickLogout() {
        openMenu();
        onView(withText("Log out")).perform(click());
    }

    public void clickOurMission() {
        onView(withId(R.id.our_mission_image_button)).perform(click());
    }

    public void clickEditNewsButton() {
        onView(withId(R.id.edit_news_material_button)).perform(click());
    }
}