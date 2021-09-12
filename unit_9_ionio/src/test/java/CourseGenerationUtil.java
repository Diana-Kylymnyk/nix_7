import entity.Course;

public class CourseGenerationUtil {

    public static final String NAME = "testName";

    public static Course generateCourse() {
        Course course = new Course();
        course.setCourseTitle(NAME);
        course.setCredit(12);
        return course;
    }

    public static Course generateCourse(String name, int credit) {
        Course course = new Course();
        course.setCourseTitle(name);
        course.setCredit(credit);
        return course;
    }

    public static Course generateCourse(String name) {
        Course course = new Course();
        course.setCourseTitle(name);
        course.setCredit(12);
        return course;
    }
}
