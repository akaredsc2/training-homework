package org.vitaly.homework04.observer;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static org.vitaly.util.InputChecker.PRESS_ISSUE_MUST_NOT_BE_NULL;
import static org.vitaly.util.InputChecker.requireNonNull;

/**
 * Created by vitaly on 2017-03-02.
 */
public class ExperiencedGardener implements Subscriber {
    private Set<Press> pressSet;

    public ExperiencedGardener() {
        this.pressSet = new HashSet<>();
    }

    @Override
    public void inform(Press newIssue) {
        requireNonNull(newIssue, PRESS_ISSUE_MUST_NOT_BE_NULL);

        if (newIssue.getName().toLowerCase().contains("garden") && newIssue.getPaperQuality() == PaperQuality.HIGH) {
            pressSet.add(newIssue);
        }
    }

    @Override
    public Collection<Press> getPressCollection() {
        return Collections.unmodifiableSet(pressSet);
    }
}
