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

        Course course1 = new Course();
        course1.setId(course.getId());
        course1.setCourseTitle(course.getCourseTitle());
        course1.setCredit(course.getCredit());
        MyArrayList<Course> c = new MyArrayList<>();
        c.add(course1);

        if (course.getStudentList() != null) {
            for (int i = 0; i < course.getStudentList().size(); i++) {
                course.getStudentList().get(i).setCourseList(c);
                Student student = course.getStudentList().get(i);
                if (!studentDao.existById(student.getId())) {
                    LOGGER_INFO.info("create new student: " + student.getFirstName());
                    studentDao.create(student);
                }
            }
        }
    }

    public void update(Course course) {
        LOGGER_INFO.info("update course: " + course.getCourseTitle());
        if (courseDao.existById(course.getId())) {
            courseDao.update(course);

            String courseId = course.getId();
            course.setStudentList(courseDao.findCourseById(courseId).getStudentList());
            Course thisCourse = courseDao.findCourseById(courseId);
            if (thisCourse.getStudentList() != null) {
                for (int i = 0; i < thisCourse.getStudentList().size(); i++) {
                    String studentId = thisCourse.getStudentList().get(i).getId();
                    for (int j = 0; j < studentDao.findStudentById(studentId).getCourseList().size(); j++) {
                        if (studentDao.findStudentById(studentId).getCourseList().get(j).getId().equals(courseId))
                            studentDao.findStudentById(studentId).getCourseList().set(j, course);
                    }
                }
            }
        }
    }

    public void deleteCourse(String id) {
        LOGGER_WARN.warn("remove course by id: " + id);
        if (courseDao.existById(id)) {
            Course thisCourse = courseDao.findCourseById(id);
            if (thisCourse.getStudentList() != null) {
                for (int i = 0; i < thisCourse.getStudentList().size(); i++) {
                    String studentId = thisCourse.getStudentList().get(i).getId();
                    for (int j = 0; j < studentDao.findStudentById(studentId).getCourseList().size(); j++) {
                        if (studentDao.findStudentById(studentId).getCourseList().get(j).getId().equals(id))
                            studentDao.findStudentById(studentId).getCourseList().remove(j);
                    }
                }
            }
            courseDao.delete(id);
        }
    }

    public Course findCourseById(String id) {
        if (courseDao.findAllCourses() != null) {
            LOGGER_INFO.info("find course by id:" + id);
            return courseDao.findCourseById(id);
        } else {
            LOGGER_ERROR.error("List of courses is null!");
            System.out.println("List of courses is null!");
            return null;
        }
    }

    public MyArrayList<Course> findAllCourses() throws NullPointerException {
        if (courseDao.findAllCourses() != null) {
            LOGGER_INFO.info("find all courses");
            return courseDao.findAllCourses();
        } else {
            LOGGER_ERROR.error("List of courses is null!");
            System.out.println("List of courses is null!");
            return null;
        }
    }

    public MyArrayList<Student> findAllStudentsOfCourseByCourseId(String id) {
        if (courseDao.findCourseById(id) != null) {
            LOGGER_INFO.info("find all students of course by id:" + id);
            return courseDao.findCourseById(id).getStudentList();
        } else {
            LOGGER_ERROR.error("Course is null!");
            System.out.println("Course is null!");
            return null;
        }
    }
}
