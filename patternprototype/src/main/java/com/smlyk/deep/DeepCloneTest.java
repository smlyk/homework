package com.smlyk.deep;

/**
 * @author yekai
 */
public class DeepCloneTest {

    public static void main(String[] args) {

        QiTianDaSheng source = new QiTianDaSheng();

        QiTianDaSheng shallowClone = source.shallowClone(source);
        System.out.println("浅克隆:---- "+ (shallowClone.jingGuBang == source.jingGuBang));


        try {
            QiTianDaSheng  deepClone= (QiTianDaSheng) source.clone();

            System.out.println("深克隆：" + (deepClone.jingGuBang == source.jingGuBang));

        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }


    }


}
