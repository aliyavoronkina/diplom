package ru.iteco.fmhandroid.pages;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import ru.iteco.fmhandroid.R;

public class AuthPage {

    public void enterLogin(String login) {
        onView(withId(R.id.login_text_input_layout)).perform(replaceText(login), closeSoftKeyboard());
    }

    public void enterPassword(String password) {
        onView(withId(R.id.password_text_input_layout)).perform(replaceText(password), closeSoftKeyboard());
    }

    public void clickSignInButton() {
        onView(withId(R.id.enter_button)).perform(click());
    }

    public void login(String login, String password) {
        enterLogin(login);
        enterPassword(password);
        clickSignInButton();
    }
}