package com.smlyk.factorymethod;

import com.smlyk.ICourse;

/**
 * @author yekai
 */
public class FactoryMethodTest {

    public static void main(String[] args) {
        ICourseFactory factory = new JavaCourseFatory();
        ICourse course = factory.create();
        course.record();


        factory = new PythonCourseFactory();
        course = factory.create();
        course.record();
    }
}
