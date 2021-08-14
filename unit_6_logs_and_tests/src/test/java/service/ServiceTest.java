package service;

import entity.Course;
import entity.Student;
import org.junit.jupiter.api.*;
import util.MyArrayList;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ServiceTest {

    private final StudentService studentService = new StudentService();
    private final CourseService courseService = new CourseService();

    MyArrayList<Course> courses = courseService.findAllCourses();
    MyArrayList<Student> students = studentService.findAllStudents();

    @Test
    @Order(1)
    public void createStudentWithCourseListAndFindAllCourses() {
        Course course1 = CourseGenerationUtil.generateCourse();
        Course course2 = CourseGenerationUtil.generateCourse("Name", 1);

        MyArrayList<Course> c = new MyArrayList<>();
        c.add(course1);
        c.add(course2);

        Student user = StudentGenerationUtil.generateStudent("lastName");
        user.setCourseList(c);
        Course course = CourseGenerationUtil.generateCourse("test");
        courseService.create(course);
        studentService.create(user);
        System.out.println("\nCreate Student With Course List And Find All Courses:");
        printAll(students);
        printAll(courses);
        Assertions.assertEquals(3, courseService.findAllCourses().size());
    }

    @Test
    @Order(2)
    public void deleteOneCourseFromEverywhere() {
        Course course = courses.get(1);
        courseService.deleteCourse(course.getId());
        System.out.println("\nDelete One Course From Everywhere:");
        printAll(students);
        printAll(courses);
        Assertions.assertEquals(2, courseService.findAllCourses().size());
    }

    @Test
    @Order(3)
    public void updateOneCourseEverywhere() {
        MyArrayList<Course> allCourses = courseService.findAllCourses();
        Course course = new Course();
        course.setId(allCourses.get(1).getId());
        course.setCourseTitle("NewName");
        course.setCredit(121);
        courseService.update(course);
        System.out.println("\nUpdate One Course Everywhere:");
        printAll(students);
        printAll(courses);
        Assertions.assertEquals(121, courseService.findCourseById(course.getId()).getCredit());
    }

    @Test
    @Order(4)
    public void findAllCoursesOfStudentByStudentId() {
        System.out.println("\nFind All Courses Of Student By Student Id:");
        MyArrayList<Course> courseMyArrayList = new MyArrayList<>();
        for (int i = 0; i < students.size(); i++) {
            courseMyArrayList = studentService.findAllCoursesOfStudentByStudentId(students.get(i).getId());
        }
        System.out.println(courseMyArrayList);
        Assertions.assertEquals(1, courseMyArrayList.size());
    }

    public void printAll(MyArrayList<?> arrayList) {
        for (int i = 0; i < arrayList.size(); i++) {
            System.out.println(arrayList.get(i));
        }
    }
}
