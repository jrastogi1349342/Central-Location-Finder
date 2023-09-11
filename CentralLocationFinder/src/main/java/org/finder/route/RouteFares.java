package org.finder.route;

public class RouteFares {
    private RoutePrice price;

    public RoutePrice getPrice() {
        return price;
    }

    public void setPrice(RoutePrice price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "RouteFares{" +
                "price=" + price +
                '}';
    }
}
