package com.smlyk.observer.guava;

import com.google.common.eventbus.EventBus;
import com.smlyk.observer.gperadvice.Question;

/**
 * @author yekai
 */
public class GuavaEventTest {
    public static void main(String[] args) {

        //消息总线
        EventBus eventBus = new EventBus();
        GuavaTeacher tom = new GuavaTeacher("Tom");
        GuavaTeacher mic = new GuavaTeacher("Mic");
        eventBus.register(tom);
        eventBus.register(mic);
        //业务逻辑代码
        Question question = new Question();
        question.setUserName("小明");
        question.setContent("观察者模式适用于哪些场景？");

        eventBus.post(question);


    }
}
