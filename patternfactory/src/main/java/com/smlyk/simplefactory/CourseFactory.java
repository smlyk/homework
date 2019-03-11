package com.smlyk.simplefactory;

import com.smlyk.ICourse;

/**
 * @author yekai
 */
public class CourseFactory {

    public ICourse create(Class<? extends ICourse> clazz){

        try {
            if (null != clazz){
                return clazz.newInstance();
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;

    }
}
