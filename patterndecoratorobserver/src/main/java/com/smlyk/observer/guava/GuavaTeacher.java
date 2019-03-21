package com.smlyk.observer.guava;

import com.google.common.eventbus.Subscribe;
import com.smlyk.observer.gperadvice.Question;

/**
 * @author yekai
 */
public class GuavaTeacher {

    private String name = "Gper生态圈";

    private String teacherName;

    public GuavaTeacher(String teacherName){
        this.teacherName = teacherName;
    }

    @Subscribe
    public void subscribe(Question question){

        System.out.println("-------------------------");
        System.out.println(teacherName + "老师，你好！\n" +
                "您收到了一个来自["+name+"]的提问，希望您解答，问题内容如下：\n"+
                question.getContent()+"\n"+"提问者："+question.getUserName());
    }

}
