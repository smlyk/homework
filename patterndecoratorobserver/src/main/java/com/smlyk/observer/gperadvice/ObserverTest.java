package com.smlyk.observer.gperadvice;

/**
 * @author yekai
 */
public class ObserverTest {

    public static void main(String[] args) {
        Gper gper = Gper.getInstance();

        Teacher tom = new Teacher("Tom");
        Teacher mic = new Teacher("Mic");

        gper.addObserver(tom);
        gper.addObserver(mic);

        //业务逻辑代码
        Question question = new Question();
        question.setUserName("小明");
        question.setContent("观察者模式适用于哪些场景？");

        gper.publishQuestion(question);
    }

}
