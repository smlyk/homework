package com.smlyk.simplefactory;

import com.smlyk.ICourse;
import com.smlyk.JavaCourse;

/**
 * @author yekai
 */
public class SimpleFactoryTest {

    public static void main(String[] args) {

        CourseFactory factory =  new CourseFactory();
        ICourse course = factory.create(JavaCourse.class);

        course.record();
    }
}
