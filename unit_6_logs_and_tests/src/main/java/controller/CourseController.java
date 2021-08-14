package controller;

import entity.Course;
import entity.Student;
import service.CourseService;
import util.MyArrayList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CourseController {

    private final CourseService courseService = new CourseService();

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
                    return;
                }
                crud(position, reader);
            }
        } catch (IOException e) {
            System.out.println("problem: = " + e.getMessage());
        }
    }

    private void runNavigation() {
        System.out.println();
        System.out.println("if you want create course, please enter 1");
        System.out.println("if you want update course, please enter 2");
        System.out.println("if you want delete course, please enter 3");
        System.out.println("if you want findById course, please enter 4");
        System.out.println("if you want findAll course, please enter 5");
        System.out.println("if you want findStudentsById course, please enter 6");
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
        System.out.println("CourseController.create");
        try {
            System.out.println("Please, enter your course title");
            String courseTitle = reader.readLine();
            System.out.println("Please, enter your credit");
            String credit = reader.readLine();

            MyArrayList<Student> studentMyArrayList = new MyArrayList<>();
            System.out.println("Please, enter count of students");
            String count = reader.readLine();
            for (int i = 0; i < Integer.parseInt(count); i++) {
                System.out.println("Please, enter first name of student");
                String firstName = reader.readLine();
                System.out.println("Please, enter last name of student");
                String lastName = reader.readLine();
                Student student = new Student();
                student.setFirstName(firstName);
                student.setLastName(lastName);
                studentMyArrayList.add(student);
            }

            Course course = new Course();
            course.setCourseTitle(courseTitle);
            course.setCredit(Integer.parseInt(credit));
            course.setStudentList(studentMyArrayList);
            courseService.create(course);
        } catch (Exception e) {
            System.out.println("problem: = " + e.getMessage());
        }
    }

    private void update(BufferedReader reader) {
        System.out.println("CourseController.update");
        try {
            System.out.println("Please, enter id");
            String id = reader.readLine();
            System.out.println("Please, enter your course title");
            String courseTitle = reader.readLine();
            System.out.println("Please, enter your credit");
            String credit = reader.readLine();
            Course course = new Course();
            course.setId(id);
            course.setCourseTitle(courseTitle);
            course.setCredit(Integer.parseInt(credit));
            courseService.update(course);
        } catch (Exception e) {
            System.out.println("problem: = " + e.getMessage());
        }
    }

    private void delete(BufferedReader reader) {
        System.out.println("CourseController.delete");
        try {
            System.out.println("Please, enter id");
            String id = reader.readLine();
            courseService.deleteCourse(id);
        } catch (IOException e) {
            System.out.println("problem: = " + e.getMessage());
        }
    }

    private void findById(BufferedReader reader) {
        System.out.println("CourseController.findById");
        try {
            System.out.println("Please, enter id");
            String id = reader.readLine();
            System.out.println(courseService.findCourseById(id));
        } catch (IOException e) {
            System.out.println("problem: = " + e.getMessage());
        }
    }

    private void findAll() {
        System.out.println("CourseController.findAll");
        MyArrayList<Course> courses = courseService.findAllCourses();
        for (int i = 0; i < courses.size(); i++) {
            System.out.println(courses.get(i));
        }
    }

    private void findAllCoursesOfStudentByStudentId(BufferedReader reader) {
        System.out.println("StudentController.findAllCoursesOfStudentByStudentId");
        try {
            System.out.println("Please, enter id");
            String id = reader.readLine();
            System.out.println(courseService.findAllStudentsOfCourseByCourseId(id));
        } catch (IOException e) {
            System.out.println("problem: = " + e.getMessage());
        }
    }
}
