package ru.otus.ioc;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashSet;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.annotation.Log;
import ru.otus.service.TestLogging;
import ru.otus.service.TestLoggingInterface;

public class Ioc {
    private static final Logger logger = LoggerFactory.getLogger(Ioc.class);

    private Ioc() {}

    public static TestLoggingInterface createTestLogging() {
        InvocationHandler handler = new DemoInvocationHandler(new TestLogging());
        return (TestLoggingInterface) Proxy.newProxyInstance(
                Ioc.class.getClassLoader(), new Class<?>[] {TestLoggingInterface.class}, handler);
    }

    static class DemoInvocationHandler implements InvocationHandler {
        private final TestLoggingInterface myClass;
        private static final Set<Method> logAnnotationMethods = new HashSet<>();

        DemoInvocationHandler(TestLoggingInterface myClass) {
            Method[] declaredMethods = myClass.getClass().getDeclaredMethods();
            for (Method method : declaredMethods) {
                if (method.isAnnotationPresent(Log.class)) {
                    logAnnotationMethods.add(method);
                }
            }
            this.myClass = myClass;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            Method targetMethod = myClass.getClass().getDeclaredMethod(method.getName(), method.getParameterTypes());
            if (logAnnotationMethods.contains(targetMethod)) {
                for (Object arg : args) {
                    logger.info("invoking arg:{}", arg);
                }
            }
            return method.invoke(myClass, args);
        }

        @Override
        public String toString() {
            return "DemoInvocationHandler{" + "myClass=" + myClass + '}';
        }
    }
}
