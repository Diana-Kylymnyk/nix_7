import entity.Course;
import entity.Student;
import org.junit.jupiter.api.*;
import service.CourseService;
import service.StudentService;
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
        courseService.create(course1);
        courseService.create(course2);

        MyArrayList<String> c = new MyArrayList<>();
        c.add(course1.getId());
        c.add(course2.getId());

        Student user = StudentGenerationUtil.generateStudent("lastName");
        user.setCourseListId(c);
        Course course = CourseGenerationUtil.generateCourse("test");
        courseService.create(course);
        studentService.create(user);
        System.out.println("\nCreate Student With Course List And Find All Courses:");
        printAll(students);
        printAll(courses);
        Assertions.assertEquals(3, courseService.findAllCourses().size());
    }

    public void printAll(MyArrayList<?> arrayList) {
        for (int i = 0; i < arrayList.size(); i++) {
            System.out.println(arrayList.get(i));
        }
    }
}
