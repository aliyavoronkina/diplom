package ru.iteco.fmhandroid.pages;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import ru.iteco.fmhandroid.R;

public class AboutPage {

    public void checkAboutScreen() {
        onView(withId(R.id.about_back_image_button)).check(matches(isDisplayed()));
    }

    public void clickBackButton() {
        onView(withId(R.id.about_back_image_button)).perform(click());
    }
}