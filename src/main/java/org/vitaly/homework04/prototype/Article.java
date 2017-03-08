package org.vitaly.homework04.prototype;

import static org.vitaly.util.InputChecker.TITLE_MUST_NOT_BE_NULL;
import static org.vitaly.util.InputChecker.requireNonNull;

/**
 * Created by vitaly on 2017-03-08.
 */
public class Article implements Cloneable {
    private String title;
    private String content;

    private Article(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public static Article newArticle(String title, String content) {
        requireNonNull(title, TITLE_MUST_NOT_BE_NULL);
        requireNonNull(content, "Content must not be null!");

        return new Article(title, content);
    }

    @Override
    public Article clone() throws CloneNotSupportedException {
        return (Article) super.clone();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Article article = (Article) o;

        return getTitle().equals(article.getTitle()) && getContent().equals(article.getContent());
    }

    @Override
    public int hashCode() {
        int result = getTitle().hashCode();
        result = 31 * result + getContent().hashCode();
        return result;
    }
}
