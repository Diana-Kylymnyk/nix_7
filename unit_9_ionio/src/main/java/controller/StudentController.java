package controller;

import entity.Course;
import entity.Student;
import service.StudentService;
import util.MyArrayList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class StudentController {

    private final StudentService studentService = new StudentService();

    public void start() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Select your option");
        String position;
        try {
            runNavigation();
            while ((position = reader.readLine()) != null) {
                crud(position, reader);
                position = reader.readLine();
                if (position.equals("0")) {
                    break;
                }
                crud(position, reader);
            }
        } catch (IOException e) {
            System.out.println("problem: = " + e.getMessage());
        }
    }

    private void runNavigation() {
        System.out.println();
        System.out.println("if you want create student, please enter 1");
        System.out.println("if you want update student, please enter 2");
        System.out.println("if you want delete student, please enter 3");
        System.out.println("if you want findById student, please enter 4");
        System.out.println("if you want findAll student, please enter 5");
        System.out.println("if you want findCoursesById student, please enter 6");
        System.out.println("if you want exit, please enter 0");
        System.out.println();
    }

    private void crud(String position, BufferedReader reader) {
        switch (position) {
            case "1":
                create(reader);
                break;
            case "2":
                update(reader);
                break;
            case "3":
                delete(reader);
                break;
            case "4":
                findById(reader);
                break;
            case "5":
                findAll();
                break;
            case "6":
                findAllCoursesOfStudentByStudentId(reader);
                break;
        }
        runNavigation();
    }

    private void create(BufferedReader reader) {
        System.out.println("StudentController.create");
        try {
            System.out.println("Please, enter your firstName");
            String firstName = reader.readLine();
            System.out.println("Please, enter your lastName");
            String lastName = reader.readLine();

            Student student = new Student();
            student.setFirstName(firstName);
            student.setLastName(lastName);
            studentService.create(student);
        } catch (Exception e) {
            System.out.println("problem: = " + e.getMessage());
        }
    }

    private void update(BufferedReader reader) {
        System.out.println("StudentController.update");
        try {
            System.out.println("Please, enter id");
            String id = reader.readLine();
            System.out.println("Please, enter your first name");
            String firstName = reader.readLine();
            System.out.println("Please, enter your last name");
            String lastName = reader.readLine();
            Student student = new Student();
            student.setId(id);
            student.setFirstName(firstName);
            student.setLastName(lastName);
            studentService.update(student);
        } catch (IOException e) {
            System.out.println("problem: = " + e.getMessage());
        }
    }

    private void delete(BufferedReader reader) {
        System.out.println("StudentController.delete");
        try {
            System.out.println("Please, enter id");
            String id = reader.readLine();
            studentService.deleteStudent(id);
        } catch (IOException e) {
            System.out.println("problem: = " + e.getMessage());
        }
    }

    private void findById(BufferedReader reader) {
        System.out.println("StudentController.findById");
        try {
            System.out.println("Please, enter id");
            String id = reader.readLine();
            System.out.println(studentService.findStudentById(id));
        } catch (IOException e) {
            System.out.println("problem: = " + e.getMessage());
        }
    }

    private void findAll() {
        System.out.println("StudentController.findAll");
        MyArrayList<Student> students = studentService.findAllStudents();
        for (int i = 0; i < students.size(); i++) {
            System.out.println(students.get(i));
        }
    }

    private void findAllCoursesOfStudentByStudentId(BufferedReader reader) {
        System.out.println("StudentController.findAllCoursesOfStudentByStudentId");
        try {
            System.out.println("Please, enter id");
            String id = reader.readLine();
            System.out.println(studentService.findAllCoursesOfStudentByStudentId(id));
        } catch (IOException e) {
            System.out.println("problem: = " + e.getMessage());
        }
    }
}
