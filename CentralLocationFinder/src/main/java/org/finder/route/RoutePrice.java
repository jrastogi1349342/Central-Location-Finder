package org.finder.route;

public class RoutePrice {
    private String currency;
    private float value;

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "RoutePrice{" +
                "currency='" + currency + '\'' +
                ", value=" + value +
                '}';
    }
}
