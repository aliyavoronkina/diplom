package ru.iteco.fmhandroid.pages;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import ru.iteco.fmhandroid.R;

public class NewsPage {

    public void clickOnNewsItem(int position) {
        onView(withId(R.id.news_list_recycler_view))
                .perform(actionOnItemAtPosition(position, click()));
    }

    public void clickSortButton() {
        onView(withId(R.id.sort_news_material_button)).perform(click());
    }

    public void clickFilterButton() {
        onView(withId(R.id.filter_news_material_button)).perform(click());
    }

    public void clickExpandButton() {
        onView(withId(R.id.expand_material_button)).perform(click());
    }

    public void clickAddNewsButton() {
        onView(withId(R.id.add_news_image_view)).perform(click());
    }

    public void enterNewsTitle(String title) {
        onView(withId(R.id.news_item_title_text_input_edit_text)).perform(replaceText(title));
    }

    public void enterNewsDescription(String description) {
        onView(withId(R.id.news_item_description_text_input_edit_text)).perform(replaceText(description));
    }

    public void clickSaveButton() {
        onView(withId(R.id.save_button)).perform(click());
    }

    public void checkNewsListIsDisplayed() {
        onView(withId(R.id.news_list_recycler_view)).check(matches(isDisplayed()));
    }
}