package techgroup.com.news24.Models;

import com.google.gson.annotations.SerializedName;

public class Route {
    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("from_terminal_id")
    private String from_terminal_id;

    @SerializedName("to_terminal_id")
    private String to_terminal_id;

    public Route(int id, String name, String from_terminal_id, String to_terminal_id) {

        this.id = id;
        this.name = name;
        this.from_terminal_id = from_terminal_id;
        this.to_terminal_id = to_terminal_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFrom_terminal_id() {
        return from_terminal_id;
    }

    public void setFrom_terminal_id(String from_terminal_id) {
        this.from_terminal_id = from_terminal_id;
    }

    public String getTo_terminal_id() {
        return to_terminal_id;
    }

    public void setTo_terminal_id(String to_terminal_id) {
        this.to_terminal_id = to_terminal_id;
    }

}
