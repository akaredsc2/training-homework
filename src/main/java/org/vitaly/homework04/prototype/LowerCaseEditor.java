package org.vitaly.homework04.prototype;

import static org.vitaly.util.InputChecker.ARTICLE;
import static org.vitaly.util.InputChecker.requireNonNull;

/**
 * Created by vitaly on 2017-03-08.
 */
public class LowerCaseEditor implements Editor {
    @Override
    public Article edit(Article article) throws CloneNotSupportedException {
        requireNonNull(article, ARTICLE);

        Article clone = article.clone();

        clone.setTitle(clone.getTitle().toLowerCase());
        clone.setContent(clone.getContent().toLowerCase());

        return clone;
    }
}
