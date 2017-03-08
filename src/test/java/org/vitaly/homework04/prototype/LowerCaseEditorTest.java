package org.vitaly.homework04.prototype;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.isEmptyString;

/**
 * Created by vitaly on 2017-03-08.
 */
public class LowerCaseEditorTest {
    private ArticleRegistry articleRegistry;
    private Editor editor;
    private String title;
    private String content;
    private Article article;

    @Before
    public void setUp() throws Exception {
        title = "art";
        content = "ICLE";
        article = Article.newArticle(title, content);
        articleRegistry = new ArticleRegistry();
        articleRegistry.addArticleToRegistry(article);
        editor = new LowerCaseEditor();
    }

    @Test
    public void edit() throws Exception {
        List<Article> articles = articleRegistry.getArticles(article1 -> article1.getTitle().equals(title));
        Article editedArticle = editor.edit(articles.get(0));

        assertThat(editedArticle.getContent(), allOf(
                not(isEmptyString()),
                not(equalTo(content)),
                equalTo(content.toLowerCase())));
    }

    @Test
    public void editDoesNotModifyOriginalArticle() throws Exception {
        List<Article> articles = articleRegistry.getArticles(article1 -> article1.getTitle().equals(title));
        Article editedArticle = editor.edit(articles.get(0));

        assertThat(editedArticle, allOf(
                not(equalTo(article)),
                not(sameInstance(article))));
    }

    @Test(expected = IllegalArgumentException.class)
    public void editNullArticleShouldThrowException() throws Exception {
        editor.edit(null);
    }
}