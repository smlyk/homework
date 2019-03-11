package com.smlyk.abstractfactory;

/**
 * @author yekai
 */
public class AbstractFactoryTest {
    public static void main(String[] args) {

        JavaCourseFactory factory1 = new JavaCourseFactory();
        factory1.createNote().edit();
        factory1.createVideo().record();

        PythonCourseFactory factory2 = new PythonCourseFactory();
        factory2.createNote().edit();
        factory2.createVideo().record();

    }
}
