package com.hit.coursesmanagement.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity(name="students")
public class StudentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "age")
    private int age;

    @OneToMany(mappedBy = "student")
    private List<RegistrationEntity> student;

    public int getId() {
        return id;
    }

    public List<RegistrationEntity> getStudent() {
        return student;
    }

    public void setStudent(List<RegistrationEntity> student) {
        this.student = student;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
