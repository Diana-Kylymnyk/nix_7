package service;

import dao.CourseDao;
import dao.StudentDao;
import entity.Course;
import entity.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.MyArrayList;

public class StudentService {
    private static final Logger LOGGER_INFO = LoggerFactory.getLogger("info");
    private static final Logger LOGGER_WARN = LoggerFactory.getLogger("warn");
    private static final Logger LOGGER_ERROR = LoggerFactory.getLogger("error");

    private final StudentDao studentDao = new StudentDao();
    private final CourseDao courseDao = new CourseDao();

    public void create(Student student) {
        LOGGER_INFO.info("create new student: " + student.getFirstName());
        studentDao.create(student);

        Student student1 = new Student();
        student1.setId(student.getId());
        student1.setFirstName(student.getFirstName());
        student1.setLastName(student.getLastName());
        MyArrayList<Student> s = new MyArrayList<>();
        s.add(student1);

        if (student.getCourseList() != null) {
            for (int i = 0; i < student.getCourseList().size(); i++) {
                student.getCourseList().get(i).setStudentList(s);
                Course course = student.getCourseList().get(i);
                if (!courseDao.existById(course.getId())) {
                    LOGGER_INFO.info("create new course: " + course.getCourseTitle());
                    courseDao.create(course);
                }
            }
        }
    }

    public void update(Student student) {
        LOGGER_INFO.info("update student: " + student.getFirstName());
        if (studentDao.existById(student.getId())) {
            studentDao.update(student);

            String studentId = student.getId();
            student.setCourseList(studentDao.findStudentById(studentId).getCourseList());
            Student thisStudent = studentDao.findStudentById(studentId);
            if (thisStudent.getCourseList() != null) {
                for (int i = 0; i < thisStudent.getCourseList().size(); i++) {
                    String courseId = thisStudent.getCourseList().get(i).getId();
                    for (int j = 0; j < courseDao.findCourseById(courseId).getStudentList().size(); j++) {
                        if (courseDao.findCourseById(courseId).getStudentList().get(j).getId().equals(studentId))
                            courseDao.findCourseById(courseId).getStudentList().set(j, student);
                    }
                }
            }
        }
    }

    public void deleteStudent(String id) {
        LOGGER_WARN.warn("remove student by id: " + id);
        if (studentDao.existById(id)) {
            Student thisStudent = studentDao.findStudentById(id);
            if (thisStudent.getCourseList() != null) {
                for (int i = 0; i < thisStudent.getCourseList().size(); i++) {
                    String courseId = thisStudent.getCourseList().get(i).getId();
                    for (int j = 0; j < courseDao.findCourseById(courseId).getStudentList().size(); j++) {
                        if (courseDao.findCourseById(courseId).getStudentList().get(j).getId().equals(id))
                            courseDao.findCourseById(courseId).getStudentList().remove(j);
                    }
                }
            }
            studentDao.delete(id);
        }
    }

    public Student findStudentById(String id) {
        if (studentDao.findAllStudents() != null) {
            LOGGER_INFO.info("find student by id:" + id);
            return studentDao.findStudentById(id);
        } else {
            LOGGER_ERROR.error("List of students is null!");
            System.out.println("List of students is null!");
            return null;
        }
    }

    public MyArrayList<Student> findAllStudents() {
        LOGGER_INFO.info("find all students");
        if (studentDao.findAllStudents() != null) {
            return studentDao.findAllStudents();
        } else {
            LOGGER_ERROR.error("List of students is null!");
            System.out.println("List of students is null!");
            return null;
        }
    }

    public MyArrayList<Course> findAllCoursesOfStudentByStudentId(String id) {
        if (studentDao.findStudentById(id) != null) {
            LOGGER_INFO.info("find all courses of student by id:" + id);
            return studentDao.findStudentById(id).getCourseList();
        } else {
            LOGGER_ERROR.error("Student is null!");
            System.out.println("Student is null!");
            return null;
        }
    }
}
