package org.vitaly.week05.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vitaly on 01.03.17.
 */
public class Subscriber implements Observer {
    private List<Printable> printables;

    public Subscriber() {
        this.printables = new ArrayList<>();
    }

    @Override
    public void notify(Printable newIssue) {
        printables.add(newIssue);
    }

    public List<Printable> getPrintables() {
        return new ArrayList<>(printables);
    }
}
