package com.smlyk.abstractfactory;

/**
 * @author yekai
 */
public abstract class CourseFactory {

    abstract INote createNote();

    abstract IVideo createVideo();

}
