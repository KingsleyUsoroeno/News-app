package techgroup.com.news24.Models;

import com.google.gson.annotations.SerializedName;

public class BusStop {
    @SerializedName("id")
    private int id;

    @SerializedName("terminal_id")
    private int terminal_id;

    @SerializedName("bus_schedule_id")
    private int bus_schedule;

    @SerializedName("cost")
    private int cost;


    @SerializedName("created_at")
    private String created_at;

    @SerializedName("updated_at")
    private String updated_at;

    public BusStop(int id, int terminal_id,int bus_schedule, int cost, String created_at, String updated_at) {

        this.id = id;
        this.terminal_id = terminal_id;
        this.bus_schedule = bus_schedule;
        this.cost = cost;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTerminal_id() {
        return terminal_id;
    }

    public void setTerminal_id(int terminal_id) {
        this.terminal_id = terminal_id;
    }

    public int getBus_schedule() {
        return bus_schedule;
    }

    public void setBus_schedule(int bus_schedule) {
        this.bus_schedule = bus_schedule;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
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


}
