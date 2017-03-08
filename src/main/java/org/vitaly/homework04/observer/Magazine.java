package org.vitaly.homework04.observer;

/**
 * Created by vitaly on 2017-03-02.
 */
public class Magazine extends Press {
    public Magazine(String name, String title) {
        super(name, title);
    }

    @Override
    public PaperQuality getPaperQuality() {
        return PaperQuality.HIGH;
    }
}
