package edu.gatech.donatrix;

import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import android.support.test.rule.ActivityTestRule;

import androidx.test.espresso.intent.Intents;
import androidx.test.espresso.intent.rule.IntentsTestRule;
import edu.gatech.donatrix.controllers.LoginActivity;
import edu.gatech.donatrix.controllers.UserHomeActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class LoginActivityTest {
    @Rule
    public ActivityTestRule<LoginActivity> mActivityRule = new ActivityTestRule<>(LoginActivity.class);
    @Rule
    public IntentsTestRule<LoginActivity> intentsTestRule = new IntentsTestRule<>(LoginActivity.class);
    @Test
    public void checkUser() {
        onView(withId(R.id.loginEmailTextField)).perform(typeText("Zeke Day"), closeSoftKeyboard());
        onView(withId(R.id.registerPasswordInputText)).perform(typeText("janisday"), closeSoftKeyboard());
        onView(withId(R.id.loginLoginButton)).perform(click());

        Intents.init();
        intended(hasComponent(UserHomeActivity.class.getName()));
    }
}
