package com.example.volunteerpanel.ui.showevent;

public class Event {
    private long id;
    private String name;
    private String date;
    private String location;
    private String description;

    public Event(long id, String name, String date, String location, String description, String string) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.location = location;
        this.description = description;
    }

    // Getters and Setters
    public long getId() { return id; }
    public String getName() { return name; }
    public String getDate() { return date; }
    public String getLocation() { return location; }
    public String getDescription() { return description; }

    public void setId(long id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setDate(String date) { this.date = date; }
    public void setLocation(String location) { this.location = location; }
    public void setDescription(String description) { this.description = description; }
}
