package org.vitaly.homework04.prototype;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import static org.vitaly.util.InputChecker.ARTICLE_MUST_NOT_BE_NULL;
import static org.vitaly.util.InputChecker.requireNonNull;

/**
 * Created by vitaly on 2017-03-08.
 */
public class ArticleRegistry {
    private List<Article> articles;

    public ArticleRegistry() {
        this.articles = new ArrayList<>();
    }

    public boolean addArticleToRegistry(Article article) {
        requireNonNull(article, ARTICLE_MUST_NOT_BE_NULL);

        if (articles.contains(article)) {
            return false;
        } else {
            articles.add(article);
            return true;
        }
    }

    public List<Article> getArticles(Predicate<Article> predicate) throws CloneNotSupportedException {
        requireNonNull(predicate, "Predicate must not be null!");

        List<Article> result = new ArrayList<>();
        for (Article article : articles) {
            if (predicate.test(article)) {
                result.add(article.clone());
            }
        }
        return result;
    }
}
