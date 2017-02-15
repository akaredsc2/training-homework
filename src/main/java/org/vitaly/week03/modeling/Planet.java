package org.vitaly.week03.modeling;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vitaly on 15.02.17.
 */
public class Planet {
    private String name;
    private List<Continent> continentList;
    private List<Ocean> oceanList;

    public Planet(String name) {
        this.name = name;
        this.continentList = new ArrayList<>();
        this.oceanList = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public int getContinentCount() {
        return continentList.size();
    }

    public List<String> getContinentNames() {
        List<String> continentNames = new ArrayList<>();
        for (Continent continent : continentList) {
            continentNames.add(continent.getName());
        }
        return continentNames;
    }

    public void addContinent(Continent continent) {
        if (continent != null) {
            continentList.add(continent);
        }
    }

    public void addOcean(Ocean ocean) {
        if (ocean != null) {
            oceanList.add(ocean);
        }
    }
}
