package org.finder.location;

public class LocationAddress {
    private String street;
    private String city;
    private String state;
    private String country;
    private int postalcode;

    public LocationAddress(String street, String city, String state, String country, int postalcode) {
        setStreet(street);
        setCity(city);
        setState(state);
        setCountry(country);
        setPostalcode(postalcode);
    }

    private String convertStr(String input) {
        String [] words = input.trim().split(" ");
        StringBuilder adjStr = new StringBuilder();
        for (int i = 0; i < words.length - 1; i++) {
            adjStr.append(words[i]).append("+");
        }
        adjStr.append(words[words.length - 1]);

        return adjStr.toString();
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = convertStr(street);
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = convertStr(city);
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = convertStr(state);
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = convertStr(country);
    }

    public int getPostalcode() {
        return postalcode;
    }

    public void setPostalcode(int postalcode) {
        this.postalcode = postalcode;
    }

    @Override
    public String toString() {
        return street + "%2C+" + city + "+" + state + "+" + country + "+" + postalcode;
    }
}
