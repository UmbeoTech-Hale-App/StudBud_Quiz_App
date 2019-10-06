package com.ankita.studbud;

public class Courses {
    private String courseName;
    private String courseDescription;

    public Courses(String name, String description) {
        this.courseName = name;
        this.courseDescription = description;
    }

    public String getCourseName() {
        return this.courseName;
    }

    public String getCourseDescription() {
        return courseDescription;
    }
}
