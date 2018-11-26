package techgroup.com.news24.Models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class BusSchedule {
    @SerializedName("id")
    private int id;

    @SerializedName("departure_date_time")
    private String departure_date_time;

    @SerializedName("arrival_date_time")
    private String arrival_date_time;

    @SerializedName("cost")
    private int cost;

    @SerializedName("available_seats")
    private int available_seats;

    @SerializedName("route_id")
    private int route_id;

    @SerializedName("fleet_id")
    private int fleet_id;

    @SerializedName("created_at")
    private String created_at;

    @SerializedName("updated_at")
    private String updated_at;

    @SerializedName("bus_stops")
    private List<BusStop> busStop;

    @SerializedName("routes")
    private Route routes;

    public BusSchedule(int id, String departure_date_time, String arrival_date_time, int cost,
                       int available_seats, int route_id, int fleet_id, String created_at, String updated_at,
                       List<BusStop> busStop, Route routes ) {
        this.id = id;
        this.departure_date_time = departure_date_time;
        this.arrival_date_time = arrival_date_time;
        this.cost = cost;
        this.available_seats = available_seats;
        this.route_id = route_id;
        this.fleet_id = fleet_id;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.busStop = busStop;
        this.routes = routes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDeparture_date_time() {
        return departure_date_time;
    }

    public void setDeparture_date_time(String departure_date_time) {
        this.departure_date_time = departure_date_time;
    }

    public String getArrival_date_time() {
        return arrival_date_time;
    }

    public void setArrival_date_time(String arrival_date_time) {
        this.arrival_date_time = arrival_date_time;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getAvailable_seats() {
        return available_seats;
    }

    public void setAvailable_seats(int available_seats) {
        this.available_seats = available_seats;
    }

    public int getRoute_id() {
        return route_id;
    }

    public void setRoute_id(int route_id) {
        this.route_id = route_id;
    }

    public int getFleet_id() {
        return fleet_id;
    }

    public void setFleet_id(int fleet_id) {
        this.fleet_id = fleet_id;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public List<BusStop> getBusStop() {
        return busStop;
    }

    public void setBusStop(ArrayList<BusStop> busStop) {
        this.busStop = busStop;
    }

    public Route getRoutes() {
        return routes;
    }

    public void setRoutes(Route routes) {
        this.routes = routes;
    }
}
