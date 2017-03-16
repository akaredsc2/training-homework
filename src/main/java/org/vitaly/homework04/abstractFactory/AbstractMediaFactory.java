package org.vitaly.homework04.abstractFactory;

/**
 * Created by vitaly on 2017-03-16.
 */
public interface AbstractMediaFactory {
    Audio createAudio(String name, String language);

    Image createImage(String name);

    Text createText(String name, String language);
}
