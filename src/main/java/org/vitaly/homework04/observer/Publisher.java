package org.vitaly.homework04.observer;

import static org.vitaly.util.InputChecker.PRESS_ISSUE_MUST_NOT_BE_NULL;
import static org.vitaly.util.InputChecker.requireNonNull;

/**
 * Created by vitaly on 2017-03-02.
 */
public interface Publisher {
    Newspaper publishNewspaper(String title);

    Magazine publishMagazine(String title);

    default void sendPress(Press press, PostOffice postOffice) {
        requireNonNull(press, PRESS_ISSUE_MUST_NOT_BE_NULL);
        requireNonNull(postOffice, "Post office must not be null!");
        postOffice.receivePress(press);
    }
}
