package com.smlyk.deep;

import java.io.*;
import java.util.Date;

/**
 * @author yekai
 */
public class QiTianDaSheng extends Monkey implements Cloneable, Serializable{

    protected JingGuBang jingGuBang;

    public QiTianDaSheng(){
        //初始化
         this.birthday = new Date();
         this.jingGuBang = new JingGuBang();
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return this.deepClone();
    }

    private Object deepClone() {
            try (
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(bos)){
                oos.writeObject(this);

                try( ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
                    ObjectInputStream ois = new ObjectInputStream(bis)
                    ){
                       QiTianDaSheng clone = (QiTianDaSheng) ois.readObject();
                       return clone;
                    } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }catch (IOException e) {
                e.printStackTrace();
            }

            return null;
    }


    public QiTianDaSheng shallowClone(QiTianDaSheng source){

        QiTianDaSheng target = new QiTianDaSheng();
        target.height = source.height;
        target.weight = source.weight;

        target.jingGuBang = source.jingGuBang;

        return target;
    }
}
