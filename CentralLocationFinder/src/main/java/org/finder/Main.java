package org.finder;

import com.google.gson.Gson;
import org.finder.browse.BrowseReq;
import org.finder.geocode.GeocodeReq;
import org.finder.location.LocationAddress;
import org.finder.location.LocationCoords;
import org.finder.route.RouteReq;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;

//Complete in this state, though can be converted into JSP app
public class Main {
    private static ArrayList<LocationAddress> addresses = new ArrayList<>();
    private static ArrayList<LocationCoords> coords = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        GeocodeReq geocodeReq;
        HttpClient httpClient = HttpClient.newHttpClient();
        Gson gson = new Gson();

        addresses.add(new LocationAddress(Constants.streetOne, Constants.cityOne,
                Constants.stateOne, Constants.countryOne, Constants.postalCodeOne));
        addresses.add(new LocationAddress(Constants.streetTwo, Constants.cityTwo,
                Constants.stateTwo, Constants.countryTwo, Constants.postalCodeTwo));

        for (LocationAddress address : addresses) {
            HttpRequest getCoords = HttpRequest.newBuilder()
                    .uri(new URI("https://geocode.search.hereapi.com/v1/geocode?q=" +
                            address.toString() + "&apiKey=" + Constants.API_KEY))
                    .build();

            HttpResponse<String> getCoordsResponse = httpClient.send(getCoords,
                    HttpResponse.BodyHandlers.ofString());

            geocodeReq = gson.fromJson(getCoordsResponse.body(), GeocodeReq.class);
            coords.add(geocodeReq.getItems()[0].getPosition());
        }

//        System.out.println(coords.get(0).toString());

        LocationCoords avgLoc = avgCoords();
        BrowseReq browseReq;
        int limit = 3;

        //3 parks closest to given avg location, except for sports fields
        HttpRequest getParks = HttpRequest.newBuilder()
                .uri(new URI("https://browse.search.hereapi.com/v1/browse?at=" +
                        avgLoc + "&limit=" + limit + "&categories=550-5510,!550-5510-0203&" +
                        "apiKey=h03GmTysvqs_8rakufET-xUT2N2arnysLwj8_t9232M"))
                .build();

        HttpResponse<String> getParksResponse = httpClient.send(getParks,
                HttpResponse.BodyHandlers.ofString());

        browseReq = gson.fromJson(getParksResponse.body(), BrowseReq.class);

//        for (BrowseItems item : browseReq.getItems()) {
//            System.out.println(item.getPosition());
//        }

        RouteReq routeReq;
        int bestLocationIdx = -1;
        float minTimeMean = Float.MAX_VALUE;
        double minTimeSD = Double.MAX_VALUE;

        for (int i = 0; i < browseReq.getItems().length; i++) {
            float currTimeMean = 0F;
            ArrayList<Integer> durations = new ArrayList<>();
            for (LocationCoords coord : coords) {
                HttpRequest getTimes = HttpRequest.newBuilder()
                        .uri(new URI("https://router.hereapi.com/v8/routes?transportMode=car&origin=" +
                                coord + "&destination=" + browseReq.getItems()[i].getPosition().toString() +
                                "&return=summary,tolls&apikey=h03GmTysvqs_8rakufET-xUT2N2arnysLwj8_t9232M"))
                        .build();

                HttpResponse<String> getTimesResponse = httpClient.send(getTimes,
                        HttpResponse.BodyHandlers.ofString());

                routeReq = gson.fromJson(getTimesResponse.body(), RouteReq.class);

                durations.add(routeReq.getRoutes()[0].getSections()[0].getSummary().getDuration());

                currTimeMean += durations.get(durations.size() - 1);
            }

            currTimeMean /= (float) addresses.size();
            
            double currTimeSD = 0F;
            for (Integer duration : durations) {
                currTimeSD += Math.pow(duration - currTimeMean, 2);
            }

            currTimeSD /= addresses.size();
            currTimeSD = Math.sqrt(currTimeSD);

            if (currTimeMean < minTimeMean && currTimeSD < minTimeSD) {
                minTimeMean = currTimeMean;
                minTimeSD = currTimeSD;
                bestLocationIdx = i;
            }
        }

        System.out.println("Minimum mean time: " + (minTimeMean / 60) + " minutes");
        System.out.println("Minimum SD time: " + (minTimeSD / 60) + " minutes");
        System.out.println("Location idx: " + bestLocationIdx);
        System.out.println("Coordinates of optimal location: " +
                browseReq.getItems()[bestLocationIdx].getPosition().toString());
    }

    private static LocationCoords avgCoords() {
        float lat = 0F;
        float lng = 0F;
        for (LocationCoords coords : coords) {
            lat += coords.getLat();
            lng += coords.getLng();
        }

        LocationCoords avgCoords = new LocationCoords();
        avgCoords.setLat(lat / (float) coords.size());
        avgCoords.setLng(lng / (float) coords.size());

        return avgCoords;
    }
}