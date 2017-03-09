package org.vitaly.homework04.prototype;

/**
 * Created by vitaly on 2017-03-08.
 */
@FunctionalInterface
public interface Editor {
    Article edit(Article article) throws CloneNotSupportedException;
}
