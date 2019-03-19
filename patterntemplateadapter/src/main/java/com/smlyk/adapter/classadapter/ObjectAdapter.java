package com.smlyk.adapter.classadapter;

/**
 * @author yekai
 */
public class ObjectAdapter implements Ps2{

    private Usb usb;

    public ObjectAdapter(Usb usb){
        this.usb = usb;
    }

    @Override
    public void isPs2() {
        usb.isUsb();
    }
}
