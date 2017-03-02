package org.vitaly.week05.observer;

import java.util.Collection;

/**
 * Created by vitaly on 2017-03-02.
 */
public interface Subscriber {
    void inform(Press newIssue);

    Collection<Press> getPressCollection();
}
