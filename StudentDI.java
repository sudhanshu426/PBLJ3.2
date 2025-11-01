package com.example.app.di;

public class StudentDI {
    private Course course;

    public StudentDI(Course course) {
        this.course = course;
    }

    public void showDetails() {
        System.out.println("Student details:");
        course.displayCourse();
    }
}
