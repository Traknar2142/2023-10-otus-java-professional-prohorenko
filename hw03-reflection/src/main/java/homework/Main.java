package homework;

import homework.processor.TestAnnotationProcessor;
import java.lang.reflect.InvocationTargetException;

public class Main {
    public static void main(String[] args)
            throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException,
                    IllegalAccessException {
        TestAnnotationProcessor testAnnotationProcessor = new TestAnnotationProcessor();
        testAnnotationProcessor.process("homework.test.MathServiceTest");
    }
}
