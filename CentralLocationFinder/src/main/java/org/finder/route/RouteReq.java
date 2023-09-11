package org.finder.route;

import java.util.Arrays;

public class RouteReq {
    private RouteItems [] routes;

    public RouteItems[] getRoutes() {
        return routes;
    }

    public void setRoutes(RouteItems[] routes) {
        this.routes = routes;
    }

    @Override
    public String toString() {
        return "RouteReq{" +
                "routes=" + Arrays.toString(routes) +
                '}';
    }
}
