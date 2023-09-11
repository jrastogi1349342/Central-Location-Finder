package org.finder.route;

import java.util.Arrays;

public class RouteSections {
    private RouteSummary summary;
    private RouteTolls [] tolls;

    public RouteSummary getSummary() {
        return summary;
    }

    public void setSummary(RouteSummary summary) {
        this.summary = summary;
    }

    public RouteTolls [] getTolls() {
        return tolls;
    }

    public void setTolls(RouteTolls [] tolls) {
        this.tolls = tolls;
    }

    @Override
    public String toString() {
        return "RouteSections{" +
                "summary=" + summary +
                ", tolls=" + Arrays.toString(tolls) +
                '}';
    }
}
