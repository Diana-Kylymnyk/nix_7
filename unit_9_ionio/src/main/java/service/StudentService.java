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

        if (student.getCourseListId() != null) {
            for (int i = 0; i < student.getCourseListId().size(); i++) {
                String courseId = student.getCourseListId().get(i);
                if (!courseDao.existById(courseId)) {
                    Course course = new Course();
                    MyArrayList<String> list = new MyArrayList<>();
                    list.add(student.getId());
                    courseDao.createWithIdAndList(course, courseId, list);
                }
            }
        }
    }

    public void update(Student student) {
        LOGGER_INFO.info("update student: " + student.getFirstName());
        if (studentDao.existById(student.getId())) {
            studentDao.update(student);
        }
    }

    public void deleteStudent(String id) {
        LOGGER_WARN.warn("remove student by id: " + id);
        if (studentDao.existById(id)) {
            studentDao.delete(id);
        }
    }

    public Student findStudentById(String id) {
        if (studentDao.readAllStudents() != null) {
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
        if (studentDao.readAllStudents() != null) {
            return studentDao.readAllStudents();
        } else {
            LOGGER_ERROR.error("List of students is null!");
            System.out.println("List of students is null!");
            return null;
        }
    }

    public MyArrayList<String> findAllCoursesOfStudentByStudentId(String id) {
        if (studentDao.findStudentById(id) != null) {
            LOGGER_INFO.info("find all courses of student by id:" + id);
            return studentDao.findStudentById(id).getCourseListId();
        } else {
            LOGGER_ERROR.error("Student is null!");
            System.out.println("Student is null!");
            return null;
        }
    }
}
