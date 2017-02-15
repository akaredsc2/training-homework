package org.vitaly.week03.modeling;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vitaly on 15.02.17.
 */
public class Ocean {
    private String name;
    private List<Island> islandList;

    public Ocean(String name) {
        this.name = name;
        this.islandList = new ArrayList<>();
    }

    public String getName() {
        return name;
    }
}
