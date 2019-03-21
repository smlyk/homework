package com.smlyk.observer.events;

/**
 * @author yekai
 */
public class MouseEventTest {

    public static void main(String[] args) {

        MouseEventCallback callback = new MouseEventCallback();

        Mouse mouse = new Mouse();

        mouse.addListener(MouseEventType.ON_CLICK,callback);
        mouse.click();

        mouse.addListener(MouseEventType.ON_DOUBLE_CLICK,callback);
        mouse.doubleClick();

    }
}
