package org.vitaly.week05.observer;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by vitaly on 2017-03-02.
 */
public class NovaPoshta implements PostOffice {
    private Set<Subscriber> subscribers;

    public NovaPoshta() {
        this.subscribers = new HashSet<>();
    }

    @Override
    public void addSubscriber(Subscriber subscriber) {
        subscribers.add(subscriber);
    }

    @Override
    public void removeSubscriber(Subscriber subscriber) {
        subscribers.remove(subscriber);
    }

    @Override
    public void receivePress(Press press) {
        for (Subscriber subscriber : subscribers) {
            subscriber.inform(press);
        }
    }
}
