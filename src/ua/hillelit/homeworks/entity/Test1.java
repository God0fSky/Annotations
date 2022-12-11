package ua.hillelit.homeworks.entity;

import ua.hillelit.homeworks.annotation.AfterSuite;
import ua.hillelit.homeworks.annotation.BeforeSuite;
import ua.hillelit.homeworks.annotation.Test;

public class Test1 {

    @BeforeSuite
    public static void doBeforeSuite1() {
        System.out.println("Hi, im BeforeSuite1");
    }

    @Test(priority = 1)
    public static void doTest1() {
        System.out.println("Hi, im Test1");
    }

    @Test(priority = 2)
    public static void finishTest1() {
        System.out.println("Hi, im finishTest1");
    }

    @AfterSuite
    public static void doAfterSuite1() {
        System.out.println("Hi, im AfterSuite1");
    }

}
