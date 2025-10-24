package com.example.androiduitesting;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.*;
import static org.hamcrest.Matchers.anything;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class ShowActivityTest {

    @Rule
    public ActivityScenarioRule<MainActivity> rule =
            new ActivityScenarioRule<>(MainActivity.class);

    private void addCity(String name) {
        onView(withId(R.id.button_add)).perform(click());
        onView(withId(R.id.editText_name)).perform(replaceText(name));
        onView(withId(R.id.button_confirm)).perform(click());
    }

    @Test
    public void switchesToShowActivityOnListClick() {
        addCity("Edmonton");
        onData(anything()).inAdapterView(withId(R.id.city_list)).atPosition(0).perform(click());

        // If ShowActivity is displayed, the unique view should be visible
        onView(withId(R.id.text_city_name)).check(matches(isDisplayed()));
    }

    @Test
    public void cityNameIsConsistent() {
        String name = "Vancouver";
        addCity(name);
        onData(anything()).inAdapterView(withId(R.id.city_list)).atPosition(0).perform(click());

        onView(withId(R.id.text_city_name)).check(matches(withText(name)));
    }

    @Test
    public void backButtonReturnsToMainActivity() {
        addCity("Calgary");
        onData(anything()).inAdapterView(withId(R.id.city_list)).atPosition(0).perform(click());

        onView(withId(R.id.button_back)).perform(click());

        // Back on MainActivity: the list should be visible again
        onView(withId(R.id.city_list)).check(matches(isDisplayed()));
    }
}
