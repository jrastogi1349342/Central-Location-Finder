package org.finder.route;

public class RouteTolls {
    private RouteFares fares;

    public RouteFares getFares() {
        return fares;
    }

    public void setFares(RouteFares fares) {
        this.fares = fares;
    }

    @Override
    public String toString() {
        return "RouteTolls{" +
                "fares=" + fares +
                '}';
    }
}
