package com.penimalla.servlet;

public class Problem {
    private int id;
    private String issueTitle;
    private String category;
    private String priority;
    private String mobile;
    private String area;
    private String location;
    private String description;
    private String reporter;
    private String imagePath;
    private double latitude;
    private double longitude;

    // Constructors, getters, setters below...
    public Problem() {}
    public Problem(int id, String issueTitle, String category, String priority, String mobile, String area, String location,
                   String description, String reporter, String imagePath, double latitude, double longitude) {
        this.id = id;
        this.issueTitle = issueTitle;
        this.category = category;
        this.priority = priority;
        this.mobile = mobile;
        this.area = area;
        this.location = location;
        this.description = description;
        this.reporter = reporter;
        this.imagePath = imagePath;
        this.latitude = latitude;
        this.longitude = longitude;
    }
    // Getters and setters...
    // (implement for all fields)
}
