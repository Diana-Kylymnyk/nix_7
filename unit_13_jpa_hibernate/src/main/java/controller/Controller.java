package controller;

import dto.CourseDto;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.CourseService;

import javax.persistence.EntityManager;
import java.util.Scanner;
import java.util.function.Supplier;

public class Controller {

    private static final Logger LOGGER_WARN = LoggerFactory.getLogger("warn");
    private static final Logger LOGGER_ERROR = LoggerFactory.getLogger("error");
    private static final Scanner in = new Scanner(System.in);

    public void start() {
        Configuration configuration = new Configuration().configure();
        try {
            SessionFactory sessionFactory = configuration.buildSessionFactory();
            Session session = sessionFactory.openSession();
            EntityManager entityManager = sessionFactory.createEntityManager();

            Supplier<EntityManager> supplier = () -> entityManager;
            CourseService courseService = new CourseService(supplier);

            boolean flag = true;
            while (flag) {
                System.out.println("Enter student id (exit - 0)");
                long studId = in.nextLong();
                if (studId != 0) {
                    CourseDto courseDto = courseService.findNextLessonByStudId(studId);
                    try {
                        System.out.println("Next lesson for student " + studId + ":");
                        System.out.println(courseDto.getTopic().getName());
                        System.out.println(courseDto.getStartsAt() + " - " + courseDto.getEndsAt());
                        System.out.println(courseDto.getTeacher().getName());
                    } catch (NullPointerException e) {
                        LOGGER_WARN.warn("Student does not have lessons");
                    }
                } else {
                    flag = false;
                }
            }
            session.close();
        } catch (Exception e) {
            LOGGER_ERROR.error("Error: ", e);
        }
    }
}
