package org.vitaly.homework04.observer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.vitaly.util.InputChecker.PRESS_ISSUE;
import static org.vitaly.util.InputChecker.requireNonNull;

/**
 * Created by vitaly on 2017-03-02.
 */
public class JuniorJavaDeveloper implements Subscriber {
    private List<Press> pressList;

    public JuniorJavaDeveloper() {
        this.pressList = new ArrayList<>();
    }

    @Override
    public void inform(Press newIssue) {
        requireNonNull(newIssue, PRESS_ISSUE);

        if (newIssue.getName().toLowerCase().contains("java")) {
            pressList.add(newIssue);
        }
    }

    @Override
    public Collection<Press> getPressCollection() {
        return Collections.unmodifiableList(pressList);
    }
}
