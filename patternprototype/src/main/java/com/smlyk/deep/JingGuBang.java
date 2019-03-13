package com.smlyk.deep;

import java.io.Serializable;

/**
 * @author yekai
 */
public class JingGuBang implements Serializable{

    private float h = 100;
    private float d = 10;

    public void big(){
        this.d *= 2;
        this.h *= 2;
    }

    public void small(){
        this.d /= 2;
        this.h /= 2;
    }
}
