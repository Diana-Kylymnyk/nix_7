package dao;

import entity.Lesson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.List;

public class CourseDao {

    private static final Logger LOGGER_INFO = LoggerFactory.getLogger("info");
    private static final Logger LOGGER_ERROR = LoggerFactory.getLogger("error");

    public List<Lesson> findNextLessonByStudId(EntityManager jpa, Long id) {

        EntityTransaction transaction = jpa.getTransaction();
        transaction.begin();
        try {
            LOGGER_INFO.info("Start dao");
            String hql = "select l from Lesson l " +
                    "join l.topic topic " +
                    "join topic.courses course " +
                    "join course.groups coursegroup " +
                    "join coursegroup.students student " +
                    "where student.id = :id and current_timestamp < l.startsAt order by l.startsAt";
            TypedQuery<Lesson> query = jpa.createQuery(hql, Lesson.class);
            query.setMaxResults(1);
            query.setParameter("id", id);
            transaction.commit();
            return query.getResultList();
        } catch (Exception e) {
            LOGGER_ERROR.error("Error: ", e);
            transaction.rollback();
            throw new RuntimeException("failed to find next lesson");
        }
    }
}
