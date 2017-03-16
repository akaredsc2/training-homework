package org.vitaly.homework04.observer;

import static org.vitaly.util.InputChecker.TITLE;
import static org.vitaly.util.InputChecker.requireNonNull;

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
        requireNonNull(title, TITLE);

        return new Newspaper("Everyday Java", title);
    }

    @Override
    public Magazine publishMagazine(String title) {
        requireNonNull(title, TITLE);

        return new Magazine("Java Magazine", title);
    }
}
