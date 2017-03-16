package org.vitaly.homework04.abstractFactory;

/**
 * Created by vitaly on 2017-03-16.
 */
public class Video implements Image {
    private String name;

    public Video(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String play() {
        return "Playing " + name + " video";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Video video = (Video) o;

        return name != null ? name.equals(video.name) : video.name == null;
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }
}
