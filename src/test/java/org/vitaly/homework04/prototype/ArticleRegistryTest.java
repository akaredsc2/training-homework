package org.vitaly.homework04.prototype;

import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Objects;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.iterableWithSize;

/**
 * Created by vitaly on 2017-03-08.
 */
public class ArticleRegistryTest {
    private Article article1;
    private Article article2;
    private Article article3;
    private ArticleRegistry articleRegistry;

    @Before
    public void setUp() throws Exception {
        article1 = Article.newArticle("title1", "content1");
        article2 = Article.newArticle("title12", "content12");
        article3 = Article.newArticle("title123", "content123");

        articleRegistry = new ArticleRegistry();

        articleRegistry.addArticleToRegistry(article1);
        articleRegistry.addArticleToRegistry(article2);
        articleRegistry.addArticleToRegistry(article3);
    }

    @Test
    public void addDifferentArticleToRegistry() throws Exception {
        Article newArticle = Article.newArticle("title4", "content4");

        articleRegistry.addArticleToRegistry(newArticle);
        List<Article> actualArticlesInRegistry = articleRegistry.getArticles(Objects::nonNull);

        int expectedSize = 4;
        assertThat(actualArticlesInRegistry, allOf(
                iterableWithSize(expectedSize),
                hasItems(article1, article2, article3, newArticle)));
    }

    @Test
    public void doNotAddArticleThatIsAlreadyInRegistry() throws Exception {
        articleRegistry.addArticleToRegistry(article1);
        List<Article> actualArticlesInRegistry = articleRegistry.getArticles(Objects::nonNull);

        int expectedSize = 3;
        assertThat(actualArticlesInRegistry, allOf(
                iterableWithSize(expectedSize),
                hasItems(article1, article2, article3)));
    }

    @Test(expected = IllegalArgumentException.class)
    public void addNullToRegistryShouldThrowException() throws Exception {
        articleRegistry.addArticleToRegistry(null);
    }

    @Test
    public void getArticlesWithPredicateMatch() throws Exception {
        List<Article> articles = articleRegistry.getArticles(article -> article.getTitle().contains("title12"));

        int expectedSize = 2;
        assertThat(articles, allOf(
                iterableWithSize(expectedSize),
                hasItems(article2, article3),
                not(hasItem(article1))));
    }

    @Test
    public void noPredicateMatchReturnsEmptyCollection() throws Exception {
        List<Article> articles = articleRegistry.getArticles(article -> article.getContent().equals("not a content"));

        assertThat(articles, empty());
    }

    @Test(expected = IllegalArgumentException.class)
    public void nullPredicateShouldThrowException() throws Exception {
        articleRegistry.getArticles(null);
    }
}