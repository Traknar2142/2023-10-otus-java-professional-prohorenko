package homework.processor;

import homework.annotations.After;
import homework.annotations.Before;
import homework.annotations.Test;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings({"java:S2629", "java:S3457", "java:S2259", "java:S1117", "java:S106"})
public class TestAnnotationProcessor {
    public void process(String className)
            throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException,
                    IllegalAccessException {
        Class<?> testClass = Class.forName(className);
        List<Method> annotatedTestMethods = getAnnotatedTestMethods(testClass);
        Method beforeMethod = getAnnotatedMethod(testClass, Before.class);
        Method afterMethod = getAnnotatedMethod(testClass, After.class);

        Object instance = testClass.getDeclaredConstructor().newInstance();
        int failCount = 0;
        for (Method method : annotatedTestMethods) {
            try {
                beforeMethod.invoke(instance);
                method.invoke(instance);
                System.out.println("test: " + method.getName() + " passed");
            } catch (InvocationTargetException e) {
                System.out.println("test: " + method.getName() + " fail: "
                        + e.getTargetException().getMessage());
                failCount++;
            } finally {
                afterMethod.invoke(instance);
            }
        }
        printStatistic(annotatedTestMethods.size(), failCount);
    }

    private void printStatistic(int testCount, int failCount) {
        System.out.println("executed: " + testCount);
        System.out.println("fails: " + failCount);
        System.out.println("passed: " + (testCount - failCount));
    }

    private Method getAnnotatedMethod(Class<?> clazz, Class<? extends Annotation> annotation) {
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            if (method.isAnnotationPresent(annotation)) {
                return method;
            }
        }
        return null;
    }

    private List<Method> getAnnotatedTestMethods(Class<?> clazz) {
        Method[] methods = clazz.getDeclaredMethods();
        List<Method> testMethods = new ArrayList<>();
        for (Method method : methods) {
            if (method.isAnnotationPresent(Test.class)) {
                testMethods.add(method);
            }
        }
        return testMethods;
    }
}
