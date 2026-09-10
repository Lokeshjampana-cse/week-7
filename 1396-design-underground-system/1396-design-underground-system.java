import java.util.HashMap;
import java.util.Map;

class UndergroundSystem {

    // id -> [stationName, checkInTime]
    private Map<Integer, CheckInData> checkIns;

    // "start,end" -> [totalTime, numberOfTrips]
    private Map<String, RouteData> routes;

    public UndergroundSystem() {
        checkIns = new HashMap<>();
        routes = new HashMap<>();
    }

    public void checkIn(int id, String stationName, int t) {
        checkIns.put(id, new CheckInData(stationName, t));
    }

    public void checkOut(int id, String stationName, int t) {
        CheckInData data = checkIns.get(id);

        String route = data.station + "," + stationName;

        int travelTime = t - data.time;

        if (!routes.containsKey(route)) {
            routes.put(route, new RouteData());
        }

        RouteData routeData = routes.get(route);

        routeData.totalTime += travelTime;
        routeData.trips++;
    }

    public double getAverageTime(String startStation, String endStation) {
        String route = startStation + "," + endStation;

        RouteData routeData = routes.get(route);

        return (double) routeData.totalTime / routeData.trips;
    }

    // Stores customer's check-in information
    class CheckInData {
        String station;
        int time;

        CheckInData(String station, int time) {
            this.station = station;
            this.time = time;
        }
    }

    // Stores information about a route
    class RouteData {
        int totalTime = 0;
        int trips = 0;
    }
}
