package dao;

import db.DBInMemory;
import entity.Student;
import util.MyArrayList;

public class StudentDao {

    public void create(Student student) {
        DBInMemory.getInstance().createStudent(student);
    }

    public void update(Student student) {
        DBInMemory.getInstance().updateStudent(student);
    }

    public void delete(String id) {
        DBInMemory.getInstance().deleteStudent(id);
    }

    public Student findStudentById(String id) {
        return DBInMemory.getInstance().findStudentById(id);
    }

    public MyArrayList<Student> findAllStudents() {
        return DBInMemory.getInstance().findAllStudents();
    }

    public boolean existById(String id) {
        return DBInMemory.getInstance().existStudentById(id);
    }
}
