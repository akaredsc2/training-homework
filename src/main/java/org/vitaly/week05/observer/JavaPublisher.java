package org.vitaly.week05.observer;

import static java.util.Objects.requireNonNull;
import static org.vitaly.util.InputChecker.TITLE_MUST_NOT_BE_NULL;

/**
 * Created by vitaly on 2017-03-02.
 */
public class JavaPublisher implements Publisher {
    private static JavaPublisher instance = new JavaPublisher();

    private JavaPublisher() {
    }

    public static Publisher getInstance() {
        return instance;
    }

    @Override
    public Newspaper publishNewspaper(String title) {
        requireNonNull(title, TITLE_MUST_NOT_BE_NULL);

        return new Newspaper("Everyday Java", title);
    }

    @Override
    public Magazine publishMagazine(String title) {
        requireNonNull(title, TITLE_MUST_NOT_BE_NULL);

        return new Magazine("Java Magazine", title);
    }
}
