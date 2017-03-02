package org.vitaly.week05.observer;

/**
 * Created by vitaly on 2017-03-02.
 */
public interface PostOffice {
    void addSubscriber(Subscriber subscriber);

    void removeSubscriber(Subscriber subscriber);

    void receivePress(Press press);
}
