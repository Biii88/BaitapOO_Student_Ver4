package baitapoop_student_ver4;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Student extends Person {
    private float GPA;
    private String major;

    public Student(String id, String fullName, Date dateOfBirth, float GPA, String major) {
        super(id, fullName, dateOfBirth);
        this.GPA = GPA;
        this.major = major;
    }

    public Student() {}

    public float getGPA() {
        return GPA;
    }

    public void setGPA(float GPA) {
        this.GPA = GPA;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public void addPerson() {
        // Implement logic to add student if needed
    }

    public void updatePerson(String id) {
        // Implement logic to update student if needed
    }

    public void displayInfo() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String formattedDate = sdf.format(getDateOfBirth());
        System.out.println("ID: " + getId());
        System.out.println("Full name: " + getFullName());
        System.out.println("Date of birth: " + formattedDate);
        System.out.println("GPA: " + getGPA());
        System.out.println("Major: " + getMajor());
    }
}