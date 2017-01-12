package de.claudioaltamura.os.jsonassert;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.skyscreamer.jsonassert.JSONCompareResult;

public class JsonAssertTestUtils {

    static Matcher<JSONCompareResult> failsWithMessage(final Matcher<String> expectedMessage) {
        return new TypeSafeMatcher<JSONCompareResult>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("a failed comparison with message ").appendDescriptionOf(expectedMessage);
            }

            @Override
            public boolean matchesSafely(JSONCompareResult item) {
                return item.failed() && expectedMessage.matches(item.getMessage());
            }
        };
    }

}
