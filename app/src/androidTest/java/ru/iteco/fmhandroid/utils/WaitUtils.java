package ru.iteco.fmhandroid.utils;

import androidx.test.espresso.IdlingResource;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

public class WaitUtils {

    public static IdlingResource waitForView(int viewId) {
        return new IdlingResource() {
            private boolean isIdle = false;
            private ResourceCallback callback;

            @Override
            public String getName() {
                return "WaitForView_" + viewId;
            }

            @Override
            public boolean isIdleNow() {
                try {
                    onView(withId(viewId)).check(matches(isDisplayed()));
                    isIdle = true;
                    if (callback != null) {
                        callback.onTransitionToIdle();
                    }
                } catch (Exception e) {
                    isIdle = false;
                }
                return isIdle;
            }

            @Override
            public void registerIdleTransitionCallback(ResourceCallback callback) {
                this.callback = callback;
            }
        };
    }

    public static IdlingResource waitForAppToLoad() {
        return new IdlingResource() {
            private boolean isIdle = false;
            private ResourceCallback callback;

            @Override
            public String getName() {
                return "WaitForAppToLoad";
            }

            @Override
            public boolean isIdleNow() {
                try {
                    onView(withId(R.id.enter_button)).check(matches(isDisplayed()));
                    isIdle = true;
                    if (callback != null) {
                        callback.onTransitionToIdle();
                    }
                } catch (Exception e) {
                    isIdle = false;
                }
                return isIdle;
            }

            @Override
            public void registerIdleTransitionCallback(ResourceCallback callback) {
                this.callback = callback;
            }
        };
    }
}