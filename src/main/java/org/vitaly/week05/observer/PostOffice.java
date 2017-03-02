package org.vitaly.week05.observer;

import java.util.List;
import java.util.Set;

/**
 * Created by vitaly on 2017-03-02.
 */
public interface PostOffice {
    void addSubscriber(Subscriber subscriber);

    void removeSubscriber(Subscriber subscriber);

    Set<Subscriber> getSubscribers();

    void receivePress(Press press);

    List<Press> getReceivedPressArchive();
}
