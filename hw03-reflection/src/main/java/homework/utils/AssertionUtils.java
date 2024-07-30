package homework.utils;

import homework.exception.TestDoesntPassException;

public class AssertionUtils {
    private AssertionUtils() {}

    public static void assertEquals(Object expected, Object actual) {
        if (!expected.equals(actual)) {
            throw new TestDoesntPassException(actual.toString() + " doesn't equal " + expected.toString());
        }
    }
}
