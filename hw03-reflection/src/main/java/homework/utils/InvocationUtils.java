package homework.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class InvocationUtils {
    private InvocationUtils() {}

    public static void optionalInvoke(Method method, Object instance)
            throws InvocationTargetException, IllegalAccessException {
        if (method != null && instance != null) {
            method.invoke(instance);
        }
    }
}
