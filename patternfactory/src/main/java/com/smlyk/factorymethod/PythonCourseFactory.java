package com.smlyk.factorymethod;

import com.smlyk.ICourse;
import com.smlyk.PythonCourse;

/**
 * @author yekai
 */
public class PythonCourseFactory implements ICourseFactory{
    @Override
    public ICourse create() {
        return new PythonCourse();
    }
}
