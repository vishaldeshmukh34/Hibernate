package com.student;

import jakarta.persistence.*;

@Entity
@Table(name="student")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String city;
    private int marks;

    public Student() {}

    public Student(String name, String city, int marks) {
        this.name = name;
        this.city = city;
        this.marks = marks;
    }

    // getters & setters
    public int getId() { return id; }
    public String getName() { return name; }
    public String getCity() { return city; }
    public int getMarks() { return marks; }

    public void setName(String name) { this.name = name; }
    public void setCity(String city) { this.city = city; }
    public void setMarks(int marks) { this.marks = marks; }
}
