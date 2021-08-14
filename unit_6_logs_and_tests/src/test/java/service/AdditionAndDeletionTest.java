package service;

import entity.Student;
import org.junit.jupiter.api.*;
import util.MyArrayList;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AdditionAndDeletionTest {

    private final static StudentService studentService = new StudentService();
    private final static int STUDENTS_SIZE = 10;

    @BeforeAll
    public static void setUp() {
        for (int i = 0; i < STUDENTS_SIZE; i++) {
            Student student = StudentGenerationUtil.generateStudent(StudentGenerationUtil.NAME + i);
            studentService.create(student);
        }
        Assertions.assertEquals(STUDENTS_SIZE, studentService.findAllStudents().size());
    }

    @Test
    @Order(2)
    public void createStudent() {
        Student user = StudentGenerationUtil.generateStudent("lastName");
        studentService.create(user);
        MyArrayList<Student> students = studentService.findAllStudents();
        Assertions.assertEquals(STUDENTS_SIZE + 1, students.size());
    }

    @Test
    @Order(3)
    public void findAll() {
        MyArrayList<Student> users = studentService.findAllStudents();
        Assertions.assertEquals(STUDENTS_SIZE + 1, users.size());
    }

    @Test
    @Order(4)
    public void delete() {
        MyArrayList<Student> students = studentService.findAllStudents();
        Assertions.assertEquals(STUDENTS_SIZE + 1, students.size());
        for (int i = 0; i < 5; i++) {
            studentService.deleteStudent(students.get(i).getId());
        }
        students = studentService.findAllStudents();
        Assertions.assertEquals(STUDENTS_SIZE - 4, students.size());
    }
}
