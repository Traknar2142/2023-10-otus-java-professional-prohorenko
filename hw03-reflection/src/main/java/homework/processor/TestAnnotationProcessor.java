package homework.processor;

import homework.annotations.After;
import homework.annotations.Before;
import homework.annotations.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class TestAnnotationProcessor {
    public void process(String className) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class<?> testClass = Class.forName(className);
        Method[] methods = testClass.getDeclaredMethods();
        Method beforeMethod = null;
        Method afterMethod = null;
        List<Method> testMethods = new ArrayList<>();

        for(Method method : methods) {
            if (method.isAnnotationPresent(Before.class)){
                beforeMethod = method;
            }
            if (method.isAnnotationPresent(After.class)){
                afterMethod = method;
            }
            if (method.isAnnotationPresent(Test.class)){
                testMethods.add(method);
            }
        }
        Object instance = testClass.getDeclaredConstructor().newInstance();
        int failCount = 0;
        for (Method method: testMethods) {
            try {
                beforeMethod.invoke(instance);
                method.invoke(instance);
                afterMethod.invoke(instance);
                System.out.println("test: " + method.getName() + " passed");
            } catch (InvocationTargetException e){
                afterMethod.invoke(instance);
                System.out.println("test: " + method.getName() + " fail: " + e.getTargetException().getMessage());
                failCount++;
            }
        }
        System.out.println("executed: " +  testMethods.size());
        System.out.println("fails: " +  failCount);
        System.out.println("passed: " + (testMethods.size() - failCount));
    }
}
