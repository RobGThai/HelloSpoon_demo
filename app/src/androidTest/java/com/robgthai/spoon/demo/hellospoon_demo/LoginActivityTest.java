package com.robgthai.spoon.demo.hellospoon_demo;

import android.app.Activity;
import android.test.ActivityInstrumentationTestCase2;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.squareup.spoon.Spoon;

import static android.support.test.espresso.Espresso.closeSoftKeyboard;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.pressImeActionButton;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasFocus;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

public class LoginActivityTest extends ActivityInstrumentationTestCase2<LoginActivity> {

    private static final long WAIT_TIME = 1000l;

    public LoginActivityTest() {
        super(LoginActivity.class);
    }

    public void testHelloWorldVisibleByDefault() {
        ViewGroup root = (ViewGroup) getActivity().findViewById(android.R.id.content);
        int total = root.getChildCount();

        for(int i = 0; i < total; i++) {
            View v = root.getChildAt(i);
            if(v instanceof TextView) {
                assertEquals("TextView contain Login", ((TextView) v).getText().toString(), "Hello Login");
            }
        }
    }

    public void test_LoginFlow() {
        Activity a = getActivity();

        Spoon.screenshot(a, "Start");

        onView(withId(R.id.edtEmail)).perform(typeText("this"));
        Spoon.screenshot(a, "type_this");

        onView(withId(R.id.edtEmail)).perform(typeText("isrobg"));
        Spoon.screenshot(a, "type_isrobg");

        onView(withId(R.id.edtEmail)).perform(typeText("@gmail.com"));
        Spoon.screenshot(a, "type_gmail_com");

        onView(withId(R.id.edtPassword)).perform(typeText("password"));
        Spoon.screenshot(a, "password");

        onView(withId(R.id.btnLogin)).perform(click());
        Spoon.screenshot(a, "Clicked");

        onView(allOf(withId(R.id.txtResult), withText("OK"))).check(matches(isDisplayed()));
    }

    public void test_LoginFail() {
        Activity a = getActivity();

        Spoon.screenshot(a, "Start");

        onView(withId(R.id.edtEmail)).perform(typeText("this"));
        Spoon.screenshot(a, "type_this");

        closeSoftKeyboard();
        waitForMe(WAIT_TIME);

        onView(withId(R.id.btnLogin)).perform(click());
        Spoon.screenshot(a, "Clicked");

        onView(allOf(withId(R.id.txtResult), withText("Invalid login"))).check(matches(isDisplayed()));
    }

    public void test_LoginWithInvalidEmail() {
        Activity a = getActivity();

        Spoon.screenshot(a, "Start");

        onView(withId(R.id.edtEmail)).perform(typeText("this"));
        Spoon.screenshot(a, "type_this");

        onView(withId(R.id.edtPassword)).perform(typeText("password"));
        Spoon.screenshot(a, "password");

        closeSoftKeyboard();
        waitForMe(WAIT_TIME);

        onView(withId(R.id.btnLogin)).perform(click());
        Spoon.screenshot(a, "Clicked");

        onView(allOf(withId(R.id.txtResult), withText("Invalid email"))).check(matches(isDisplayed()));
    }

    public void test_ResetRemoveEverything() {
        Activity a = getActivity();

        Spoon.screenshot(a, "Start");

        onView(withId(R.id.edtEmail)).perform(typeText("this"));
        Spoon.screenshot(a, "type_this");

        onView(withId(R.id.edtPassword)).perform(typeText("password"));
        Spoon.screenshot(a, "password");

        closeSoftKeyboard();
        waitForMe(WAIT_TIME);

        onView(withId(R.id.btnLogin)).perform(click());
        Spoon.screenshot(a, "Login_Clicked");

        onView(withId(R.id.btnReset)).perform(click());
        Spoon.screenshot(a, "Reset_Clicked");

        onView(withId(R.id.edtEmail)).check(matches(withText("")));
        onView(withId(R.id.edtPassword)).check(matches(withText("")));
        onView(withId(R.id.txtResult)).check(matches(withText("")));
    }

    private void waitForMe(long milliseconds) {

        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {

        }
    }

}
