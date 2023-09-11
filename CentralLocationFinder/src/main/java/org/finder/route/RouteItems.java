package org.finder.route;

import java.util.Arrays;

public class RouteItems {
    private RouteSections [] sections;

    public RouteSections[] getSections() {
        return sections;
    }

    public void setSections(RouteSections[] sections) {
        this.sections = sections;
    }

    @Override
    public String toString() {
        return "RouteItems{" +
                "sections=" + Arrays.toString(sections) +
                '}';
    }
}
