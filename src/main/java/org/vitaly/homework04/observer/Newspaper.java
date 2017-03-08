package org.vitaly.homework04.observer;

/**
 * Created by vitaly on 2017-03-02.
 */
public class Newspaper extends Press {
    public Newspaper(String name, String title) {
        super(name, title);
    }

    @Override
    public PaperQuality getPaperQuality() {
        return PaperQuality.LOW;
    }
}
