package org.vitaly.week05.observer;

/**
 * Created by vitaly on 01.03.17.
 */
public interface Observer {
    void notify(Printable newIssue);
}
