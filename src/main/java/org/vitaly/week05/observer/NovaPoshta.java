package org.vitaly.week05.observer;

import java.util.*;

import static org.vitaly.util.InputChecker.requireNonNull;

/**
 * Created by vitaly on 2017-03-02.
 */
public class NovaPoshta implements PostOffice {
    private Set<Subscriber> subscribers;
    private List<Press> receivedPressArchive;

    public NovaPoshta() {
        this.subscribers = new HashSet<>();
        this.receivedPressArchive = new ArrayList<>();
    }

    @Override
    public void addSubscriber(Subscriber subscriber) {
        requireNonNull(subscriber, "Subscriber must not be null!");

        subscribers.add(subscriber);
    }

    @Override
    public void removeSubscriber(Subscriber subscriber) {
        requireNonNull(subscriber, "Subscriber must not be null!");

        subscribers.remove(subscriber);
    }

    @Override
    public Set<Subscriber> getSubscribers() {
        return Collections.unmodifiableSet(subscribers);
    }

    @Override
    public void receivePress(Press press) {
        requireNonNull(press, "Press must not be null!");

        receivedPressArchive.add(press);
        for (Subscriber subscriber : subscribers) {
            subscriber.inform(press);
        }
    }

    @Override
    public List<Press> getReceivedPressArchive() {
        return Collections.unmodifiableList(receivedPressArchive);
    }
}
