package ua.hillelit.homeworks;

import ua.hillelit.homeworks.entity.Test1;
import ua.hillelit.homeworks.entity.Test2;
import ua.hillelit.homeworks.entity.TestRunner;

public class Main {

    public static void main(String[] args) {
        Test1 test1 = new Test1();
        Test2 test2 = new Test2();

        TestRunner.start(test1.getClass());
        System.out.println();
        TestRunner.start(test2.getClass());
    }

}
