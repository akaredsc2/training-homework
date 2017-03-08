package org.vitaly.homework04.prototype;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.core.AllOf.allOf;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

/**
 * Created by vitaly on 2017-03-08.
 */
public class ArticleTest {
    private Article article;
    private String title;
    private String content;

    @Before
    public void setUp() throws Exception {
        title = "title";
        content = "bla-bla-bla";
        article = Article.newArticle(title, content);
    }

    @Test
    public void newArticle() throws Exception {
        Article newArticle = Article.newArticle(title, content);

        assertThat(newArticle, allOf(
                notNullValue(),
                equalTo(article),
                not(sameInstance(article))));
    }

    @Test(expected = IllegalArgumentException.class)
    public void newArticleWithNullTitleShouldThrowException() throws Exception {
        Article.newArticle(null, content);
    }

    @Test(expected = IllegalArgumentException.class)
    public void newArticleWithNullContentShouldThrowException() throws Exception {
        Article.newArticle(title, null);
    }

    @Test
    public void cloneReturnsDifferentInstance() throws Exception {
        Article clone = article.clone();

        assertThat(clone, allOf(
                notNullValue(),
                equalTo(article),
                not(sameInstance(article))));
    }

    @Test
    public void changingCloneDoesNotAffectOriginalObject() throws Exception {
        Article clone = article.clone();

        clone.setContent(content.toUpperCase());

        assertThat(article.getContent(), allOf(
                not(sameInstance(clone.getContent())),
                not(equalTo(clone.getContent())),
                equalTo(content)));
    }
}