package org.vitaly.homework04.prototype;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * Created by vitaly on 2017-03-08.
 */
public class WordShufflingEditorTest {
    private ArticleRegistry articleRegistry;
    private Editor editor;
    private String title;
    private Article article;

    @Before
    public void setUp() throws Exception {
        title = "art";
        String content = "word1 word2 word3";
        article = Article.newArticle(title, content);
        articleRegistry = new ArticleRegistry();
        articleRegistry.addArticleToRegistry(article);
        editor = new WordShufflingEditor();
    }

    @Test
    public void edit() throws Exception {
        List<Article> articles = articleRegistry.getArticles(article1 -> article1.getTitle().equals(title));
        Article originalArticle = articles.get(0);
        String[] originalWords = originalArticle.getContent().split("\\s+");

        Article editedArticle = editor.edit(originalArticle);
        String[] shuffledWords = editedArticle.getContent().split("\\s+");

        int expectedWordsCount = originalWords.length;
        assertThat(shuffledWords, allOf(
                not(emptyArray()),
                arrayWithSize(expectedWordsCount),
                arrayContainingInAnyOrder(originalWords)));
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