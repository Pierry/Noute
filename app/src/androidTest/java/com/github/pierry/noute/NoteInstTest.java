package com.github.pierry.noute;

import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import com.github.pierry.noute.common.TestHelper;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.swipeUp;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static android.support.test.espresso.matcher.ViewMatchers.isEnabled;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.action.ViewActions.doubleClick;
import static com.github.pierry.noute.common.TestHelper.actionOnItemViewAtPosition;
import static com.github.pierry.noute.common.TestHelper.withRecyclerView;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasEntry;
import static android.support.test.espresso.Espresso.onView;

@RunWith(AndroidJUnit4.class) @LargeTest public class NoteInstTest {

  @Rule public ActivityTestRule<MainActivity_> mainActivity =
      new ActivityTestRule<MainActivity_>(MainActivity_.class);

  private static final String CONTENT_CORRECT = "I'm developer and I like it\n\nThis is my note";

  @Test public void createNote() {

    // create new note
    onView(withId(R.id.content)).perform(typeText(CONTENT_CORRECT), closeSoftKeyboard());

    // save note
    onView(withId(R.id.add)).perform(click());

    // verify if recyclerView is ok
  }
}
