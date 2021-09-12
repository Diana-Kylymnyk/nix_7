package service;

import dao.CourseDao;
import dao.StudentDao;
import entity.Course;
import entity.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.MyArrayList;

public class CourseService {

    private static final Logger LOGGER_INFO = LoggerFactory.getLogger("info");
    private static final Logger LOGGER_WARN = LoggerFactory.getLogger("warn");
    private static final Logger LOGGER_ERROR = LoggerFactory.getLogger("error");

    private final StudentDao studentDao = new StudentDao();
    private final CourseDao courseDao = new CourseDao();

    public void create(Course course) {
        LOGGER_INFO.info("create new course: " + course.getCourseTitle());
        courseDao.create(course);

        if (course.getStudentListId() != null) {
            for (int i = 0; i < course.getStudentListId().size(); i++) {
                String studentId = course.getStudentListId().get(i);
                if (!studentDao.existById(studentId)) {
                    Student student = new Student();
                    MyArrayList<String> list = new MyArrayList<>();
                    list.add(course.getId());
                    studentDao.createWithIdAndList(student, studentId, list);
                }
            }
        }
    }

    public void update(Course course) {
        LOGGER_INFO.info("update course: " + course.getCourseTitle());
        if (courseDao.existById(course.getId())) {
            courseDao.update(course);
        }
    }

    public void deleteCourse(String id) {
        LOGGER_WARN.warn("remove course by id: " + id);
        if (courseDao.existById(id)) {
            courseDao.delete(id);
        }
    }

    public Course findCourseById(String id) {
        if (courseDao.readAllCourses() != null) {
            LOGGER_INFO.info("find course by id:" + id);
            return courseDao.findCourseById(id);
        } else {
            LOGGER_ERROR.error("List of courses is null!");
            System.out.println("List of courses is null!");
            return null;
        }
    }

    public MyArrayList<Course> findAllCourses() throws NullPointerException {
        if (courseDao.readAllCourses() != null) {
            LOGGER_INFO.info("find all courses");
            return courseDao.readAllCourses();
        } else {
            LOGGER_ERROR.error("List of courses is null!");
            System.out.println("List of courses is null!");
            return null;
        }
    }

    public MyArrayList<String> findAllStudentsOfCourseByCourseId(String id) {
        if (courseDao.findCourseById(id) != null) {
            LOGGER_INFO.info("find all students of course by id:" + id);
            return courseDao.findCourseById(id).getStudentListId();
        } else {
            LOGGER_ERROR.error("Course is null!");
            System.out.println("Course is null!");
            return null;
        }
    }
}
