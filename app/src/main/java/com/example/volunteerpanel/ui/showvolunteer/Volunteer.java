package com.example.volunteerpanel.ui.showvolunteer;

public class Volunteer {
    private long id;
    private String name;
    private String email;
    private String phone;
    private long collegeId;
    private String collegeName;

    // Constructor
    public Volunteer(long id, String name, String email, String phone, String collegeName) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.collegeId = collegeId;
        this.collegeName = collegeName;
    }

    // Getters
    public long getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }
    public long getCollegeId() { return collegeId; }
    public String getCollegeName() { return collegeName; }

    // Setters
    public void setId(long id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setEmail(String email) { this.email = email; }
    public void setPhone(String phone) { this.phone = phone; }
    public void setCollegeId(long collegeId) { this.collegeId = collegeId; }
    public void setCollegeName(String collegeName) { this.collegeName = collegeName; }
}
