import entity.Student;

public class StudentGenerationUtil {

    public static final String NAME = "test";

    public static Student generateStudent() {
        Student student = new Student();
        student.setFirstName(NAME);
        student.setLastName("lastNameTest");
        return student;
    }

    public static Student generateStudent(String firstName, String lastName) {
        Student student = new Student();
        student.setFirstName(firstName);
        student.setLastName(lastName);
        return student;
    }

    public static Student generateStudent(String lastName) {
        Student student = new Student();
        student.setFirstName(NAME);
        student.setLastName(lastName);
        return student;
    }
}
