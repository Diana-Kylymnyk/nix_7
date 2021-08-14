package dao;

import db.DBInMemory;
import entity.Course;
import util.MyArrayList;

public class CourseDao {

    public void create(Course course) {
        DBInMemory.getInstance().createCourse(course);
    }

    public void update(Course course) {
        DBInMemory.getInstance().updateCourse(course);
    }

    public void delete(String id) {
        DBInMemory.getInstance().deleteCourse(id);
    }

    public Course findCourseById(String id) {
        return DBInMemory.getInstance().findCourseById(id);
    }

    public MyArrayList<Course> findAllCourses() {
        return DBInMemory.getInstance().findAllCourses();
    }

    public boolean existById(String id) {
        return DBInMemory.getInstance().existCourseById(id);
    }
}
