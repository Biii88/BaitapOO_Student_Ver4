package baitapoop_student_ver4;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Processor {
    private static final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PersonList personList = new PersonList();

        while (true) {
            System.out.println("1. Add a new student");
            System.out.println("2. Add a new teacher");
            System.out.println("3. Update a person by ID");
            System.out.println("4. Delete a person by ID");
            System.out.println("5. Find a person by ID");
            System.out.println("6. Display all students and teachers");
            System.out.println("7. Find the student with the highest GPA");
            System.out.println("8. Find teachers by department");
            System.out.println("9. Exit");
            System.out.print("Choose an option: ");
            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    addStudent(scanner, personList);
                    break;
                case 2:
                    addTeacher(scanner, personList);
                    break;
                case 3:
                    updatePerson(scanner, personList);
                    break;
                case 4:
                    deletePerson(scanner, personList);
                    break;
                case 5:
                    findPersonById(scanner, personList);
                    break;
                case 6:
                    personList.displayEveryone();
                    break;
                case 7:
                    findTopStudent(personList);
                    break;
                case 8:
                    findTeachersByDepartment(scanner, personList);
                    break;
                case 9:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void addStudent(Scanner scanner, PersonList personList) {
        System.out.print("ID: ");
        String id = scanner.nextLine();
        System.out.print("Full name: ");
        String fullName = scanner.nextLine();
        System.out.print("Date of birth (dd/MM/yyyy): ");
        Date dateOfBirth;
        try {
            dateOfBirth = sdf.parse(scanner.nextLine());
        } catch (ParseException e) {
            System.out.println("Invalid date format. Please use dd/MM/yyyy.");
            return;
        }
        System.out.print("GPA: ");
        float GPA;
        try {
            GPA = Float.parseFloat(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid GPA format.");
            return;
        }
        System.out.print("Major: ");
        String major = scanner.nextLine();

        Student student = new Student(id, fullName, dateOfBirth, GPA, major);
        personList.addStudent(student);
        System.out.println("Student added successfully.");
    }

    private static void addTeacher(Scanner scanner, PersonList personList) {
        System.out.print("ID: ");
        String id = scanner.nextLine();
        System.out.print("Full name: ");
        String fullName = scanner.nextLine();
        System.out.print("Date of birth (dd/MM/yyyy): ");
        Date dateOfBirth;
        try {
            dateOfBirth = sdf.parse(scanner.nextLine());
        } catch (ParseException e) {
            System.out.println("Invalid date format. Please use dd/MM/yyyy.");
            return;
        }
        System.out.print("Department: ");
        String department = scanner.nextLine();
        System.out.print("Teaching Subject: ");
        String teachingSubject = scanner.nextLine();

        Teacher teacher = new Teacher(id, fullName, dateOfBirth, department, teachingSubject);
        personList.addTeacher(teacher);
        System.out.println("Teacher added successfully.");
    }

    private static void updatePerson(Scanner scanner, PersonList personList) {
        System.out.print("Enter ID of the person to update: ");
        String updateId = scanner.nextLine();
        Person existingPerson = personList.findPersonById(updateId);
        if (existingPerson == null) {
            System.out.println("Person not found.");
            return;
        }
        System.out.print("Enter new Name: ");
        String newName = scanner.nextLine();
        existingPerson.setFullName(newName);

        System.out.print("Enter new Date of birth (dd/MM/yyyy): ");
        try {
            Date newDateOfBirth = sdf.parse(scanner.nextLine());
            existingPerson.setDateOfBirth(newDateOfBirth);
        } catch (ParseException e) {
            System.out.println("Invalid date format. Please use dd/MM/yyyy.");
            return;
        }

        if (existingPerson instanceof Student) {
            Student existingStudent = (Student) existingPerson;
            System.out.print("Enter new GPA: ");
            float newGPA;
            try {
                newGPA = Float.parseFloat(scanner.nextLine());
                existingStudent.setGPA(newGPA);
            } catch (NumberFormatException e) {
                System.out.println("Invalid GPA format.");
                return;
            }
            System.out.print("Enter new Major: ");
            String newMajor = scanner.nextLine();
            existingStudent.setMajor(newMajor);
            System.out.println("Student updated successfully.");
        } else if (existingPerson instanceof Teacher) {
            Teacher existingTeacher = (Teacher) existingPerson;
            System.out.print("Enter new Department: ");
            String newDepartment = scanner.nextLine();
            existingTeacher.setDepartment(newDepartment);
            System.out.print("Enter new Teaching Subject: ");
            String newTeachingSubject = scanner.nextLine();
            existingTeacher.setTeachingSubject(newTeachingSubject);
            System.out.println("Teacher updated successfully.");
        }
    }

    private static void deletePerson(Scanner scanner, PersonList personList) {
        System.out.print("Enter ID of the person to delete: ");
        String deleteId = scanner.nextLine();
        personList.deletePersonById(deleteId);
        System.out.println("Person deleted successfully.");
    }

    private static void findPersonById(Scanner scanner, PersonList personList) {
        System.out.print("Enter ID of the person to find: ");
        String findId = scanner.nextLine();
        Person foundPerson = personList.findPersonById(findId);
        if (foundPerson != null) {
            System.out.println("Person found:");
            foundPerson.displayInfo();
        } else {
            System.out.println("Person not found.");
        }
    }

    private static void findTopStudent(PersonList personList) {
        Student topStudent = personList.findTopStudent();
        if (topStudent != null) {
            System.out.println("Top Student:");
            topStudent.displayInfo();
        } else {
            System.out.println("No students available.");
        }
    }

    private static void findTeachersByDepartment(Scanner scanner, PersonList personList) {
        System.out.print("Enter Department to find teachers: ");
        String department = scanner.nextLine();
        ArrayList<Teacher> teachers = personList.findTeacherByDepartment(department);
        if (teachers != null && !teachers.isEmpty()) {
            System.out.println("Teachers found:");
            for (Teacher teacher : teachers) {
                teacher.displayInfo();
            }
        } else {
            System.out.println("No teachers found in this department.");
        }
    }
}