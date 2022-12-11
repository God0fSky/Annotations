package ua.hillelit.homeworks.entity;

import ua.hillelit.homeworks.annotation.AfterSuite;
import ua.hillelit.homeworks.annotation.BeforeSuite;
import ua.hillelit.homeworks.annotation.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

public class TestRunner {

    public static void start(Class clazz) {
        //checkAnnotationPriority(clazz, Test.class.getName());
        isValidBeforeSuite(clazz);
        isValidAfterSuite(clazz);

        checkAnnotation(clazz, BeforeSuite.class.getName());
        checkAnnotationPriority(clazz, Test.class.getName());
        checkAnnotation(clazz, AfterSuite.class.getName());

    }

    private static void checkAnnotation(Class clazz, String annotationName) {
        for (Method method : clazz.getMethods()) {
            for (Annotation annotation : method.getAnnotations()) {
                if (checkAnnotationName(annotation, annotationName)) {
                    invokeMethod(method, clazz);
                }
            }
        }
    }

    private static void checkAnnotationPriority(Class clazz, String annotationName) {
        Map<Integer, Method> methods = new TreeMap<>(Comparator.naturalOrder());
        for (Method method : clazz.getMethods()) {
            for (Annotation annotation : method.getAnnotations()) {
                if (checkAnnotationName(annotation, annotationName)) {
                    methods.put(method.getAnnotation(Test.class).priority(), method);
                }
            }
        }
        methods.values().forEach(method -> invokeMethod(method, clazz));
    }



    private static boolean checkAnnotationName(Annotation an, String checkAn) {
        if (an.annotationType().getName().equals(checkAn)) {
            return true;
        }
        return false;
    }

    private static void invokeMethod(Method method, Class clazz) {
        try {
            method.invoke(clazz);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    private static void isValidBeforeSuite(Class clazz) {
        if (countAnnotation(clazz, BeforeSuite.class) == 0) {
            throw new RuntimeException("Class " + clazz.getName() + " has not @BeforeSuite annotation!");
        }
        System.out.println(countAnnotation(clazz, BeforeSuite.class));
        if (countAnnotation(clazz, BeforeSuite.class) > 1) {
            throw new RuntimeException("Found more than one @BeforeSuite annotation!");
        }
    }


    private static void isValidAfterSuite(Class clazz) {
        if (countAnnotation(clazz, AfterSuite.class) == 0) {
            throw new RuntimeException("Class" + clazz.getName() + " has not @AfterSuite annotation!");
        }
        System.out.println(countAnnotation(clazz, AfterSuite.class));
        if (countAnnotation(clazz, AfterSuite.class) > 1) {
            throw new RuntimeException("Found more than one @AfterSuite annotation!");
        }
    }

    private static long countAnnotation(Class clazz, Class annotation) {
        Method[] methods = clazz.getDeclaredMethods();
        return Arrays.stream(methods)
                .filter(f -> f.isAnnotationPresent(annotation))
                .count();
    }



}
