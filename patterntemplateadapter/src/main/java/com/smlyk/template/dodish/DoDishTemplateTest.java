package com.smlyk.template.dodish;

/**
 * @author yekai
 */
public class DoDishTemplateTest {

    public static void main(String[] args) {

        DodishTemplate eggsWithTomato= new EggsWithTomato();
        eggsWithTomato.dodish();

        System.out.println("------------------------");

        DodishTemplate bouilli = new Bouilli();
        bouilli.dodish();

    }

}
