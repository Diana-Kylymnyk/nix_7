package dto;

import entity.Teacher;
import entity.Topic;

import java.sql.Timestamp;

public class CourseDto {

    Timestamp startsAt;
    Timestamp endsAt;
    Teacher teacher;
    Topic topic;

    public CourseDto(Timestamp startsAt, Timestamp endsAt, Teacher teacher, Topic topic) {
        this.startsAt = startsAt;
        this.endsAt = endsAt;
        this.teacher = teacher;
        this.topic = topic;
    }

    public Timestamp getStartsAt() {
        return startsAt;
    }

    public Timestamp getEndsAt() {
        return endsAt;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public Topic getTopic() {
        return topic;
    }
}
