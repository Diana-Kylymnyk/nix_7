package db;

import entity.Course;
import entity.Student;
import util.MyArrayList;

import java.util.UUID;

public class DBInMemory {

    private static DBInMemory instance;

    private final MyArrayList<Student> students = new MyArrayList<>();
    private final MyArrayList<Course> courses = new MyArrayList<>();

    private DBInMemory() {
    }

    public static DBInMemory getInstance() {
        if (instance == null) {
            instance = new DBInMemory();
        }
        return instance;
    }

    public void createStudent(Student student) {
        student.setId(generateId(Entity.STUDENT));
        students.add(student);
    }

    public void createCourse(Course course) {
        course.setId(generateId(Entity.COURSE));
        courses.add(course);
    }

    public void updateStudent(Student student) {
        Student inDbStudent = findStudentById(student.getId());
        inDbStudent.setFirstName(student.getFirstName());
        inDbStudent.setLastName(student.getLastName());
    }

    public void updateCourse(Course course) {
        Course inDbCourse = findCourseById(course.getId());
        inDbCourse.setCourseTitle(course.getCourseTitle());
        inDbCourse.setCredit(course.getCredit());
    }

    public void deleteStudent(String id) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getId().equals(id)) {
                students.remove(i);
            }
        }
    }

    public void deleteCourse(String id) {
        for (int i = 0; i < courses.size(); i++) {
            if (courses.get(i).getId().equals(id)) {
                courses.remove(i);
            }
        }
    }

    public Student findStudentById(String id) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getId().equals(id)) {
                return students.get(i);
            }
        }
        return null;
    }

    public Course findCourseById(String id) {
        for (int i = 0; i < courses.size(); i++) {
            if (courses.get(i).getId().equals(id)) {
                return courses.get(i);
            }
        }
        return null;
    }

    public MyArrayList<Student> findAllStudents() {
        return students;
    }

    public MyArrayList<Course> findAllCourses() {
        return courses;
    }

    public boolean existStudentById(String id) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getId().equals(id)) {
                return true;
            }
        }
        return false;
    }

    public boolean existCourseById(String id) {
        for (int i = 0; i < courses.size(); i++) {
            if (courses.get(i).getId().equals(id)) {
                return true;
            }
        }
        return false;
    }

    public String generateId(Entity entity) {
        String id = UUID.randomUUID().toString();
        switch (entity) {
            case STUDENT: {
                for (int i = 0; i < students.size(); i++) {
                    if (students.get(i).getId().equals(id)) {
                        return generateId(entity);
                    }
                }
            }
            break;
            case COURSE: {
                for (int i = 0; i < courses.size(); i++) {
                    if (courses.get(i).getId().equals(id)) {
                        return generateId(entity);
                    }
                }
            }
        }
        return id;
    }

    private enum Entity {
        STUDENT, COURSE
    }
}
