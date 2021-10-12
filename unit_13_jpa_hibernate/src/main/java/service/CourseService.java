package service;

import dao.CourseDao;
import dto.CourseDto;
import entity.Lesson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.function.Supplier;

public class CourseService {

    private static final Logger LOGGER_INFO = LoggerFactory.getLogger("info");
    private static final Logger LOGGER_WARN = LoggerFactory.getLogger("warn");
    private static final Logger LOGGER_ERROR = LoggerFactory.getLogger("error");

    private final Supplier<EntityManager> managerSupplier;

    private final CourseDao dao = new CourseDao();

    public CourseService(Supplier<EntityManager> managerSupplier) {
        this.managerSupplier = managerSupplier;
    }

    public CourseDto findNextLessonByStudId(Long id) {
        EntityManager jpa = managerSupplier.get();
        try {
            LOGGER_INFO.info("Start finding lesson by student id");
            List<Lesson> result = dao.findNextLessonByStudId(jpa, id);
            LOGGER_INFO.info("Finish finding lesson by student id");
            if (result.size() != 0)
                return pushLessonToDto(result.get(0));
            else {
                LOGGER_WARN.warn("Student has not lesson");
                return null;
            }
        } catch (Exception e) {
            LOGGER_ERROR.error("Error: ", e);
            throw new RuntimeException(e);
        }
    }

    private CourseDto pushLessonToDto(Lesson lesson) {
        return new CourseDto(lesson.getStartsAt(), lesson.getEndsAt(), lesson.getTeacher(), lesson.getTopic());
    }
}
