package org.vitaly.week05.observer;

/**
 * Created by vitaly on 2017-03-02.
 */
public abstract class Press {
    private final String name;
    private final String title;

    public Press(String name,String title) {
        this.name = name;
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public String getTitle() {
        return title;
    }

    public abstract PaperQuality getPaperQuality();
}
