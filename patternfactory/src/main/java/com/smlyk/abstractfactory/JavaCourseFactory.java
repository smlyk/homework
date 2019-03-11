package com.smlyk.abstractfactory;

/**
 * @author yekai
 */
public class JavaCourseFactory extends CourseFactory{
    @Override
    INote createNote() {
        return new JavaNote();
    }

    @Override
    IVideo createVideo() {
        return new JavaVideo();
    }
}
