package com.smlyk.dbroute.db;

/**
 * @author yekai
 */
public class DynamicDataSourceEntity {

    public static final String DEFAULE_SOURCE = null;

    public static final ThreadLocal<String> THREAD_LOCAL = new ThreadLocal<>();

    private DynamicDataSourceEntity(){}

    public static String get(){

         return THREAD_LOCAL.get();

    }

    public static void restore(){
        THREAD_LOCAL.set(DEFAULE_SOURCE);
    }

    //DB_2018
    //DB_2019
    public static void set(String source){

        THREAD_LOCAL.set(source);

    }

    public static void set(int year){

        THREAD_LOCAL.set("DB_" + year);
    }

}
