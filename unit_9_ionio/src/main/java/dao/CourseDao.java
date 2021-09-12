package dao;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import entity.Course;
import util.MyArrayList;

import java.io.*;
import java.util.UUID;

public class CourseDao {

    private static final String filePath = "db" + File.separator + "courses.json";
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private MyArrayList<Course> courses = readAllCourses();

    private static void initDB() {
        try {
            File file = new File(filePath);
            file.getParentFile().mkdirs();
            file.createNewFile();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public MyArrayList<Course> readAllCourses() {
        try {
            MyArrayList<Course> res = gson.fromJson(new FileReader(filePath), new TypeToken<MyArrayList<Course>>() {
            }.getType());
            if (res == null) {
                throw new FileNotFoundException();
            }
            return res;
        } catch (FileNotFoundException e) {
            MyArrayList<Course> res = new MyArrayList<>();
            writeAllCourses(res);
            return res;
        }
    }

    public void writeAllCourses(MyArrayList<Course> lst) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            String json = gson.toJson(lst);
            writer.write(json);
        } catch (IOException e) {
            if (e instanceof FileNotFoundException) {
                initDB();
                writeAllCourses(new MyArrayList<>());
            } else
                e.printStackTrace();
        }
    }

    public void create(Course course) {
        course.setId(generateId());
        courses.add(course);
        writeAllCourses(courses);
    }

    public void createWithIdAndList(Course course, String id, MyArrayList<String> list) {
        course.setId(id);
        course.setStudentListId(list);
        courses.add(course);
        writeAllCourses(courses);
    }

    public void update(Course course) {
        Course courseById = findCourseById(course.getId());
        courseById.setStudentListId(courseById.getStudentListId());
        courseById.setCourseTitle(courseById.getCourseTitle());
        writeAllCourses(courses);
    }

    public void delete(String id) {
        courses = readAllCourses();
        for (int i = 0; i < courses.size(); i++) {
            if (courses.get(i).getId().equals(id)) {
                courses.remove(i);
            }
        }
        writeAllCourses(courses);
    }

    public Course findCourseById(String id) {
        courses = readAllCourses();
        for (int i = 0; i < courses.size(); i++) {
            if (courses.get(i).getId().equals(id)) {
                return courses.get(i);
            }
        }
        return null;
    }

    public boolean existById(String id) {
        courses = readAllCourses();
        for (int i = 0; i < courses.size(); i++) {
            if (courses.get(i).getId().equals(id)) {
                return true;
            }
        }
        return false;
    }

    private String generateId() {
        String id = UUID.randomUUID().toString();
        for (int i = 0; i < courses.size(); i++) {
            if (courses.get(i).getId().equals(id)) {
                return generateId();
            }
        }
        return id;
    }
}
