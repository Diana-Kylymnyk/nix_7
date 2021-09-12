package dao;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import entity.Student;
import util.MyArrayList;

import java.io.*;
import java.util.UUID;

public class StudentDao {

    private static final String filePath = "db" + File.separator + "students.json";
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private MyArrayList<Student> students = readAllStudents();

    private void initDB() {
        try {
            File file = new File(filePath);
            file.getParentFile().mkdirs();
            file.createNewFile();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public MyArrayList<Student> readAllStudents() {
        try {
            MyArrayList<Student> res = gson.fromJson(new FileReader(filePath), new TypeToken<MyArrayList<Student>>() {
            }.getType());
            if (res == null) {
                throw new FileNotFoundException();
            }
            return res;
        } catch (FileNotFoundException e) {
            MyArrayList<Student> res = new MyArrayList<>();
            writeAllStudents(res);
            return res;
        }
    }

    public void writeAllStudents(MyArrayList<Student> lst) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            String json = gson.toJson(lst);
            writer.write(json);
        } catch (IOException e) {
            if (e instanceof FileNotFoundException) {
                initDB();
                writeAllStudents(new MyArrayList<>());
            } else
                e.printStackTrace();
        }
    }

    public void create(Student student) {
        student.setId(generateId());
        students.add(student);
        writeAllStudents(students);
    }

    public void createWithIdAndList(Student student, String id, MyArrayList<String> list) {
        student.setId(id);
        student.setCourseListId(list);
        students.add(student);
        writeAllStudents(students);
    }

    public void update(Student student) {
        students = readAllStudents();
        Student inDbUser = findStudentById(student.getId());
        inDbUser.setFirstName(student.getFirstName());
        inDbUser.setLastName(student.getLastName());
        writeAllStudents(students);
    }

    public void delete(String id) {
        students = readAllStudents();
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getId().equals(id)) {
                students.remove(i);
            }
        }
        writeAllStudents(students);
    }

    public Student findStudentById(String id) {
        students = readAllStudents();
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getId().equals(id)) {
                return students.get(i);
            }
        }
        return null;
    }


    public boolean existById(String id) {
        students = readAllStudents();
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getId().equals(id)) {
                return true;
            }
        }
        return false;
    }

    private String generateId() {
        String id = UUID.randomUUID().toString();
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getId().equals(id)) {
                return generateId();
            }
        }
        return id;
    }
}
