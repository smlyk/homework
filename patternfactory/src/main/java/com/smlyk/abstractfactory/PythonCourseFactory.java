package com.smlyk.abstractfactory;

/**
 * @author yekai
 */
public class PythonCourseFactory extends CourseFactory {
    @Override
    INote createNote() {
        return new PythonNote();
    }

    @Override
    IVideo createVideo() {
        return new PythonVedio();
    }
}
