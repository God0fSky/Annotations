package ua.hillelit.homeworks.entity;

import ua.hillelit.homeworks.annotation.AfterSuite;
import ua.hillelit.homeworks.annotation.BeforeSuite;
import ua.hillelit.homeworks.annotation.Test;

public class Test2 {

    @BeforeSuite
    public static void doBeforeSuite2() {
        System.out.println("Hi, im BeforeSuite2");
    }

    @Test(priority = 2)
    public static void doTest2() {
        System.out.println("Hi, im Test2");
    }


    @Test(priority = 1)
    public static void finishTest2() {
        System.out.println("Hi, im finishTest2");
    }

    @AfterSuite
    public static void doAfterSuite2() {
        System.out.println("Hi, im AfterSuite2");
    }

}
