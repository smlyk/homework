package com.smlyk.staticproxy;

import com.smlyk.Person;

/**
 * @author yekai
 */
public class Father implements Person{

    private Person person;

    public Father(Person person){
        this.person = person;
    }

    @Override
    public void findLover() {
        System.out.println("父亲物色对象");
        this.person.findLover();
        System.out.println("双方父母同意,确立关系");
    }


}
