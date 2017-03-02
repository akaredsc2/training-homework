package org.vitaly.week05.observer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.vitaly.util.InputChecker.PRESS_ISSUE_MUST_NOT_BE_NULL;
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
        requireNonNull(newIssue, PRESS_ISSUE_MUST_NOT_BE_NULL);

        if (newIssue.getName().toLowerCase().contains("java")) {
            pressList.add(newIssue);
        }
    }

    @Override
    public Collection<Press> getPressCollection() {
        return Collections.unmodifiableList(pressList);
    }
}
