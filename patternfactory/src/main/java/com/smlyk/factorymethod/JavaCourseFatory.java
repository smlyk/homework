package com.smlyk.factorymethod;

import com.smlyk.ICourse;
import com.smlyk.JavaCourse;

/**
 * @author yekai
 */
public class JavaCourseFatory implements ICourseFactory{
    @Override
    public ICourse create() {
        return new JavaCourse();
    }
}
