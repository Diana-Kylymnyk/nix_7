package entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "lessons")
public class Lesson extends BasicEntity {

    @Column(nullable = false)
    private String name;

    @Column(name = "starts_at", nullable = false)
    private Timestamp startsAt;

    @Column(name = "ends_at", nullable = false)
    private Timestamp endsAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "topic_id", nullable = false)
    private Topic topic;

    public String getName() {
        return name;
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
