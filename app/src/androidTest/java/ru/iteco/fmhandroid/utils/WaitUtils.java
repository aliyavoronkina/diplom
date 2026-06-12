package ru.iteco.fmhandroid.utils;

import android.view.View;
import androidx.test.espresso.IdlingResource;
import androidx.test.espresso.IdlingRegistry;
import androidx.test.espresso.ViewInteraction;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.assertion.ViewAssertions.matches;

import ru.iteco.fmhandroid.R;


public class WaitUtils {

    private static class ViewIdlingResource implements IdlingResource {
        private final int viewId;
        private ResourceCallback callback;
        private boolean isIdle = false;

        public ViewIdlingResource(int viewId) {
            this.viewId = viewId;
        }

        @Override
        public String getName() {
            return "ViewIdlingResource for " + viewId;
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
    }

    public static IdlingResource waitForView(int viewId) {
        return new ViewIdlingResource(viewId);
    }
}