package org.vitaly.homework04.prototype;

import java.util.ArrayList;
import java.util.Collections;
import java.util.StringJoiner;

import static org.vitaly.util.InputChecker.ARTICLE;
import static org.vitaly.util.InputChecker.requireNonNull;

/**
 * Created by vitaly on 2017-03-08.
 */
public class WordShufflingEditor implements Editor {
    @Override
    public Article edit(Article article) throws CloneNotSupportedException {
        requireNonNull(article, ARTICLE);

        Article clone = article.clone();

        String[] contentWords = clone.getContent().split("\\s+");
        ArrayList<String> contentWordList = new ArrayList<>();
        Collections.addAll(contentWordList, contentWords);
        Collections.shuffle(contentWordList);

        StringJoiner stringJoiner = new StringJoiner(" ");
        for (String word : contentWordList) {
            stringJoiner.add(word);
        }

        clone.setContent(stringJoiner.toString());

        return clone;
    }
}
